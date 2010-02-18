package de.hdtconsulting.yahoo.finance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import de.hdtconsulting.yahoo.finance.csv.connection.YConnection;
import de.hdtconsulting.yahoo.finance.csv.connection.YConnectionDayTrade;
import de.hdtconsulting.yahoo.finance.csv.connection.YConnectionHistoric;
import de.hdtconsulting.yahoo.finance.csv.connection.YHost;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Yapi {

	public static String HIST_DAYLY = "d";

	public static String HIST_WEEKLY = "w";

	public static String HIST_MONTHLY = "m";

	public static String HIST_DIVIDEND = "v";

	private YConnection connection = new YConnection();

	private YConnectionDayTrade connectionDayTrade = new YConnectionDayTrade();

	private YConnectionHistoric connectionHistoric = new YConnectionHistoric();

	private YQuoteList quoteList = new YQuoteList();

	private YFormat format = new YFormat();

	private Date refreshTime;

	private StringBuffer csv = new StringBuffer();
	
	public void setProxy(YHost proxy) {
		this.connection.setProxy(proxy);
		this.connectionDayTrade.setProxy(proxy);
		this.connectionHistoric.setProxy(proxy);
	}
	
	public void resetProxy() {
		this.connection.resetProxy();
		this.connectionHistoric.resetProxy();
		this.connectionDayTrade.resetProxy();
	}
	
	public void setFormat(YFormat format) {
		this.format = format;
	}

	public Date getRefreshTime() {
		return refreshTime;
	}

	public String getCsv() {
		return csv.toString();
	}

	public void addTag(String code) {
		this.format.setStatusOn(code);
	}

	public void removeTag(String code) {
		this.format.setStatusOff(code);
	}

	public void addQuote(YSymbol symbol) {
		this.quoteList.add(symbol);
	}

	public void removeQuote(YSymbol symbol) {
		this.quoteList.remove(symbol);
	}
	
	public void removeAllQuotes() {
		this.quoteList.clear();
	}

	public void refresh() {

		try {

			refreshRealTime();

			refreshDayTrade();

			this.refreshTime = new Date();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void refreshRealTime() throws MalformedURLException, IOException, URISyntaxException {

		this.connection.setFormat(this.format.getUrlFormatParameter());
		this.connection.setSymbols(this.quoteList.getUrlSymbolParameter());

		//TODO refactor extract method
		InputStreamReader input = new InputStreamReader(this.connection
				.getCsv());

		StringBuffer buffer = new StringBuffer();
		BufferedReader data = new BufferedReader(input);

		String line;
		while ((line = data.readLine()) != null) {
			buffer.append(line + "\n");
		}
		this.csv = buffer;
		//
		
		StringReader br = new StringReader(buffer.toString());
		CSVReader reader = new CSVReader(br);

		List<String[]> csvdata = reader.readAll();

		List<YTag> enabledTags;
		enabledTags = this.format.getEnabledTags();

		for (String[] csvline : csvdata) {

			YQuote quote = this.quoteList.getStockQuote(csvline[0]);
			int i = 0;
			for (YTag tag : enabledTags) {
				quote.setValue(tag, csvline[i]);
				i++;
			}
			if ("N/A".equals(quote.getValue(YTag.ERROR_INDICATION))) {
				quote.setValid(true);
			}

		}

	}

	public void refreshDayTrade() throws MalformedURLException, IOException, URISyntaxException {

		for (YQuote quote : quoteList.getQuotes()) {

			this.connectionDayTrade.setSymbol(quote.getSymbol().getCode());

			InputStreamReader input = new InputStreamReader(
					this.connectionDayTrade.getCsv());

			BufferedReader data = new BufferedReader(input);

			StringBuffer buffer = new StringBuffer();
			String line;

			while ((line = data.readLine()) != null) {
				buffer.append(line + "\n");
			}
			quote.setCsvDayTrade(buffer.toString());

			StringReader br = new StringReader(buffer.toString());
			CSVReader reader = new CSVReader(br, ';');

			List<String[]> csvdata = reader.readAll();
			ArrayList<YTrade> dayTrades = new ArrayList<YTrade>();

			for (String[] csvline : csvdata) {
				YTrade trade = new YTrade();
				trade.setDate(csvline[0]);
				trade.setTime(csvline[1]);
				trade.setValue(new BigDecimal(csvline[2]));
				trade.setVolume(new Integer(csvline[3]));
				trade.setUnknown(new Integer(csvline[4]));
				dayTrades.add(trade);
			}
			quote.setDayTrades(dayTrades);

		}

	}

	public YQuote getHistoric(YSymbol symbol, Date startDate, Date endDate,
			String interval) {

		YQuote quote;

		if ((quote = this.quoteList.getStockQuote(symbol)) == null) {
			quote = new YQuote(symbol);
		}

		this.connectionHistoric.setSymbol(quote.getSymbol().getCode());
		this.connectionHistoric.setStartDate(startDate);
		this.connectionHistoric.setEndDate(endDate);
		this.connectionHistoric.setInterval(interval);

		try {

			InputStreamReader input = new InputStreamReader(
					this.connectionHistoric.getCsv());

			BufferedReader data = new BufferedReader(input);

			StringBuffer buffer = new StringBuffer();
			String line;

			while ((line = data.readLine()) != null) {
				buffer.append(line + "\n");
			}
			quote.setCsvHistoric(buffer.toString());

			StringReader br = new StringReader(buffer.toString());
			CSVReader reader = new CSVReader(br);

			List<String[]> csvdata = reader.readAll();
			ArrayList<YHistoric> historics = new ArrayList<YHistoric>();

			for (String[] csvline : csvdata) {

				// first line is header
				if (!"Date".equals(csvline[0])) {
					YHistoric historic = new YHistoric();
					historic.setDate(csvline[0]);
					historic.setOpen(new BigDecimal(csvline[1]));
					historic.setHigh(new BigDecimal(csvline[2]));
					historic.setLow(new BigDecimal(csvline[3]));
					historic.setClose(new BigDecimal(csvline[4]));
					historic.setVolume(new BigDecimal(csvline[5]));
					historic.setAdjClose(new BigDecimal(csvline[6]));
					historics.add(historic);
				}

			}
			quote.setHistorics(historics);

		} catch (Exception e) {
//			System.out.println("Couldn't find symbol: " + symbol.getCode());
		}

		return quote;

	}

	public ArrayList<YQuote> getQuotes() {
		return quoteList.getQuotes();
	}

}
