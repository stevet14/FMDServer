package de.hdtconsulting.yahoo.finance.csv.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class AbstractYConnection {

	protected static int PORT = -1;
	
	protected static String PARAM_SYMBOL = "s";

	private HttpHost proxy = null;

	private HttpClient client = new DefaultHttpClient();

	public void setProxy(YHost proxy) {
		this.proxy = new HttpHost(proxy.getServer(), proxy.getPort());
	}

	public void resetProxy() {
		this.proxy = null;
	}
	
	protected InputStream getCsv(URI uri) throws ClientProtocolException, IOException {

		InputStream inputstream = null;
		
		if(this.proxy!=null) {
			this.client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = client.execute(httpget);

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			inputstream = entity.getContent();
		}
		
		return inputstream;
		
	}

}
