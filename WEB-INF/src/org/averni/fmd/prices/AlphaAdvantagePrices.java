package org.averni.fmd.prices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.averni.fmd.domain.Symbol;

	public class AlphaAdvantagePrices extends URLPrices {

	@Override
	public String[] getPrices(Symbol symbol) throws Exception {

		String[] lines = new String[0];

		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		// We need to iterate a few times to get all the data we need...
		for (int i = 1; i < 4; i++) {
			Calendar cal = Calendar.getInstance();
			// Start with last Friday's 'Close'...
			if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY
					&& cal.get(Calendar.DAY_OF_WEEK) < Calendar.SATURDAY)
				cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) - 1);
			else
				cal.add(Calendar.DATE,
					(-cal.get(Calendar.DAY_OF_WEEK) % 7) - 1);

			int endYear = cal.get(Calendar.YEAR);
			String endMonth = monthName[cal.get(Calendar.MONTH)];
			int endDay = cal.get(Calendar.DATE);

			// 144 days is the maximum number of days we can grab so we need to
			// iterate a bit to get enough data
			cal.add(Calendar.DATE, -144 * i);
			// go back...
			int startYear = cal.get(Calendar.YEAR);
			String startMonth = monthName[cal.get(Calendar.MONTH)];
			int startDay = cal.get(Calendar.DATE);

			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY_ADJUSTED&&apikey=JJ9G81WOK1NRAAYY&datatype=csv&symbol="
					+ symbol.getSymbol();
			
			lines = getSymbolDatafromURL(urlString);
		}
		return lines;
	}
	
	@Override
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
		List<String> prices = new ArrayList<String>();
		dis.readLine(); //skip titles line
		while ((s = dis.readLine()) != null) {
			prices.add(s);
		}
		dis.close();

		if ( prices.size() == 0 || prices.get(0).startsWith("{"))
			throw new Exception("Could not find symbol: ");
		// Bug out if we can't grab the data.

		return prices.toArray(new String[prices.size()]);
	}




}
