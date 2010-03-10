package org.averni.fmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.averni.fmd.domain.Price;
import org.averni.fmd.domain.Symbol;
import org.averni.fmd.util.HibernateUtil;
import org.hibernate.Session;

import de.hdtconsulting.yahoo.finance.YHistoric;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.connection.YHost;

public class SymbolLoader {

	private Yapi yapi;
	private Session session;

	public enum Exchange {
		AMEX, CVE, FINDEX, INDEX, LSE, NASDAQ, NYSE, TSX, FOREX, FUTURES
	}

	public Exchange exchange;

	public SymbolLoader() {
		yapi = new Yapi();
		checkProxyServer();
	}

	public void loadSymbols(Exchange exchange) {
		String exchangeFileName = getExchangeFileName(exchange);

		try {
			InputStream resource = this.getClass().getResourceAsStream("/resource/" + exchangeFileName);
			InputStreamReader isr = new InputStreamReader(resource);
			BufferedReader br = new BufferedReader(isr);
			
			String line;

			while ((line = br.readLine()) != null) {
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				String symbolCode = "";
				String description = "";
				if (exchange.equals(Exchange.FINDEX)) {
					String[] entries = line.split(",");
					symbolCode = entries[0];
					description = entries[1];
				} else {
					if (line.length() < 64)
						continue; // skip lines without symbols...
					symbolCode = line.substring(63, 77).trim()
							.replace('-', '~').replace('.', '-').replace('~',
									'.');
					description = line.substring(0, 63).trim();
				}
				Symbol symbol = (Symbol) session.createQuery(
						"from Symbol as symbol where symbol.symbol = ?")
						.setString(0, symbolCode).uniqueResult();

				if (symbol == null) {
					symbol = new Symbol();
					symbol.setExchange(exchange.toString());
					symbol.setSymbol(symbolCode);
					symbol.setDescription(description);
					symbol.setMarketType("<unknown>");
				}
				session.save(symbol);
				try {
					addPrices(symbol);
				} catch (Exception e) {
					// skip the symbol if we can't get any prices.
					System.out.println(e.getMessage());
					continue;
				}
				session.getTransaction().commit();
				System.out.println("Saved symbol and prices for: "
						+ symbol.getDescription());
			}
			isr.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void addPrices(Symbol symbol) throws Exception {
		Set<Price> prices;
		switch (Exchange.valueOf(symbol.getExchange())) {
		case FOREX:
			prices = getForexPrices(symbol);
			break;
		case FUTURES:
			prices = getFuturesPrices(symbol);
			break;
		default:
			prices = getYahooPrices(symbol);
		}
		symbol.setPrices(prices);
		session.save(symbol);

	}

	private Set<Price> getFuturesPrices(Symbol symbol) throws Exception {

		Set<Price> prices = new HashSet<Price>();

		String urlString = "http://futures.tradingcharts.com/chart/"
				+ symbol.getSymbol()
				+ "/W/?saveprefs=t&xshowdata=t&xCharttype=b&xhide_specs=f&xhide_analysis=f&xhide_survey=f&xhide_news=f";
		URL url;
		URLConnection urlConn;
		BufferedReader dis;

		url = new URL(urlString);
		urlConn = url.openConnection();
		urlConn.setDoInput(true);
		urlConn.setUseCaches(false);
		urlConn
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.64 Safari/532.5");

		dis = new BufferedReader(
				new InputStreamReader(urlConn.getInputStream()));
		String s;

		while ((s = dis.readLine()) != null) {
			if (s.contains("<meta name=\"description\"")) {
				int start = s.indexOf("content");
				int end = s.indexOf(" -");
				symbol.setDescription(s.substring(start + 9, end));
			}
			if (s.contains("var dataStr"))
				break;
		}
		dis.close();

		if (s == null || !s.contains("|"))
			throw new Exception("Could not find symbol: " + symbol.getSymbol());
		// Bug out if we can't grab the data.

		s = s.substring(s.indexOf('\'') + 1, s.lastIndexOf('\''));
		// System.out.println(s);

		String[] lines = s.split("\\|");
		getPricesFromText(symbol, prices, lines);

		System.out.println("Found: " + symbol.getSymbol());

		return prices;

	}

	private Set<Price> getForexPrices(Symbol symbol) throws Exception {
		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };

		Set<Price> prices = new HashSet<Price>();
		// We need to iterate a few times to get all the data we need...
		for (int i = 1; i < 4; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK)); // Start
			// with
			// last Friday's
			// close...
			// System.out.println("Today is: " + cal.get(Calendar.DAY_OF_WEEK));

			int endYear = cal.get(Calendar.YEAR);
			String endMonth = monthName[cal.get(Calendar.MONTH)];
			int endDay = cal.get(Calendar.DATE);

			// 144 days is the maximum number of days we can grab so we need to
			// iterate a bit to get enough data
			cal.add(Calendar.DATE, -144 * i);
			// go back...
			int startYear = cal.get(Calendar.YEAR);
			String startMonth = monthName[cal.get(Calendar.MONTH)];
			int startDay = cal.get(Calendar.DATE);

			// System.out.println("Date: " + startDay + "/" + startMonth + "/" +
			// startYear);
			// System.out.println("Date: " + endDay + "/" + endMonth + "/" +
			// endYear);

			String urlString = "http://forex.tradingcharts.com/charts/index.php?sym="
					+ symbol.getSymbol()
					+ "&data=b&tz=GMT&type=b&cs=1&period=1d&defdates=0"
					+ "&bmonth="
					+ startMonth
					+ "&bday="
					+ startDay
					+ "&byear="
					+ startYear
					+ "&bhour=&bmin="
					+ "&emonth="
					+ endMonth
					+ "&eday="
					+ endDay
					+ "&eyear="
					+ endYear
					+ "&ehour=&emin=&Img+Type=png&drsi=0&ma1=0&dmacd=0&ma2=0&bol=0&dstoch=0&Submit=Submit";
			// System.out.println("URL: " + urlString);

			URL url;
			URLConnection urlConn;
			BufferedReader dis;

			url = new URL(urlString);
			urlConn = url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.64 Safari/532.5");

			dis = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			String s;// , name;

			// boolean isForex = false;
			while ((s = dis.readLine()) != null) {
				if (s.contains("<meta name=\"description\"")) {
					int start = s.indexOf("content");
					int end = s.lastIndexOf(" -"); // Assume its a Future...
					if (end == -1) {
						end = s.lastIndexOf(" ..."); // ...if not, it's a Forex.
						start += 26;
						// isForex = true;
					}
					// name = s.substring(start + 9, end);
				}
				if (s.contains("var dataStr"))
					break;
			}
			dis.close();

			if (s == null || !s.contains("|"))
				throw new Exception("Could not find symbol: "
						+ symbol.getSymbol());
			// Bug out if we can't grab the data.

			s = s.substring(s.indexOf('\'') + 1, s.lastIndexOf('\''));
			// System.out.println(s);
			String[] lines = s.split("\\|");
			getPricesFromText(symbol, prices, lines);
		}
		System.out.println("Found: " + symbol.getSymbol());

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
				// System.out.println("Symbol: " + symbol.getSymbol() +
				// " - "
				// + price.getDate() + " : Vol. : "
				// + price.getVolume());
			}
		}
	}

	private Set<Price> getYahooPrices(Symbol symbol) throws Exception {
		Set<Price> prices = new HashSet<Price>();

		Calendar gc = Calendar.getInstance();
		Date endDate = gc.getTime();
		gc.add(Calendar.WEEK_OF_MONTH, -100);
		Date startDate = gc.getTime();

		YSymbol ys = new YSymbol(symbol.getSymbol());
		ArrayList<YHistoric> historics = yapi.getHistoric(ys, startDate,
				endDate, Yapi.HIST_WEEKLY).getHistorics();

		if (historics.size() == 0)
			throw new Exception("Could not find symbol: " + ys.getCode());

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (YHistoric historic : historics) {
			Date priceDate = (Date) formatter.parse(historic.getDate());
			Price price = (Price) session
					.createQuery(
							"from Price as price where price.symbol = ? and price.date = ?")
					.setEntity(0, symbol).setDate(1, priceDate).uniqueResult();

			if (price == null) {
				price = new Price();
				price.setDate(priceDate);
				price.setOpen(historic.getOpen().doubleValue());
				price.setHigh(historic.getHigh().doubleValue());
				price.setLow(historic.getLow().doubleValue());
				price.setClose(historic.getClose().doubleValue());
				price.setVolume(historic.getVolume().doubleValue());
				price.setPeriod("Weekly");
				price.setSymbol(symbol);
				prices.add(price);
				// System.out.println("Symbol: " + symbol.getSymbol() + " - "
				// + price.getDate() + " : Vol. : "
				// + price.getVolume());
			}
		}
		return prices;
	}

	public void checkProxyServer() {
		try {
			if (InetAddress.getLocalHost().getHostAddress().startsWith("10.")) {
				// log.debug("using proxy");
			} else {
				// log.debug("not using proxy");
				return;
			}

			System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "webproxy.svr.chp.co.uk");
			System.setProperty("http.proxyPort", "3128");

			YHost proxy = new YHost();
			proxy.setServer("webproxy.svr.chp.co.uk");
			proxy.setPort(3128);
			yapi.setProxy(proxy);

		} catch (UnknownHostException e) {
			System.err.println("Unable to lookup proxy");
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
		System.out.println("Found: " + symbolCode);
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
		case AMEX:
			return "Symbols\\AMEX.txt";
		case CVE:
			return "Symbols\\CVE.txt";
		case FINDEX:
			return "Symbols\\ForeignIndices.csv";
		case INDEX:
			return "Symbols\\Index.txt";
		case LSE:
			return "Symbols\\LSE.txt";
		case NASDAQ:
			return "Symbols\\NASDAQ.txt";
		case NYSE:
			return "Symbols\\nyse.txt";
		case TSX:
			return "Symbols\\TSX.txt";
		case FOREX:
			return "Symbols\\Forex.txt";
		case FUTURES:
			return "Symbols\\Futures.txt";
		}
		return "";
	}
}
