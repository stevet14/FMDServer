package org.averni.fmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.averni.fmd.domain.Price;
import org.averni.fmd.domain.Symbol;
import org.averni.fmd.prices.URLPrices;
import org.averni.fmd.util.HibernateUtil;
import org.hibernate.Session;

public class SymbolLoader {

	private Log log = LogFactory.getLog(SymbolLoader.class);
	private Session session;

	public enum Exchange {
		AMEX, CVE, FINDEX, INDEX, LSE, NASDAQ, NYSE, TSX, FOREX, FUTURES
	}

	public Exchange exchange;

	public SymbolLoader() {
		URLPrices.checkProxyServer();
	}

	public void loadSymbols(Exchange exchange) {
		String exchangeFileName = getExchangeFileName(exchange);

		try {
			InputStream resource = this.getClass().getResourceAsStream(
					"/resource/Symbols/FMD/" + exchangeFileName);
			InputStreamReader isr = new InputStreamReader(resource);
			BufferedReader br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				String[] entries = line.split(",");
				String symbolCode = entries[0];
				String description = entries[1];

				session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Symbol symbol = (Symbol) session.createQuery(
						"from Symbol as symbol where symbol.symbol = ?")
						.setString(0, symbolCode)
						.uniqueResult();

				if (symbol == null) {
					symbol = new Symbol();
					symbol.setExchange(exchange.toString());
					symbol.setSymbol(symbolCode);
					symbol.setDescription(description);
					symbol.setMarketType("<unknown>");
				} 				
/*				else {
					Date lastDate = symbol.getPrices().iterator().next().getDate();
					//test if the date is recent (and continue).
				}
*/				session.save(symbol);
				try {
					addPrices(symbol);
					log.info("Saved symbol and prices for: "
							+ symbol.getDescription());
				} catch (Exception e) {
					// skip the symbol if we can't get any prices.
					log.error(e.getMessage() + symbol.getSymbol() + " - " + symbol.getDescription());
					continue;
				} finally {
				session.getTransaction().commit();
				}
			}
			br.close();
			isr.close();
			resource.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void addPrices(Symbol symbol) throws Exception {
		Set<Price> prices = getPrices(symbol);
		symbol.setPrices(prices);
		session.save(symbol);
	}

	private Set<Price> getPrices(Symbol symbol) throws Exception {
		Set<Price> prices = new HashSet<Price>();
		//Use 'Factory' to get appropriate 'prices' class...
		URLPrices urlPrices = URLPrices.getPricesClass(Exchange.valueOf(symbol.getExchange()));
		//Get the data by scraping form the relevant website URL and pass to the text parser...
		getPricesFromText(symbol, prices, urlPrices.getPrices(symbol));
		return prices;
	}

	private void getPricesFromText(Symbol symbol, Set<Price> prices,
			String[] lines) throws ParseException {
		for (String line : lines) {
			String[] entries = line.split(",");
			DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
			Date priceDate = (Date) formatter.parse(entries[0]);
			Price price = (Price) session
					.createQuery(
							"from Price as price where price.symbol = ? and price.date = ?")
					.setEntity(0, symbol).setDate(1, priceDate).uniqueResult();

			if (price == null) {
				price = new Price();
				price.setDate(priceDate);
				price.setOpen(Double.parseDouble(entries[1]));
				price.setHigh(Double.parseDouble(entries[2]));
				price.setLow(Double.parseDouble(entries[3]));
				price.setClose(Double.parseDouble(entries[4]));
				price.setVolume(0);
				if (symbol.getExchange().equals("FOREX"))
					price.setPeriod("Daily");
				else
					price.setPeriod("Weekly");
				price.setSymbol(symbol);
				prices.add(price);
				session.save(price);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String getSymbolData(String symbolCode) throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		/*
		 * Symbol symbol = (Symbol) session.createQuery(
		 * "from Symbol as symbol where symbol.symbol = ?").setString(0,
		 * symbolCode).uniqueResult();
		 */
		List<Price> prices = session
				.createQuery(
						"from Price as price where symbol.symbol = ? order by date desc")
				.setString(0, symbolCode).list();

		String data = "";
		// CSVWriter writer = new CSVWriter(new FileWriter(symbolCode + ".csv"),
		// ',', CSVWriter.NO_QUOTE_CHARACTER);

		// Skip if we don't have any prices...
		if (prices != null && prices.size() > 0) {
			// We need to extract weekly entries from daily events
			int modulus = prices.get(0).getPeriod().equals("Daily") ? 7 : 1;
			int lastIndex = prices.size() - (prices.size() % modulus);
			for (int i = 0; i < lastIndex; i += modulus) {
				String[] entries = new String[5];
				if (modulus > 1) {
					entries = getWeeklyFromDaily(prices, i, modulus);
				} else {
					Price price = prices.get(i);
					entries[0] = price.getDate().toString();
					entries[1] = Double.toString(price.getOpen());
					entries[2] = Double.toString(price.getHigh());
					entries[3] = Double.toString(price.getLow());
					entries[4] = Double.toString(price.getClose());
					// writer.writeNext(entries);
				}
				for (int k = 0; k < 4; k++) {
					data += entries[k] + ",";
				}
				data += entries[4] + "\n";
			}
		}
		// writer.close();
		session.close();
		log.info("Found: " + symbolCode);
		return data;
	}

	private String[] getWeeklyFromDaily(List<Price> prices, int i, int modulus) {
		double high = 0;
		double low = Double.MAX_VALUE;
		Calendar breakDate = Calendar.getInstance();
		breakDate.setTime(prices.get(i).getDate());
		breakDate.add(Calendar.DATE, 1 - modulus);
		while (prices.get(i + modulus - 1).getDate().after(breakDate.getTime())) {
			// If we have any missing trading days in the data we need to
			// shorten the week.
			modulus--;
		}
		for (int j = 0; j < modulus; j++) {
			double newHigh = prices.get(i + j).getHigh();
			double newLow = prices.get(i + j).getLow();
			if (newHigh > high)
				high = newHigh;
			if (newLow < low)
				low = newLow;
		}
		String[] entries = new String[5];
		entries[0] = prices.get(i + modulus - 1).getDate().toString();
		entries[1] = Double.toString(prices.get(i + modulus - 1).getOpen());
		entries[2] = Double.toString(high);
		entries[3] = Double.toString(low);
		entries[4] = Double.toString(prices.get(i).getClose());
		return entries;
	}

	private String getExchangeFileName(Exchange exchange) {
		switch (exchange) {
		case FINDEX:
			return "Findex.csv";
		case INDEX:
			return "Index.csv";
		case LSE:
			return "LSE.csv";
		case FOREX:
			return "Forex.csv";
		case FUTURES:
			return "Futures.csv";
		}
		return "";
	}
}
