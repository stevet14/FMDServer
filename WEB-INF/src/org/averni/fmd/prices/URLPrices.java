package org.averni.fmd.prices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.averni.fmd.SymbolLoader.Exchange;
import org.averni.fmd.domain.Symbol;

public abstract class URLPrices {

	private static Log log = LogFactory.getLog(URLPrices.class);
	
	public abstract String[] getPrices(Symbol symbol) throws Exception; 

	public static URLPrices getPricesClass(Exchange exchange) {
		switch (exchange) {
		case FOREX:
			return new ForexPrices();
		case FUTURES:
			return new FuturesPrices();
		default:
			return new AlphaAdvantagePrices();
		}
	}
	
	String[] getSymbolDatafromURL(String urlString) throws Exception {
		BufferedReader dis;
		URL url = new URL(urlString);
		URLConnection urlConn = url.openConnection();
		urlConn.setDoInput(true);
		urlConn.setUseCaches(false);
		urlConn
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.64 Safari/532.5");
		dis = new BufferedReader(
				new InputStreamReader(urlConn.getInputStream()));

		String s;
		while ((s = dis.readLine()) != null) {
			if (s.contains("var dataStr"))
				break;
		}
		dis.close();

		if (s == null || !s.contains("|"))
			throw new Exception("Could not find symbol: ");
		// Bug out if we can't grab the data.

		s = s.substring(s.indexOf('\'') + 1, s.lastIndexOf('\''));
		return s.split("\\|");
	}

	public static void checkProxyServer() {
		try {
			if (InetAddress.getLocalHost().getHostAddress().startsWith("10.")) {
				log.debug("Using proxy.");
			} else {
				log.debug("Not using proxy.");
				return;
			}

			System.setProperty("http.proxySet", "true");
			System.setProperty("http.proxyHost", "webproxy.svr.chp.co.uk");
			System.setProperty("http.proxyPort", "3128");

//			YHost proxy = new YHost();
//			proxy.setServer("webproxy.svr.chp.co.uk");
//			proxy.setPort(3128);
//			yapi.setProxy(proxy);

		} catch (UnknownHostException e) {
			System.err.println("Unable to lookup proxy");
		}
	}

}
