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
	/** USA - American Stock Exchange - Direkt von der B�rse - 20 Min delay */
	public static String AMEX = "";
	/** USA - NASDAQ - Direkt von der B�rse - 15 Min delay */
	public static String NASDAQ = "";
	/** USA - New York Stock Exchange - Direkt von der B�rse - 20 Min delay */
	public static String NYSE = "";
	/** USA - OTC Bulletin Board Market - Direkt von der B�rse - 20 Min delay */
	public static String OB = "OB";
	/** USA - Pink Sheets - Direkt von der B�rse - 15 Min delay */
	public static String PK = "PK";
	/** Argentinien - B�rse Buenos Aires - Telekurs Financial - 30 Min delay */
	public static String BA = "BA";
	/** �sterreich - Wiener B�rse - Telekurs Financial - 15 Min delay */
	public static String VI = "VI";
	/** Australien - B�rse Australian - Comstock - 20 Min delay */
	public static String AX = "AX";
	/** Brasilien - B�rse Sao Paolo - Comstock - 15 Min delay */
	public static String SA = "SA";
	/** Kanada - B�rse Toronto - Comstock - 15 Min delay */
	public static String TO = "TO";
	/** Kanada - TSX Venture Exchange - Comstock - 15 Min delay */
	public static String V = "V";
	/** China - B�rse Shanghai - Telekurs Financial - 30 Min delay */
	public static String SS = "SS";
	/** China - B�rse Shenzhen - Telekurs Financial - 30 Min delay */
	public static String SZ = "SZ";
	/** D�nemark - B�rse Kopenhagen - Telekurs Financial - 15 Min delay */
	public static String CO = "CO";
	/** Frankreich - B�rse Paris - Telekurs Financial - 15 Min delay */
	public static String PA = "PA";
	/** Deutschland - B�rse Berlin-Bremen - Telekurs Financial - 15 Min delay */
	public static String BE = "BE";
	/** Deutschland - B�rse D�sseldorf - Telekurs Financial - 15 Min delay */
	public static String DU = "DU";
	/** Deutschland - B�rse Frankfurt - Telekurs Financial - 15 Min delay */
	public static String F = "F";
	/** Deutschland - B�rse Hamburg - Telekurs Financial - 15 Min delay */
	public static String HM = "HM";
	/** Deutschland - B�rse Hanover - Telekurs Financial - 15 Min delay */
	public static String HA = "HA";
	/** Deutschland - B�rse M�nchen - Telekurs Financial - 15 Min delay */
	public static String MU = "MU";
	/** Deutschland - B�rse Stuttgart - Telekurs Financial - 15 Min delay */
	public static String SG = "SG";
	/** Deutschland - XETRA - Telekurs Financial - 15 Min delay */
	public static String DE = "DE";
	/** Hongkong - B�rse Hong Kong - Telekurs Financial - 60 Min delay */
	public static String HK = "HK";
	/** Indien - B�rse Bombay - Comstock - 15 Min delay */
	public static String BO = "BO";
	/** Indien - Nationale B�rse Indien - Telekurs Financial - 15 Min delay */
	public static String NS = "NS";
	/** Indonesien - B�rse Jakarta - Comstock - 10 Min delay */
	public static String JK = "JK";
	/** Irland - Irische B�rse - Telekurs Financial - 15 Min delay */
	public static String IR = "IR";
	/** Israel - B�rse Tel Aviv - Telekurs Financial - 20 Min delay */
	public static String TA = "TA";
	/** Italien - B�rse Mailand - Comstock - 20 Min delay */
	public static String MI = "MI";
	/** S�dkorea - B�rse Korea - Comstock - 20 Min delay */
	public static String KS = "KS";
	/** S�dkorea - KOSDAQ - Comstock - 20 Min delay */
	public static String KQ = "KQ";
	/** Mexiko - B�rse Mexiko - Telekurs Financial - 20 Min delay */
	public static String MX = "MX";
	/** Niederlande - B�rse Amsterdam - Telekurs Financial - 15 Min delay */
	public static String AS = "AS";
	/** Neuseeland - B�rse Neuseeland - Comstock - 20 Min delay */
	public static String NZ = "NZ";
	/** Norwegen - B�rse Oslo - Telekurs Financial - 15 Min delay */
	public static String OL = "OL";
	/** Portugal - B�rse Lissabon - Telekurs Financial - 15 Min delay */
	public static String LS = "LS";
	/** Singapur - B�rse Singapur - Comstock - 20 Min delay */
	public static String SI = "SI";
	/** Spanien - B�rse Barcelona - Telekurs Financial - 15 Min delay */
	public static String BC = "BC";
	/** Spanien - B�rse Bilbao - Telekurs Financial - 15 Min delay */
	public static String BI = "BI";
	/** Spanien - Madrid Fixed Income Market - Telekurs Financial - 15 Min delay */
	public static String MF = "MF";
	/** Spanien - Madrid SE C.A.T.S. - Telekurs Financial - 15 Min delay */
	public static String MC = "MC";
	/** Spanien - B�rse Madrid - Telekurs Financial - 15 Min delay */
	public static String MA = "MA";
	/** Schweden - B�rse Stockholm - Telekurs Financial - 15 Min delay */
	public static String ST = "ST";
	/** Schweiz - Schweizer B�rse - Telekurs Financial - 15 Min delay */
	public static String SW = "SW";
	/** Schweiz - Virt-X - Telekurs Financial - 15 Min delay */
	public static String VX = "VX";
	/** Taiwan - OTC B�rse Taiwan - Comstock - 20 Min delay */
	public static String TWO = "TWO";
	/** Taiwan - B�rse Taiwan - Comstock - 20 Min delay */
	public static String TW = "TW";
	/** Thailand - B�rse Thailand - Comstock - 15 Min delay */
	public static String BK = "BK";
	/** Gro�britannien - B�rse London - Telekurs Financial - 20 Min delay */
	public static String L = "L";

}
