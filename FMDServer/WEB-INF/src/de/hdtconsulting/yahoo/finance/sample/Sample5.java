package de.hdtconsulting.yahoo.finance.sample;

import java.util.Calendar;
import java.util.Date;

import de.hdtconsulting.yahoo.finance.YQuote;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Sample5 {

	public static void main(String[] args) {

		Yapi yapi = new Yapi();
		
		YFormat format = new YFormat();
		format.setStatusOn(YTag.BID);
		format.setStatusOn(YTag.BID_REAL_TIME);
		format.setStatusOn(YTag.NAME);
		
		yapi.setFormat(format);
		
		YSymbol symbol = new YSymbol("DTE");
		symbol = new YSymbol("DTE.DE");
		yapi.addQuote(symbol);

		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		yapi.refresh();
		
		System.out.println();
		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2005, 0, 1);
		Date startDate = cal.getTime();
		cal.clear();
		cal.set(2005,11,31);
		Date endDate = cal.getTime();
		
		YQuote quote = yapi.getHistoric(symbol, startDate, endDate, Yapi.HIST_DAYLY);
		
		System.out.println(quote.getCsvHistoric());
		
		System.out.println(quote.getHistorics().size());

	}

}
