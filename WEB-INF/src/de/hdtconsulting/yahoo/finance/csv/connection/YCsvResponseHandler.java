package de.hdtconsulting.yahoo.finance.csv.connection;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class YCsvResponseHandler implements ResponseHandler<String> {

	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {

		String result = null;

		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity);
		}

		return result;

	}

}
