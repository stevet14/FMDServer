package org.averni.fmd;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.averni.fmd.domain.Price;
import org.averni.fmd.domain.Signal;
import org.averni.fmd.domain.Symbol;
import org.averni.fmd.fit.FitLine;
import org.averni.fmd.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class RegressionAnalysis {
	private static final Logger log = Logger
			.getLogger(RegressionAnalysis.class);

	private static Session session;
	private static Signal signal;

	public static void main(String[] args) throws IOException {

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("from Symbol as symbol where exchange = 'FUTURES'");
		// .createQuery("from Symbol as symbol where symbol.symbol = 'BGS.L'");

		// Pageing logic (per 1000 results)
		for (int i = 0; i < 1000000; i += 1000) {
			q.setFirstResult(i);
			q.setMaxResults(1000);
			q.setFetchSize(1000);
			List<Symbol> symbols = q.list();
			if (symbols.size() == 0)
				break; // break once we run out of results...
			for (Symbol symbol : symbols) {
				Set<Price> prices = symbol.getPrices();
				if (prices.size() < 51) {
					continue;
				}
				String signal = "Sold";
				Price[] pricesArray = new Price[prices.size()];
				prices.toArray(pricesArray);
				for (int index = prices.size() - 41; index >= 0; index--) {
					Set<Price> pricesSubSet = new TreeSet<Price>(
							new PriceComparator());
					for (int j = index; j <= index + 40; j++) {
						pricesSubSet.add(pricesArray[j]);
					}
					if (signal.equals("Bought")) {
						String sellSignal = getSellSignal(pricesSubSet);
						if (sellSignal.equals("Sell")) {
							signal = "Sold";
						}
					}
					if (signal.equals("Sold")) {
						String breakoutSignal = getBreakoutSignal(pricesSubSet);
						if (breakoutSignal.equals("Buy")) {
							signal = "Bought";
						}
					}
				}
				session.evict(symbol);
			}
			session.clear();
		}
		session.close();
	}

	private static String getBreakoutSignal(Set<Price> prices) {
		double current40WeekMA = getMovingAverage(prices, 1, 40);
		double previous40WeekMA = getMovingAverage(prices, 2, 41);
		Price price = prices.iterator().next();
		double close = price.getClose();
		double high = getPrior12WeekHigh(prices);
		double gradient = getGradient(prices);
		String breakoutSignal = (close > current40WeekMA)
				&& (current40WeekMA > previous40WeekMA) && (close > high) && gradient > 15
				? "Buy"	: "Hold";

		if (breakoutSignal.equals("Buy")) {
			log.info("Symbol - " + price.getSymbol().getSymbol() + " - " + price.getSymbol().getDescription());
			log.info("Date - " + price.getDate() + "| Close - " + close
					+ "| Current 40wMA - " + current40WeekMA
					+ "|  Previous 40wMA - " + previous40WeekMA);
			log.info("Gradient - " + gradient + "%");
			log.info("Breakout Signal: " + breakoutSignal
					+ "    (Previous 12-wk high: " + high + ")");

			// Generate signal.
			// TODO...Need to check for pre-existing signals...
			signal = new Signal();
			signal.setSymbol(price.getSymbol());
			signal.setSignalType("Upside Breakout");
			signal.setBuyDate(price.getDate());
			signal.setBuyPrice(price.getClose());
			session.save(signal);

			Symbol symbol = price.getSymbol();
			Set<Signal> signals = symbol.getSignals();
			if (signals == null)
				signals = new HashSet<Signal>();
			signals.add(signal);
			symbol.setSignals(signals);
			session.save(symbol);
			session.flush();
		}
		return breakoutSignal;
	}

	private static double getGradient(Set<Price> prices) {
		// Fit points to a straight line. Use constant error.
		double[] fitParams = new double[4];
		double[] x = new double[prices.size()];
		double[] y = new double[prices.size()];
		Price[] pricesArray = new Price[prices.size()];
		prices.toArray(pricesArray);
		for (int i=0; i<prices.size(); i++) {
			Price price = pricesArray[i];
			x[i] = price.getDate().getTime() / 86400000; //Convert date into days since 1970...
			y[i] = price.getClose();
		}
		FitLine.fit(fitParams, x, y, null, null, prices.size());
		double deltaY = (x[0] - x[prices.size()-1]) * fitParams[1];
		double y1 = (fitParams[1] * x[prices.size()-1]) + fitParams[0];
		double gradient = deltaY * 100 / y1;
		return gradient;
	}

	private static String getSellSignal(Set<Price> prices) {
		double current40WeekMA = getMovingAverage(prices, 1, 40);
		double previous40WeekMA = getMovingAverage(prices, 2, 41);
		Price price = prices.iterator().next();
		double close = price.getClose();
		String sellSignal = (close < current40WeekMA)
				|| (current40WeekMA < previous40WeekMA) ? "Sell" : "Hold";

		if (sellSignal.equals("Sell")) {
			log.info("Date - " + price.getDate() + "| Close - " + close
					+ "| Current 40wMA - " + current40WeekMA
					+ "|  Previous 40wMA - " + previous40WeekMA);
			log.info("Sell Signal: " + sellSignal);

			// Update signal.
			signal.setSellDate(price.getDate());
			signal.setSellPrice(price.getClose());
			session.update(signal);

			Symbol symbol = price.getSymbol();
			Set<Signal> signals = symbol.getSignals();
			if (signals == null)
				signals = new HashSet<Signal>();
			signals.add(signal);
			symbol.setSignals(signals);
			session.save(symbol);
			session.flush();
		}
		return sellSignal;
	}

	private static double getMovingAverage(Set<Price> prices, int startWeek,
			int weeks) {
		double ma = 0;
		Iterator<Price> it = prices.iterator();
		for (int i = 1; i < startWeek; i++) {
			it.next();
		}
		for (int i = startWeek - 1; i < weeks; i++) {
			Price price = it.next();
			ma += price.getClose();
		}
		return ma / (weeks - startWeek + 1);
	}

	private static double getPrior12WeekHigh(Set<Price> prices) {
		double high = 0;
		Iterator<Price> it = prices.iterator();
		it.next(); // skip 'this week'
		for (int i = 1; i <= 12; i++) {
			Price price = it.next();
			if (price.getHigh() > high)
				high = price.getHigh();
		}
		return high;
	}

}
