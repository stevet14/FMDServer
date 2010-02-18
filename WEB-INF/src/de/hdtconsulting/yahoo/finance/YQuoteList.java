package de.hdtconsulting.yahoo.finance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * list of quotes to handle at one time
 * @author hdt
 *
 */
public class YQuoteList {

	private ArrayList<YQuote> quoteList = new ArrayList<YQuote>();
	
	private HashMap<YSymbol, YQuote> quoteMap = new HashMap<YSymbol, YQuote>();
	
	private HashMap<String, YSymbol> symbolMap = new HashMap<String, YSymbol>();

	public YQuote getStockQuote(String code) {
		return this.quoteMap.get(this.symbolMap.get(code));
	}

	public YQuote getStockQuote(YSymbol symbol) {
		return this.quoteMap.get(symbol);
	}
	
	public void add(YSymbol symbol) {
		YQuote quote = new YQuote(symbol);
		quoteList.add(quote);
		quoteMap.put(symbol, quote);
		symbolMap.put(symbol.getCode(), symbol);
	}

	public void remove(YSymbol symbol) {
		YQuote quote = this.quoteMap.get(symbol);
		quoteList.remove(quote);
		quoteMap.remove(symbol);
		symbolMap.remove(symbol.getCode());
	}

	public void clear() {
		quoteList.clear();
		quoteMap.clear();
		symbolMap.clear();
	}
	
	public String getUrlSymbolParameter() {
		
		StringBuffer sb = new StringBuffer();
		
		for(YQuote s: quoteList) {
			sb.append("+");
			sb.append(s.getSymbol().getCode());
		}

		return sb.toString();
		
	}
	
	public int size() {
		return this.quoteList.size();
	}

	public ArrayList<YQuote> getQuotes() {
		ArrayList<YQuote> list = new ArrayList<YQuote>(quoteList);
		return list;
	}

}
