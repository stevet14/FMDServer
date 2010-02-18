package de.hdtconsulting.yahoo.finance;

/**
 * Currency
 * @author hdt
 *
 */
public class YCurrency {

	private String code;

	public YCurrency(String code) {
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

		YCurrency currency;

		if (o instanceof YCurrency) {
			currency = (YCurrency) o;
		} else {
			return false;
		}

		if (!this.code.equals(currency.getCode())) {
			return false;
		}

		return true;

	}

	/** Afganistan Afghani */
	public static String AFA = "AFA";
	/** Albanian Lek */
	public static String ALL = "ALL";
	/** Algerian Dinar */
	public static String DZD = "DZD";
	/** Argentinian Peso */
	public static String ARS = "ARS";
	/** Aruban Florin */
	public static String AWG = "AWG";
	/** Australian Dollar */
	public static String AUD = "AUD";
	/** Austrian Schilling */
	public static String ATS = "ATS";
	/** Bahraini Dinar */
	public static String BHD = "BHD";
	/** Bangladesh Taka */
	public static String BDT = "BDT";
	/** Barbados Dollar */
	public static String BBD = "BBD";
	/** Belgian Franc */
	public static String BEF = "BEF";
	/** Belize Dollar */
	public static String BZD = "BZD";
	/** Bermuda Dollar */
	public static String BMD = "BMD";
	/** Bhutan Ngultrum */
	public static String BTN = "BTN";
	/** Bolivian Boliviano */
	public static String BOB = "BOB";
	/** Botswana Pula */
	public static String BWP = "BWP";
	/** Brazilian Real */
	public static String BRL = "BRL";
	/** British Pound */
	public static String GBP = "GBP";
	/** Brunei Dollar */
	public static String BND = "BND";
	/** Bulgarian Lev */
	public static String BGN = "BGN";
	/** Cambodian Riel */
	public static String KHR = "KHR";
	/** Canadian Dollar */
	public static String CAD = "CAD";
	/** Cape Verde Escudo */
	public static String CVE = "CVE";
	/** Cayman Islands Dollar */
	public static String KYD = "KYD";
	/** CFA Franc (BCEAO) */
	public static String XOF = "XOF";
	/** CFA Franc(BEAC) */
	public static String XAF = "XAF";
	/** CFP Franc */
	public static String XPF = "XPF";
	/** Chilean Peso */
	public static String CLP = "CLP";
	/** Colombian Peso */
	public static String COP = "COP";
	/** Comoros Franc */
	public static String KMF = "KMF";
	/** Costa Rican Colon */
	public static String CRC = "CRC";
	/** Croatian Kuna */
	public static String HRK = "HRK";
	/** Cuban Peso */
	public static String CUP = "CUP";
	/** Cypriot Pound */
	public static String CYP = "CYP";
	/** Czech Koruna */
	public static String CZK = "CZK";
	/** Danish Krone */
	public static String DKK = "DKK";
	/** Djibouti Franc */
	public static String DJF = "DJF";
	/** Dominican Peso */
	public static String DOP = "DOP";
	/** Dutch Guilder */
	public static String NLG = "NLG";
	/** East Caribbean Dollar */
	public static String XCD = "XCD";
	/** Egyptian Pound */
	public static String EGP = "EGP";
	/** El Salvador Colon */
	public static String SVC = "SVC";
	/** Estonian Kroon */
	public static String EEK = "EEK";
	/** Ethiopian Birr */
	public static String ETB = "ETB";
	/** Euro */
	public static String EUR = "EUR";
	/** Fiji Dollar */
	public static String FJD = "FJD";
	/** Finnish Markka */
	public static String FIM = "FIM";
	/** French Franc */
	public static String FRF = "FRF";
	/** Gambia Dalasi */
	public static String GMD = "GMD";
	/** German Mark */
	public static String DEM = "DEM";
	/** Ghanaian Cedi */
	public static String GHC = "GHC";
	/** Gibraltar Pound */
	public static String GIP = "GIP";
	/** Greek Drachma */
	public static String GRD = "GRD";
	/** Guatemala Quetzal */
	public static String GTQ = "GTQ";
	/** Guinea Franc */
	public static String GNF = "GNF";
	/** Guyana Dollar */
	public static String GYD = "GYD";
	/** Haitian Gourde */
	public static String HTG = "HTG";
	/** Honduras Lempira */
	public static String HNL = "HNL";
	/** Hong Kong Dollar */
	public static String HKD = "HKD";
	/** Hungarian Forint */
	public static String HUF = "HUF";
	/** Iceland Krona */
	public static String ISK = "ISK";
	/** Indian Rupee */
	public static String INR = "INR";
	/** Indonesian Rupiah */
	public static String IDR = "IDR";
	/** Irish Punt */
	public static String IEP = "IEP";
	/** Israeli Shekel */
	public static String ILS = "ILS";
	/** Italian Lira */
	public static String ITL = "ITL";
	/** Jamaican Dollar */
	public static String JMD = "JMD";
	/** Japanese Yen */
	public static String JPY = "JPY";
	/** Jordanian Dinar */
	public static String JOD = "JOD";
	/** Kenyan Shilling */
	public static String KES = "KES";
	/** Kuwaiti Dinar */
	public static String KWD = "KWD";
	/** Laos Kip */
	public static String LAK = "LAK";
	/** Latvian Lats */
	public static String LVL = "LVL";
	/** Lebanese Pound */
	public static String LBP = "LBP";
	/** Lesotho Loti */
	public static String LSL = "LSL";
	/** Lithuanian Litas */
	public static String LTL = "LTL";
	/** Malagasy Franc */
	public static String MGF = "MGF";
	/** Malawi Kwacha */
	public static String MWK = "MWK";
	/** Malaysian Ringgit */
	public static String MYR = "MYR";
	/** Maldives Rufiyan */
	public static String MVR = "MVR";
	/** Maltese Pound */
	public static String MTL = "MTL";
	/** Mauritania Ouguiya */
	public static String MRO = "MRO";
	/** Mauritius Rupee */
	public static String MUR = "MUR";
	/** Mexican Peso */
	public static String MXN = "MXN";
	/** Mongolian Tugrik */
	public static String MNT = "MNT";
	/** Moroccan Dirham */
	public static String MAD = "MAD";
	/** Mozambique Metical */
	public static String MZM = "MZM";
	/** Myanmar Kyat */
	public static String MMK = "MMK";
	/** Namibian Dollar */
	public static String NAD = "NAD";
	/** Nepal Rupee */
	public static String NPR = "NPR";
	/** Netherlands Antilles Guilder */
	public static String ANG = "ANG";
	/** New Zealand Dollar */
	public static String NZD = "NZD";
	/** Nicaraguan Cordoba */
	public static String NIO = "NIO";
	/** Nigerian Naira */
	public static String NGN = "NGN";
	/** Norwegian Krone */
	public static String NOK = "NOK";
	/** Oman Rial */
	public static String OMR = "OMR";
	/** Pakistani Rupee */
	public static String PKR = "PKR";
	/** Papua New Guinea Kina */
	public static String PGK = "PGK";
	/** Peruvian Sol */
	public static String PEN = "PEN";
	/** Philippines Peso */
	public static String PHP = "PHP";
	/** Polish Zloty */
	public static String PLN = "PLN";
	/** Portuguese Escudo */
	public static String PTE = "PTE";
	/** Qatari Rial */
	public static String QAR = "QAR";
	/** Renmimbi Yuan */
	public static String CNY = "CNY";
	/** Romanian Leu */
	public static String ROL = "ROL";
	/** Russian Ruble */
	public static String RUB = "RUB";
	/** Salomon Islands Dollar */
	public static String SBD = "SBD";
	/** Sao Tome & Principe Dobra */
	public static String STD = "STD";
	/** Saudi Arabian Riyal */
	public static String SAR = "SAR";
	/** Seychelles Rupee */
	public static String SCR = "SCR";
	/** Sierra Leone Leone */
	public static String SLL = "SLL";
	/** Singapore Dollar */
	public static String SGD = "SGD";
	/** Slovak Koruna */
	public static String SKK = "SKK";
	/** Slovenian Tolar */
	public static String SIT = "SIT";
	/** South African Rand */
	public static String ZAR = "ZAR";
	/** South Korean Won */
	public static String KRW = "KRW";
	/** Spanish Peseta */
	public static String ESP = "ESP";
	/** Sri Lanka Rupee */
	public static String LKR = "LKR";
	/** St. Helena Pound */
	public static String SHP = "SHP";
	/** Sudanese Dinar */
	public static String SDD = "SDD";
	/** Surinam Guilder */
	public static String SRG = "SRG";
	/** Swaziland Lilangeni */
	public static String SZL = "SZL";
	/** Swedish Krona */
	public static String SEK = "SEK";
	/** Swiss Franc */
	public static String CHF = "CHF";
	/** Syria Pound */
	public static String SYP = "SYP";
	/** Taiwan New Dollar */
	public static String TWD = "TWD";
	/** Tanzanian Shilling */
	public static String TZS = "TZS";
	/** Thai Baht */
	public static String THB = "THB";
	/** Tonga Isl Pa’anga */
	public static String TOP = "TOP";
	/** Trinidad Dollar */
	public static String TTD = "TTD";
	/** Tunisian Dinar */
	public static String TND = "TND";
	/** Turkish Lira */
	public static String TRL = "TRL";
	/** Ugandan Shilling */
	public static String UGX = "UGX";
	/** Ukraine Hryvnia */
	public static String UAH = "UAH";
	/** United Arab Emirates Dirham */
	public static String AED = "AED";
	/** US Dollar */
	public static String USD = "USD";
	/** Vanuatu Vatu */
	public static String VUV = "VUV";
	/** Venezuelan Bolivar */
	public static String VEB = "VEB";
	/** Vietnam Dong */
	public static String VND = "VND";
	/** Western Samoa Tala */
	public static String WST = "WST";
	/** Zambia Kwacha */
	public static String ZMK = "ZMK";
	/** Zimbabwean Dollar */
	public static String ZWD = "ZWD";

}
