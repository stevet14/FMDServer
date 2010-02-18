package de.hdtconsulting.yahoo.finance.sample;

import java.util.ArrayList;

import de.hdtconsulting.yahoo.finance.YQuote;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.YTrade;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Sample4 {

	public static void main(String[] args) {

		Yapi yapi = new Yapi();
		
		YFormat format = new YFormat();
		format.setStatusOn(YTag.BID);
		format.setStatusOn(YTag.BID_REAL_TIME);
		format.setStatusOn(YTag.NAME);
		
		yapi.setFormat(format);
		
		YSymbol symbol = new YSymbol("DTE.DE");
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

			ArrayList<YTrade> dayTrades = quote.getDayTrades(); 
			for(YTrade trade: dayTrades) {
				System.out.println(trade.getDate() + " " + trade.getTime() + " " + trade.getValue() + " " + trade.getVolume() );
			}
			System.out.println(dayTrades.size());

		}

	}

}
