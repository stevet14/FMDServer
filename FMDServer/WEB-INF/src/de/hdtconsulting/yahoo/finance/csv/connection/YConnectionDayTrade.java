package de.hdtconsulting.yahoo.finance.csv.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class YConnectionDayTrade extends AbstractYConnection {

	private static final String HOST = "logtrade.finance.vip.ukl.yahoo.com";

	private static final String PATH = "/lastTrades";

	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private URI getUrl() throws URISyntaxException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("output", "user"));
		qparams.add(new BasicNameValuePair("i", "eu"));
		qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbol));

		URI uri = URIUtils.createURI("http", HOST, PORT, PATH, URLEncodedUtils
				.format(qparams, "UTF-8"), null);

		return uri;

	}

	public InputStream getCsv() throws ClientProtocolException, IOException, URISyntaxException  {
		return getCsv(getUrl());
	}

}
