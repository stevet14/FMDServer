package de.hdtconsulting.yahoo.finance;

/**
 * Symbol
 * @author hdt
 *
 */
public class YSymbol {

	private String code;

	public YSymbol(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		YSymbol symbol;

		if (o instanceof YSymbol) {
			symbol = (YSymbol) o;
		} else {
			return false;
		}

		if (!this.code.equals(symbol.getCode())) {
			return false;
		}

		return true;

	}

	// Quelle (C) 2007 Yahoo!
	// http://de.biz.yahoo.com/sd/index.html
	//
	/** USA - American Stock Exchange - Direkt von der Börse - 20 Min delay */
	public static String AMEX = "";
	/** USA - NASDAQ - Direkt von der Börse - 15 Min delay */
	public static String NASDAQ = "";
	/** USA - New York Stock Exchange - Direkt von der Börse - 20 Min delay */
	public static String NYSE = "";
	/** USA - OTC Bulletin Board Market - Direkt von der Börse - 20 Min delay */
	public static String OB = "OB";
	/** USA - Pink Sheets - Direkt von der Börse - 15 Min delay */
	public static String PK = "PK";
	/** Argentinien - Börse Buenos Aires - Telekurs Financial - 30 Min delay */
	public static String BA = "BA";
	/** Österreich - Wiener Börse - Telekurs Financial - 15 Min delay */
	public static String VI = "VI";
	/** Australien - Börse Australian - Comstock - 20 Min delay */
	public static String AX = "AX";
	/** Brasilien - Börse Sao Paolo - Comstock - 15 Min delay */
	public static String SA = "SA";
	/** Kanada - Börse Toronto - Comstock - 15 Min delay */
	public static String TO = "TO";
	/** Kanada - TSX Venture Exchange - Comstock - 15 Min delay */
	public static String V = "V";
	/** China - Börse Shanghai - Telekurs Financial - 30 Min delay */
	public static String SS = "SS";
	/** China - Börse Shenzhen - Telekurs Financial - 30 Min delay */
	public static String SZ = "SZ";
	/** Dänemark - Börse Kopenhagen - Telekurs Financial - 15 Min delay */
	public static String CO = "CO";
	/** Frankreich - Börse Paris - Telekurs Financial - 15 Min delay */
	public static String PA = "PA";
	/** Deutschland - Börse Berlin-Bremen - Telekurs Financial - 15 Min delay */
	public static String BE = "BE";
	/** Deutschland - Börse Düsseldorf - Telekurs Financial - 15 Min delay */
	public static String DU = "DU";
	/** Deutschland - Börse Frankfurt - Telekurs Financial - 15 Min delay */
	public static String F = "F";
	/** Deutschland - Börse Hamburg - Telekurs Financial - 15 Min delay */
	public static String HM = "HM";
	/** Deutschland - Börse Hanover - Telekurs Financial - 15 Min delay */
	public static String HA = "HA";
	/** Deutschland - Börse München - Telekurs Financial - 15 Min delay */
	public static String MU = "MU";
	/** Deutschland - Börse Stuttgart - Telekurs Financial - 15 Min delay */
	public static String SG = "SG";
	/** Deutschland - XETRA - Telekurs Financial - 15 Min delay */
	public static String DE = "DE";
	/** Hongkong - Börse Hong Kong - Telekurs Financial - 60 Min delay */
	public static String HK = "HK";
	/** Indien - Börse Bombay - Comstock - 15 Min delay */
	public static String BO = "BO";
	/** Indien - Nationale Börse Indien - Telekurs Financial - 15 Min delay */
	public static String NS = "NS";
	/** Indonesien - Börse Jakarta - Comstock - 10 Min delay */
	public static String JK = "JK";
	/** Irland - Irische Börse - Telekurs Financial - 15 Min delay */
	public static String IR = "IR";
	/** Israel - Börse Tel Aviv - Telekurs Financial - 20 Min delay */
	public static String TA = "TA";
	/** Italien - Börse Mailand - Comstock - 20 Min delay */
	public static String MI = "MI";
	/** Südkorea - Börse Korea - Comstock - 20 Min delay */
	public static String KS = "KS";
	/** Südkorea - KOSDAQ - Comstock - 20 Min delay */
	public static String KQ = "KQ";
	/** Mexiko - Börse Mexiko - Telekurs Financial - 20 Min delay */
	public static String MX = "MX";
	/** Niederlande - Börse Amsterdam - Telekurs Financial - 15 Min delay */
	public static String AS = "AS";
	/** Neuseeland - Börse Neuseeland - Comstock - 20 Min delay */
	public static String NZ = "NZ";
	/** Norwegen - Börse Oslo - Telekurs Financial - 15 Min delay */
	public static String OL = "OL";
	/** Portugal - Börse Lissabon - Telekurs Financial - 15 Min delay */
	public static String LS = "LS";
	/** Singapur - Börse Singapur - Comstock - 20 Min delay */
	public static String SI = "SI";
	/** Spanien - Börse Barcelona - Telekurs Financial - 15 Min delay */
	public static String BC = "BC";
	/** Spanien - Börse Bilbao - Telekurs Financial - 15 Min delay */
	public static String BI = "BI";
	/** Spanien - Madrid Fixed Income Market - Telekurs Financial - 15 Min delay */
	public static String MF = "MF";
	/** Spanien - Madrid SE C.A.T.S. - Telekurs Financial - 15 Min delay */
	public static String MC = "MC";
	/** Spanien - Börse Madrid - Telekurs Financial - 15 Min delay */
	public static String MA = "MA";
	/** Schweden - Börse Stockholm - Telekurs Financial - 15 Min delay */
	public static String ST = "ST";
	/** Schweiz - Schweizer Börse - Telekurs Financial - 15 Min delay */
	public static String SW = "SW";
	/** Schweiz - Virt-X - Telekurs Financial - 15 Min delay */
	public static String VX = "VX";
	/** Taiwan - OTC Börse Taiwan - Comstock - 20 Min delay */
	public static String TWO = "TWO";
	/** Taiwan - Börse Taiwan - Comstock - 20 Min delay */
	public static String TW = "TW";
	/** Thailand - Börse Thailand - Comstock - 15 Min delay */
	public static String BK = "BK";
	/** Großbritannien - Börse London - Telekurs Financial - 20 Min delay */
	public static String L = "L";

}
