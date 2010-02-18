package de.hdtconsulting.yahoo.finance.csv.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class YConnectionHistoric extends AbstractYConnection {

	private static final String PATH = "table.csv";

	private static final String HOST = "ichart.finance.yahoo.com";

	private static String PARAM_START_MONTH = "a";

	private static String PARAM_START_DAY = "b";

	private static String PARAM_START_YEAR = "c";

	private static String PARAM_END_MONTH = "d";

	private static String PARAM_END_DAY = "e";

	private static String PARAM_END_YEAR = "f";

	private static String PARAM_INTERVAL = "g"; // (d=daily, w=weekly,
	// m=monthly, v=dividend
	// distribution)

	private Date startDate;

	private Date endDate;

	private String interval;

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private URI getUrl() throws MalformedURLException, URISyntaxException {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();

		qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbol));

		// start date
		qparams.add(new BasicNameValuePair(PARAM_START_DAY, ""
				+ this.startDate.getDate()));
		qparams.add(new BasicNameValuePair(PARAM_START_MONTH, ""
				+ this.startDate.getMonth()));
		qparams.add(new BasicNameValuePair(PARAM_START_YEAR, ""
				+ (this.startDate.getYear() + 1900)));
		// end date
		qparams.add(new BasicNameValuePair(PARAM_END_DAY, ""
				+ this.endDate.getDate()));
		qparams.add(new BasicNameValuePair(PARAM_END_MONTH, ""
				+ this.endDate.getMonth()));
		qparams.add(new BasicNameValuePair(PARAM_END_YEAR, ""
				+ (this.endDate.getYear() + 1900)));

		qparams.add(new BasicNameValuePair(PARAM_INTERVAL, this.interval));

		URI uri = URIUtils
				.createURI("http", HOST, PORT, PATH,
						URLEncodedUtils.format(qparams, "UTF-8"), null);

		return uri;

	}

	public InputStream getCsv() throws ClientProtocolException, IOException, URISyntaxException  {
		return getCsv(getUrl());
	}

}
