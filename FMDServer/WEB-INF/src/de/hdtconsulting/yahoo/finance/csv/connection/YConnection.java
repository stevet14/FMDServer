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

public class YConnection extends AbstractYConnection {

	private static String PARAM_FORMAT = "f";

	private static String PARAM_E = "e";

	private String symbols;

	private String format;

	public String getSymbols() {
		return symbols;
	}

	public void setSymbols(String symbols) {
		this.symbols = symbols;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	private URI getUrl() throws URISyntaxException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbols));
		qparams.add(new BasicNameValuePair(PARAM_FORMAT, this.format));
		qparams.add(new BasicNameValuePair(PARAM_E, ".csv"));

		URI uri = URIUtils
				.createURI("http", "quote.yahoo.com", PORT, "/d/quotes.csv",
						URLEncodedUtils.format(qparams, "UTF-8"), null);

		return uri;
	
	}

	public InputStream getCsv() throws ClientProtocolException, IOException, URISyntaxException {
		return getCsv(getUrl());
	}

}
