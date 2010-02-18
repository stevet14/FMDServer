package de.hdtconsulting.yahoo.finance.sample;

import java.util.ArrayList;

import de.hdtconsulting.yahoo.finance.YQuote;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Sample2 {

	public static void main(String[] args) {

		Yapi yapi = new Yapi();

		yapi.addTag(YTag.NAME);
		yapi.addTag(YTag.LAST_TRADE_DATE);
		yapi.addTag(YTag.LAST_TRADE_TIME);
		yapi.addTag(YTag.BID_REAL_TIME);
		yapi.addTag(YTag.ASK_REAL_TIME);

		YSymbol symbol = new YSymbol("DTE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DTE.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("PAH3.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DBK.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("VOW3.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("AB1.DE");
		yapi.addQuote(symbol);
		symbol = new YSymbol("DE0008404005.DE");
		yapi.addQuote(symbol);

		yapi.refresh();

		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		ArrayList<YQuote> quotes = yapi.getQuotes();

		System.out.println(quotes.size());

		symbol = new YSymbol("AB1.DE");
		yapi.removeQuote(symbol);
		System.out.println(quotes.size());
		quotes = yapi.getQuotes();
		System.out.println(quotes.size());

	}

}
