package org.averni.fmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Scraper {

	private static CSVWriter writer;

	public static void main(String[] args) throws IOException {

		TreeMap<String, String> symbols;
		String urlString;
		// Futures
		System.out.println("Futures\n");
		symbols = loadSymbols("Futures.csv");
		for (String symbol : symbols.keySet()) {
			urlString = "http://futures.tradingcharts.com/chart/"
					+ symbol
					+ "/W/?saveprefs=t&xshowdata=t&xCharttype=b&xhide_specs=f&xhide_analysis=f&xhide_survey=f&xhide_news=f";
			writer = new CSVWriter(new FileWriter(symbol // + "-" +
					// name
					+ ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);

			getData(symbol, symbols.get(symbol), urlString);
			writer.close();
		}

		// Forex
		System.out.println("Forex\n");
		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		symbols = loadSymbols("Forex.csv");
		for (String symbol : symbols.keySet()) {
			writer = new CSVWriter(new FileWriter(symbol // + "-" +
					// name
					+ ".csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);

			for (int i = 1; i < 4; i++) {

				Calendar cal = Calendar.getInstance();
				//Start with last Friday's close...
				cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK));

				int endYear = cal.get(Calendar.YEAR);
				String endMonth = monthName[cal.get(Calendar.MONTH)];
				int endDay = cal.get(Calendar.DATE);

				cal.add(Calendar.DATE, -144 * i); // 144 days is the maximum
													// number
				// we can
				// go back...
				int startYear = cal.get(Calendar.YEAR);
				String startMonth = monthName[cal.get(Calendar.MONTH)];
				int startDay = cal.get(Calendar.DATE);

				urlString = "http://forex.tradingcharts.com/charts/index.php?sym="
						+ symbol
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
						+ "&ehour=&emin=&Img+Type=png&drsi=0&ma1=0&dmacd=0&ma2=0&bol=0&dstoch=0&Submit=Submit";
				// System.out.println("URL: " + urlString);
				getData(symbol, symbols.get(symbol), urlString);
			}
			writer.close();
		}
	}

	private static List<String> getFileList(File path) {
		String[] files = path.list();
		List<String> prices = new ArrayList<String>();
		for (String file : files) {
			if (file.contains(".csv")) {
				prices.add(file);
				// System.out.println("Found: " + file);
			}
		}
		return prices;
	}

	private static void getData(String symbol, String name, String urlString) {
		try {
			URL url;
			URLConnection urlConn;
			BufferedReader dis;

			url = new URL(urlString);
			urlConn = url.openConnection();
			urlConn.setDoInput(true);
			urlConn.setUseCaches(false);
			urlConn
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.64 Safari/532.5");

			dis = new BufferedReader(new InputStreamReader(urlConn
					.getInputStream()));
			String s;

			boolean isForex = false;
			while ((s = dis.readLine()) != null) {
				if (s.contains("<meta name=\"description\"")) {
					int start = s.indexOf("content");
					int end = s.lastIndexOf(" -"); // Assume its a Future...
					if (end == -1) {
						end = s.lastIndexOf(" ..."); // ...if not, it's a Forex.
						start += 26;
						isForex = true;
					}
					name = s.substring(start + 9, end);
				}
				if (s.contains("var dataStr"))
					break;
			}
			dis.close();

			if (s == null || !s.contains("|"))
				return; // Bug out if we can't grab the data...

			s = s.substring(s.indexOf('\'') + 1, s.lastIndexOf('\''));
			// System.out.println(s);
			String[] lines = s.split("\\|");

			if (isForex) { // We need to extract weekly entries from daily
				// events
				String[] weekly = new String[lines.length / 7];
				for (int i = lines.length - 1; i >= lines.length % 7; i -= 7) {
					String line = lines[i];
					weekly[weekly.length - ((i + 1) / 7)] = line;
				}
				lines = weekly;
			}

			for (String line : lines) {
				String[] entries = line.split(",");
				writer.writeNext(entries);
			}
			System.out.println("Found: " + symbol + " - " + name);
		}

		catch (MalformedURLException mue) {
		} catch (IOException ioe) {
		}

	}

	private static TreeMap<String, String> loadSymbols(String file) {
		TreeMap<String, String> symbols = new TreeMap<String, String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			CSVReader reader = new CSVReader(br);
			reader.readNext(); // Skip the headers line...
			List<String[]> csvdata = reader.readAll();

			for (String[] csvline : csvdata) {
				symbols.put(csvline[1], csvline[2]);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return symbols;
	}

}