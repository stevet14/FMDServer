package org.averni.fmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import de.hdtconsulting.yahoo.finance.YHistoric;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;

public class BuySignalsOld {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BuySignalsOld.class);

	private static CSVWriter writer;
	
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK)); //Start with last Friday's close...
		Date endDate = cal.getTime();
		
		Calendar gc = Calendar.getInstance();
		gc.add(Calendar.WEEK_OF_MONTH, -100);
		Date startDate = gc.getTime();

		TreeMap<String, String> symbols;

		try {
			writer = new CSVWriter(new FileWriter("BuySignals.csv"),
					',', CSVWriter.NO_QUOTE_CHARACTER);

			// Foreign Indices
			System.out.println("Foreign Indices\n");
			symbols = loadSymbols("ForeignIndices.csv");
			findBuySignals(endDate, startDate, symbols);

			// London Stock Exchange
			System.out.println("London Stock Exchanges\n");
			symbols = loadSymbols("LondonStockExchange.csv");
			findBuySignals(endDate, startDate, symbols);

			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void findBuySignals(Date endDate, Date startDate,
			TreeMap<String, String> symbols) {

			Yapi yapi = new Yapi();

			/*
			 * for (String index : symbols.keySet()) { YSymbol symbol = new
			 * YSymbol(index); ArrayList<YHistoric> historics =
			 * yapi.getHistoric(symbol, startDate, endDate,
			 * Yapi.HIST_WEEKLY).getHistorics(); if (historics.size() < 51)
			 * continue; System.out.println(symbol.getCode() + "|" +
			 * symbols.get(index)); String[] entries = new String[2]; entries[0]
			 * = symbol.getCode(); entries[1] = symbols.get(index);
			 * writer.writeNext(entries);
			 */

			for (String index : symbols.keySet()) {
				YSymbol symbol = new YSymbol(index);
				ArrayList<YHistoric> historics = yapi.getHistoric(symbol,
						startDate, endDate, Yapi.HIST_WEEKLY).getHistorics();

				if (historics.size() < 51) {
					System.out.println(index + " - " + symbols.get(index));
					System.out.println("<Doesn't exist>");
					System.out.println();
					continue;
				}
				
				String breakoutSignal = getBreakoutSignal(historics);

				if (breakoutSignal == "Buy") {
					System.out.println(index + " - " + symbols.get(index));
					System.out.println();
					
					//CSV Output
					String[] entries = new String[3];
					entries[0] = symbol.getCode();
					entries[1] = symbols.get(index);
					entries[2] = breakoutSignal;
					writer.writeNext(entries);
				}
			}

	}

	private static String getBreakoutSignal(ArrayList<YHistoric> historics) {
		BigDecimal current40WeekMA = getMovingAverage(historics, 1, 40);
		BigDecimal previous40WeekMA = getMovingAverage(historics, 2, 41);
		BigDecimal close = historics.get(0).getClose();
		BigDecimal high = get12WeekHigh(historics);
		String breakoutSignal = (close.compareTo(current40WeekMA) == 1)
				&& (current40WeekMA.compareTo(previous40WeekMA) == 1)
				&& (close.compareTo(high) == 1) ? "Buy" : "Hold";
		
		if (breakoutSignal == "Buy") {
			System.out.println("Close - " + historics.get(0).getClose()
					+ "| Current 40wMA - " + current40WeekMA
					+ "|  Previous 40wMA - " + previous40WeekMA);
			System.out.println("Breakout Signal: " + breakoutSignal
					+ "    (Previous 12-wk high: " + high + ")");
		}
		return breakoutSignal;
	}

	private static BigDecimal getMovingAverage(ArrayList<YHistoric> historics,
			int startWeek, int weeks) {
		BigDecimal ma = new BigDecimal(0.0);
		for (int i = startWeek - 1; i < weeks; i++) {
			YHistoric historic = historics.get(i);
			ma = ma.add(historic.getClose());
		}
		return ma.divide(new BigDecimal(weeks - startWeek + 1));
	}

	private static TreeMap<String, String> loadSymbols(String file) {
		TreeMap<String, String> symbols = new TreeMap<String, String>();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			CSVReader reader = new CSVReader(br);

			List<String[]> csvdata = reader.readAll();

			for (String[] csvline : csvdata) {
				symbols.put(csvline[0], csvline[1]);
			}
		} catch (Exception e) {
			logger.error("loadSymbols()", e);

			System.out.println(e.getMessage());
		}
		return symbols;
	}

	private static BigDecimal get12WeekHigh(ArrayList<YHistoric> historics) {
		BigDecimal high = new BigDecimal(0.0);
		for (int i = 1; i < 13; i++) {
			YHistoric historic = historics.get(i);
			if (historic.getHigh().compareTo(high) == 1)
				high = historic.getHigh();
		}
		return high;
	}

}
