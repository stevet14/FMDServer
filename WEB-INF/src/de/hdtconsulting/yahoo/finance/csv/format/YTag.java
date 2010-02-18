package de.hdtconsulting.yahoo.finance.csv.format;

/**
 * Tag
 * @author hdt
 *
 */
public class YTag {

	public YTag(String code) {

		this.code = code;
		this.setEnabled(false);

	}

	private String code;

	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {

		// SYMBOL and ERROR_INDICATION always enabled!
		if (SYMBOL.equals(this.code) || ERROR_INDICATION.equals(this.code)) {
			this.enabled = true;
		} else {
			this.enabled = enabled;
		}

	}

	public String getCode() {
		return code;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		YTag tag;

		if (o instanceof YTag) {
			tag = (YTag) o;
		} else {
			return false;
		}

		if (!this.code.equals(tag.getCode())) {
			return false;
		}

		return true;

	}

	/** Ask */
	public static String a = "a";
	/** Average Daily Volume */
	public static String a2 = "a2";
	/** Ask Size */
	public static String a5 = "a5";
	/** Bid */
	public static String b = "b";
	/** Ask (Real-time) */
	public static String b2 = "b2";
	/** Bid (Real-time) */
	public static String b3 = "b3";
	/** Book Value */
	public static String b4 = "b4";
	/** Bid Size */
	public static String b6 = "b6";
	/** Change & Percent Change */
	public static String c = "c";
	/** Change */
	public static String c1 = "c1";
	/** Commission */
	public static String c3 = "c3";
	/** Change (Real-time) */
	public static String c6 = "c6";
	/** After Hours Change (Real-time) */
	public static String c8 = "c8";
	/** Dividend/Share */
	public static String d = "d";
	/** Last Trade Date */
	public static String d1 = "d1";
	/** Trade Date */
	public static String d2 = "d2";
	/** Earnings/Share */
	public static String e = "e";
	/** Error Indication (returned for symbol changed / invalid) */
	public static String e1 = "e1";
	/** EPS Estimate Current Year */
	public static String e7 = "e7";
	/** EPS Estimate Next Year */
	public static String e8 = "e8";
	/** EPS Estimate Next Quarter */
	public static String e9 = "e9";
	/** Float Shares */
	public static String f6 = "f6";
	/** Day's Low */
	public static String g = "g";
	/** Holdings Gain Percent */
	public static String g1 = "g1";
	/** Annualized Gain */
	public static String g3 = "g3";
	/** Holdings Gain */
	public static String g4 = "g4";
	/** Holdings Gain Percent (Real-time) */
	public static String g5 = "g5";
	/** Holdings Gain (Real-time) */
	public static String g6 = "g6";
	/** Day's High */
	public static String h = "h";
	/** More Info */
	public static String i = "i";
	/** Order Book (Real-time) */
	public static String i5 = "i5";
	/** 52-week Low */
	public static String j = "j";
	/** Market Capitalization */
	public static String j1 = "j1";
	/** Market Cap (Real-time) */
	public static String j3 = "j3";
	/** EBITDA */
	public static String j4 = "j4";
	/** Change From 52-week Low */
	public static String j5 = "j5";
	/** Percent Change From 52-week Low */
	public static String j6 = "j6";
	/** 52-week High */
	public static String k = "k";
	/** Last Trade (Real-time) With Time */
	public static String k1 = "k1";
	/** Change Percent (Real-time) */
	public static String k2 = "k2";
	/** Last Trade Size */
	public static String k3 = "k3";
	/** Change From 52-week High */
	public static String k4 = "k4";
	/** Percent Change From 52-week High */
	public static String k5 = "k5";
	/** Last Trade (With Time) */
	public static String l = "l";
	/** Last Trade (Price Only) */
	public static String l1 = "l1";
	/** High Limit */
	public static String l2 = "l2";
	/** Low Limit */
	public static String l3 = "l3";
	/** Day's Range */
	public static String m = "m";
	/** Day's Range (Real-time) */
	public static String m2 = "m2";
	/** 50-day Moving Average */
	public static String m3 = "m3";
	/** 200-day Moving Average */
	public static String m4 = "m4";
	/** Change From 200-day Moving Average */
	public static String m5 = "m5";
	/** Percent Change From 200-day Moving Average */
	public static String m6 = "m6";
	/** Change From 50-day Moving Average */
	public static String m7 = "m7";
	/** Percent Change From 50-day Moving Average */
	public static String m8 = "m8";
	/** Name */
	public static String n = "n";
	/** Notes */
	public static String n4 = "n4";
	/** Open */
	public static String o = "o";
	/** Previous Close */
	public static String p = "p";
	/** Price Paid */
	public static String p1 = "p1";
	/** Change in Percent */
	public static String p2 = "p2";
	/** Price/Sales */
	public static String p5 = "p5";
	/** Price/Book */
	public static String p6 = "p6";
	/** Ex-Dividend Date */
	public static String q = "q";
	/** P/E Ratio */
	public static String r = "r";
	/** Dividend Pay Date */
	public static String r1 = "r1";
	/** P/E Ratio (Real-time) */
	public static String r2 = "r2";
	/** PEG Ratio */
	public static String r5 = "r5";
	/** Price/EPS Estimate Current Year */
	public static String r6 = "r6";
	/** Price/EPS Estimate Next Year */
	public static String r7 = "r7";
	/** Symbol */
	public static String s = "s";
	/** Shares Owned */
	public static String s1 = "s1";
	/** Short Ratio */
	public static String s7 = "s7";
	/** Last Trade Time */
	public static String t1 = "t1";
	/** Trade Links */
	public static String t6 = "t6";
	/** Ticker Trend */
	public static String t7 = "t7";
	/** 1 yr Target Price */
	public static String t8 = "t8";
	/** Volume */
	public static String v = "v";
	/** Holdings Value */
	public static String v1 = "v1";
	/** Holdings Value (Real-time) */
	public static String v7 = "v7";
	/** 52-week Range */
	public static String w = "w";
	/** Day's Value Change */
	public static String w1 = "w1";
	/** Day's Value Change (Real-time) */
	public static String w4 = "w4";
	/** Stock Exchange */
	public static String x = "x";
	/** Dividend Yield */
	public static String y = "y";

	/** Ask */
	public static String ASK = a;
	/** Average Daily Volume */
	public static String AVERAGE_DAILY_VOLUME = a2;
	/** Ask Size */
	public static String ASK_SIZE = a5;
	/** Bid */
	public static String BID = b;
	/** Ask (Real-time) */
	public static String ASK_REAL_TIME = b2;
	/** Bid (Real-time) */
	public static String BID_REAL_TIME = b3;
	/** Book Value */
	public static String BOOK_VALUE = b4;
	/** Bid Size */
	public static String BID_SIZE = b6;
	/** Change & Percent Change */
	public static String CHANGE_CHANGE_PERCENT = c;
	/** Change */
	public static String CHANGE = c1;
	/** Commission */
	public static String COMMISSION = c3;
	/** Change (Real-time) */
	public static String CHANGE_REAL_TIME = c6;
	/** After Hours Change (Real-time) */
	public static String AFTER_HOURS_CHANGE_REAL_TIME = c8;
	/** Dividend/Share */
	public static String DIVIDEND_SHARE = d;
	/** Last Trade Date */
	public static String LAST_TRADE_DATE = d1;
	/** Trade Date */
	public static String TRADE_DATE = d2;
	/** Earnings/Share */
	public static String EARNINGS_SHARE = e;
	/** Error Indication (returned for symbol changed / invalid) */
	public static String ERROR_INDICATION = e1;
	/** EPS Estimate Current Year */
	public static String EPS_ESTIMATE_CURRENT_YEAR = e7;
	/** EPS Estimate Next Year */
	public static String EPS_ESTIMATE_NEXT_YEAR = e8;
	/** EPS Estimate Next Quarter */
	public static String EPS_ESTIMATE_NEXT_QUARTER = e9;
	/** Float Shares */
	public static String FLOAT_SHARES = f6;
	/** Day's Low */
	public static String LOW_DAY = g;
	/** Holdings Gain Percent */
	public static String HOLDINGS_GAIN_PERCENT = g1;
	/** Annualized Gain */
	public static String ANNUALIZED_GAIN = g3;
	/** Holdings Gain */
	public static String HOLDINGS_GAIN = g4;
	/** Holdings Gain Percent (Real-time) */
	public static String HOLDINGS_GAIN_PERCENT_REAL_TIME = g5;
	/** Holdings Gain (Real-time) */
	public static String HOLDINGS_GAIN_REAL_TIME = g6;
	/** Day's High */
	public static String HIGH_DAY = h;
	/** More Info */
	public static String MORE_INFO = i;
	/** Order Book (Real-time) */
	public static String ORDER_BOOK_REAL_TIME = i5;
	/** 52-week Low */
	public static String LOW_WEEK_52 = j;
	/** Market Capitalization */
	public static String MARKET_CAPITALIZATION = j1;
	/** Market Cap (Real-time) */
	public static String MARKET_CAPITALIZATION_REAL_TIME = j3;
	/** EBITDA */
	public static String EBITDA = j4;
	/** Change From 52-week Low */
	public static String CHANGE_FROM_52_WEEK_LOW = j5;
	/** Percent Change From 52-week Low */
	public static String CHANGE_FROM_52_WEEK_LOW_PERCENT = j6;
	/** 52-week High */
	public static String HIGH_52_WEEK = k;
	/** Last Trade (Real-time) With Time */
	public static String LAST_TRADE_WITH_TIME_REAL_TIME = k1;
	/** Change Percent (Real-time) */
	public static String CHANGE_PERCENT_REAL_TIME = k2;
	/** Last Trade Size */
	public static String LAST_TRADE_SIZE = k3;
	/** Change From 52-week High */
	public static String CHANGE_FROM_52_WEEK_HIGH = k4;
	/** Percent Change From 52-week High */
	public static String CHANGE_FROM_52_WEEK_HIGH_PERCENT = k5;
	/** Last Trade (With Time) */
	public static String LAST_TRADE_WITH_TIME = l;
	/** Last Trade (Price Only) */
	public static String LAST_TRADE_PRICE_ONLY = l1;
	/** High Limit */
	public static String HIGH_LIMIT = l2;
	/** Low Limit */
	public static String LOW_LIMIT = l3;
	/** Day's Range */
	public static String RANGE_DAY = m;
	/** Day's Range (Real-time) */
	public static String RANGE_DAY_REAL_TIME = m2;
	/** 50-day Moving Average */
	public static String MOVING_AVERAGE_50_DAY = m3;
	/** 200-day Moving Average */
	public static String MOVING_AVERAGE_200_DAY = m4;
	/** Change From 200-day Moving Average */
	public static String CHANGE_FROM_200_DAY_MOVING_AVERAGE = m5;
	/** Percent Change From 200-day Moving Average */
	public static String CHANGE_FROM_200_DAY_MOVING_AVERAGE_PERCENT = m6;
	/** Change From 50-day Moving Average */
	public static String CHANGE_FROM_50_DAY_MOVING_AVERAGE = m7;
	/** Percent Change From 50-day Moving Average */
	public static String CHANGE_FROM_50_DAY_MOVING_AVERAGE_PERCENT = m8;
	/** Name */
	public static String NAME = n;
	/** Notes */
	public static String NOTES = n4;
	/** Open */
	public static String OPEN = o;
	/** Previous Close */
	public static String PREVIOUS_CLOSE = p;
	/** Price Paid */
	public static String PRICE_PAID = p1;
	/** Change in Percent */
	public static String CHANGE_PERCENT = p2;
	/** Price/Sales */
	public static String PRICE_SALES = p5;
	/** Price/Book */
	public static String PRICE_BOOK = p6;
	/** Ex-Dividend Date */
	public static String EX_DIVIDEND_DATE = q;
	/** P/E Ratio */
	public static String PE_RATIO = r;
	/** Dividend Pay Date */
	public static String DIVIDEND_PAY_DATE = r1;
	/** P/E Ratio (Real-time) */
	public static String PE_RATIO_REAL_TIME = r2;
	/** PEG Ratio */
	public static String PEG_RATIO = r5;
	/** Price/EPS Estimate Current Year */
	public static String PRICE_EPS_ESTIMATE_CURRENT_YEAR = r6;
	/** Price/EPS Estimate Next Year */
	public static String PRICE_EPS_ESTIMATE_NEXT_YEAR = r7;
	/** Symbol */
	public static String SYMBOL = s;
	/** Shares Owned */
	public static String SHARES_OWNED = s1;
	/** Short Ratio */
	public static String SHORT_RATIO = s7;
	/** Last Trade Time */
	public static String LAST_TRADE_TIME = t1;
	/** Trade Links */
	public static String TRADE_LINKS = t6;
	/** Ticker Trend */
	public static String TICKER_TREND = t7;
	/** 1 yr Target Price */
	public static String TARGET_PRICE_1_YEAR = t8;
	/** Volume */
	public static String VOLUME = v;
	/** Holdings Value */
	public static String HOLDINGS_VALUE = v1;
	/** Holdings Value (Real-time) */
	public static String HOLDINGS_VALUE_REAL_TIME = v7;
	/** 52-week Range */
	public static String RANGE_52_WEEK = w;
	/** Day's Value Change */
	public static String VALUE_CHANGE_DAY = w1;
	/** Day's Value Change (Real-time) */
	public static String VALUE_CHANGE_DAY_REAL_TIME = w4;
	/** Stock Exchange */
	public static String STOCK_EXCHANGE = x;
	/** Dividend Yield */
	public static String DIVIDEND_YIELD = y;

}
