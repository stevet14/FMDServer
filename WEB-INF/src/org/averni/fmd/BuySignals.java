package org.averni.fmd;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.averni.fmd.domain.Price;
import org.averni.fmd.domain.Signal;
import org.averni.fmd.domain.Symbol;
import org.averni.fmd.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import au.com.bytecode.opencsv.CSVWriter;

public class BuySignals {
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(BuySignals.class);

	private static CSVWriter writer;
	private static Session session;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		String buySignals = getBuySignals();
		log.info(buySignals);
		if(true) return;
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		writer = new CSVWriter(new FileWriter("BuySignals.csv"), ',',
				CSVWriter.NO_QUOTE_CHARACTER);

		Query q = session
				.createQuery("from Symbol as symbol where (exchange='LSE' or exchange = 'FUTURES' or exchange = 'FINDEX' or exchange = 'INDEX') order by exchange, symbol")
				;
		
		//Pageing logic (per 1000 results)
		for(int i=0; i<1000000; i+=1000) {
			q.setFirstResult(i);
			q.setMaxResults(1000);
			q.setFetchSize(1000);
			List<Symbol> symbols = q.list();
			if(symbols.size()==0) break; //break once we run out of results...
			for (Symbol symbol : symbols) {
				Set<Price> prices = symbol.getPrices();
				if (prices.size() < 51) {
					continue;
				}

				String breakoutSignal = getBreakoutSignal(prices);

				if (breakoutSignal.equals("Buy")) {
					log.info(symbol.getSymbol() + " - "
							+ symbol.getDescription() + "\n");

					// CSV Output
					String[] entries = new String[3];
					entries[0] = symbol.getSymbol();
					entries[1] = symbol.getDescription();
					entries[2] = breakoutSignal;
					writer.writeNext(entries);					
				}
				session.evict(symbol);
			}
		session.clear();
		}
		writer.close();
		session.close();
	}

	private static String getBreakoutSignal(Set<Price> prices) {
		double current40WeekMA = getMovingAverage(prices, 1, 40);
		double previous40WeekMA = getMovingAverage(prices, 2, 41);
		Price price = prices.iterator().next();
		double close = price.getClose();
		double high = getPrior12WeekHigh(prices);
		String breakoutSignal = (close > current40WeekMA)
				&& (current40WeekMA > previous40WeekMA) && (close > high) ? "Buy"
				: "Hold";

		if (breakoutSignal.equals("Buy")) {
			log.info("Close - " + close + "| Current 40wMA - "
					+ current40WeekMA + "|  Previous 40wMA - "
					+ previous40WeekMA);
			log.info("Breakout Signal: " + breakoutSignal
					+ "    (Previous 12-wk high: " + high + ")");
			
			//Generate signal.
			//TODO...Need to check for pre-existing signals...
			Signal signal = new Signal();
			signal.setSymbol(price.getSymbol());
			signal.setSignalType("Upside Breakout");
			signal.setBuyDate(price.getDate());
			signal.setBuyPrice(price.getClose());
			session.save(signal);
			
			Symbol symbol = price.getSymbol();
			Set<Signal> signals = symbol.getSignals();
			if (signals==null) signals = new HashSet<Signal>();
			signals.add(signal);
			symbol.setSignals(signals);
			session.save(symbol);
			session.flush();
		}
		return breakoutSignal;
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
	
	public static String getBuySignals() {
		String buySignals = "";
		
		//Set scheduled daterange to be last full Monday-to-Friday.
		Calendar cal = Calendar.getInstance();
		
		log.info("Current date: " + cal.getTime());

		if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) 
			cal.add(Calendar.DAY_OF_MONTH, -5);
		else
			cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_WEEK) - 5);
		
		log.info("BuySignals as at: " + cal.getTime());
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		log.info("BuySignals:\n" + buySignals);
		session.beginTransaction();

		List<Signal> signals = session.createCriteria(Signal.class)
			.add( Restrictions.ge("buyDate", cal.getTime()))
	    	.addOrder( Order.desc("symbol.exchange") )
	    	.addOrder( Order.asc("symbol.description") )
	    .list();
		
		for(Signal signal : signals) {
			buySignals.concat(signal.getSymbol().getSymbol() + "," + signal.getSymbol().getDescription()+ "\n");
		}
		log.info("BuySignals:\n" + buySignals);
		return buySignals;
	}
}
