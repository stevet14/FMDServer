package de.hdtconsulting.yahoo.finance;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdtconsulting.yahoo.finance.csv.format.YTag;

/**
 * Quote / ExchangeRate
 * 
 * @author hdt
 *
 */
public class YQuote {

	private YSymbol symbol;
	
	private HashMap<YTag,String> values = new HashMap<YTag, String>();

	private ArrayList<YTrade> dayTrades = new ArrayList<YTrade>();
	
	private ArrayList<YHistoric> historics = new ArrayList<YHistoric>();

	public ArrayList<YHistoric> getHistorics() {
		return new ArrayList<YHistoric>(historics);
	}

	protected void setHistorics(ArrayList<YHistoric> historics) {
		this.historics = historics;
	}

	public ArrayList<YTrade> getDayTrades() {
		return new ArrayList<YTrade>(dayTrades);
	}

	protected void setDayTrades(ArrayList<YTrade> dayTrades) {
		this.dayTrades = dayTrades;
	}

	private String csvDayTrade;
	
	private String csvHistoric;
	
	public String getCsvHistoric() {
		return csvHistoric;
	}

	protected void setCsvHistoric(String csvHistoric) {
		this.csvHistoric = csvHistoric;
	}

	public String getCsvDayTrade() {
		return csvDayTrade;
	}

	protected void setCsvDayTrade(String csvDayTrade) {
		this.csvDayTrade = csvDayTrade;
	}

	private boolean valid = false;

	protected YQuote(YSymbol symbol) {
		this.symbol = symbol;
	}
	
	public boolean isValid() {
		return valid;
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
	}

	public YSymbol getSymbol() {
		return symbol;
	}

	public String getValue(String key) {
		YTag tag = new YTag(key);
		return values.get(tag);
	}

	protected void setValue(YTag tag, String value) {
		values.put(tag, value);
	}

	@Override
	public int hashCode() {
		return this.symbol.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		YQuote quote;
	
		if (o instanceof YQuote) {
			quote = (YQuote) o ;
		} else {
			return false;
		}
		
		if(!this.symbol.equals(quote.getSymbol())) {
			return false;
		}
		
		return true;

	}

}
