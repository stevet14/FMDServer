package org.averni.fmd.prices;

import java.util.Calendar;

import org.averni.fmd.domain.Symbol;

public class ForexPrices extends URLPrices {

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

			String urlString = "http://forex.tradingcharts.com/charts/chart_script.php4?sym="
					+ symbol.getSymbol()
					+ "&data=b&tz=GMT&type=b&cs=1&period=1d&defdates=0"
					+ "&bmonth="
					+ startMonth
					+ "&bday="
					+ startDay
					+ "&byear="
					+ startYear
					+ "&bhour=&bmin="
					+ "&emonth="
					+ endMonth
					+ "&eday="
					+ endDay
					+ "&eyear="
					+ endYear
					+ "&ehour=&emin=&Img+Type=png&drsi=0&ma1=0&dmacd=0&ma2=0&bol=0&dstoch=0&Submit=Submit&r=1";

			lines = join(lines, getSymbolDatafromURL(urlString));
		}
		return lines;
	}

	@SuppressWarnings("unchecked")
	String[] join(String[]... arrays) {
		// calculate size of target array
		int size = 0;
		for (String[] array : arrays) {
			size += array.length;
		}

		// create list of appropriate size
		java.util.List<String> list = new java.util.ArrayList(size);

		// add arrays
		for (String[] array : arrays) {
			list.addAll(java.util.Arrays.asList(array));
		}

		// create and return final array
		return list.toArray(new String[size]);
	}
}
