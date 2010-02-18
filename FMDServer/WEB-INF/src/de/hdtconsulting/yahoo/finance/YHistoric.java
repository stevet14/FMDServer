package de.hdtconsulting.yahoo.finance;

import java.math.BigDecimal;

/**
 * historical stock quote data item
 * @author hdt
 *
 */
public class YHistoric {

	private String date;
	
	private BigDecimal open;
	
	private BigDecimal high;
	
	private BigDecimal low;
	
	private BigDecimal close;
	
	private BigDecimal volume;
	
	private BigDecimal adjClose;

	public BigDecimal getOpen() {
		return open;
	}

	protected void setOpen(BigDecimal open) {
		this.open = open;
	}

	public String getDate() {
		return date;
	}

	protected void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getHigh() {
		return high;
	}

	protected void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	protected void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	protected void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	protected void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getAdjClose() {
		return adjClose;
	}

	protected void setAdjClose(BigDecimal adjClose) {
		this.adjClose = adjClose;
	}
	
}
