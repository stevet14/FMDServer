package de.hdtconsulting.yahoo.finance.sample;

import de.hdtconsulting.yahoo.finance.YCurrency;
import de.hdtconsulting.yahoo.finance.YExchangeRate;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;
import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
import de.hdtconsulting.yahoo.finance.csv.format.YTag;

public class Sample3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Yapi yapi = new Yapi();
		
		YFormat format = new YFormat();
		format.setStatusOn(YTag.ASK);
		format.setStatusOn(YTag.ASK_REAL_TIME);
		format.setStatusOn(YTag.ASK_SIZE);
		format.setStatusOn(YTag.BID);
		format.setStatusOn(YTag.BID_REAL_TIME);
		format.setStatusOn(YTag.BID_SIZE);
		format.setStatusOn(YTag.NAME);
		
		yapi.setFormat(format);
		
		YSymbol symbol = new YSymbol("DTE.DE");
		yapi.addQuote(symbol);

		YCurrency c1 = new YCurrency(YCurrency.EUR);
		YCurrency c2 = new YCurrency(YCurrency.USD);
		YCurrency c3 = new YCurrency(YCurrency.AED);
		
		YExchangeRate rate = new YExchangeRate(c1, c2);
		yapi.addQuote(rate.getSymbol());
		rate = new YExchangeRate(c1, c3);
		yapi.addQuote(rate.getSymbol());

		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

		yapi.refresh();
		
		System.out.println();
		System.out.println(yapi.getCsv());
		System.out.println(yapi.getRefreshTime());

	}
	
}
