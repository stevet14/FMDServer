package de.hdtconsulting.yahoo.finance;

import java.math.BigDecimal;

/**
 * daytrade data item
 * @author hdt
 *
 */
public class YTrade {

	private String date;

	private String time;

	private BigDecimal value;

	private Integer volume;

	private Integer unknown;

	public String getTime() {
		return time;
	}

	protected void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getValue() {
		return value;
	}

	protected void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getVolume() {
		return volume;
	}

	protected void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getUnknown() {
		return unknown;
	}

	protected void setUnknown(Integer unknown) {
		this.unknown = unknown;
	}

	public String getDate() {
		return date;
	}

	protected void setDate(String date) {
		this.date = date;
	}

}
