package de.hdtconsulting.yahoo.finance.sample;

import java.util.ArrayList;

import de.hdtconsulting.yahoo.finance.YQuote;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;


public class Sample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Yapi yapi = new Yapi();
		
		YFormat format = new YFormat();
		format.setStatusOn(YTag.BID);
		format.setStatusOn(YTag.BID_REAL_TIME);
		format.setStatusOn(YTag.NAME);
		
		yapi.setFormat(format);
		
		YSymbol symbol = new YSymbol("DTE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DTE.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("PAH3.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DBK.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DE0008404005.DE");
		yapi.addQuote(symbol);

		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		yapi.refresh();
		
		System.out.println();
		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());
		
		ArrayList<YQuote> quoteList = yapi.getQuotes();

		for(YQuote quote : quoteList) {

			System.out.println(quote.getSymbol().getCode());
			System.out.println(quote.getValue(YTag.SYMBOL));
			System.out.println(quote.getValue(YTag.NAME));
			System.out.println(quote.getValue(YTag.BID_REAL_TIME));
			System.out.println(quote.getValue(YTag.BID));
			System.out.println(quote.isValid());
			System.out.println();

		}

	}

}
