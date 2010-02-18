package org.averni.fmd;

import org.averni.fmd.SymbolLoader.Exchange;
import org.averni.fmd.util.HibernateUtil;

public class SymbolManager {

	public static void main(String[] args) throws Exception {
		HibernateUtil.getSessionFactory().getCurrentSession();

		SymbolManager mgr = new SymbolManager();
		mgr.loadAndStoreSymbols();

		HibernateUtil.getSessionFactory().close();
	}

	private void loadAndStoreSymbols() {
		SymbolLoader sl = new SymbolLoader();

/*		System.out.println("\nExchange: " + Exchange.AMEX + "\n");
		sl.loadSymbols(Exchange.AMEX);
		System.out.println("\nExchange: " + Exchange.CVE + "\n");
		sl.loadSymbols(Exchange.CVE);
*/		System.out.println("\nExchange: " + Exchange.FINDEX + "\n");
		sl.loadSymbols(Exchange.FINDEX);
		System.out.println("\nExchange: " + Exchange.INDEX + "\n");
		sl.loadSymbols(Exchange.INDEX);
		System.out.println("\nExchange: " + Exchange.LSE + "\n");
		sl.loadSymbols(Exchange.LSE);
/*		System.out.println("\nExchange: " + Exchange.NASDAQ + "\n");
		sl.loadSymbols(Exchange.NASDAQ);
		System.out.println("\nExchange: " + Exchange.NYSE + "\n");
		sl.loadSymbols(Exchange.NYSE);
		System.out.println("\nExchange: " + Exchange.TSX + "\n");
		sl.loadSymbols(Exchange.TSX);
*/		System.out.println("\nExchange: " + Exchange.FOREX + "\n");
		sl.loadSymbols(Exchange.FOREX);
		System.out.println("\nExchange: " + Exchange.FUTURES + "\n");
		sl.loadSymbols(Exchange.FUTURES);
	}

}
