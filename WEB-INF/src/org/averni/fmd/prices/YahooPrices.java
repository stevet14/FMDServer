package org.averni.fmd.prices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.averni.fmd.domain.Symbol;

import de.hdtconsulting.yahoo.finance.YHistoric;
import de.hdtconsulting.yahoo.finance.YSymbol;
import de.hdtconsulting.yahoo.finance.Yapi;

public class YahooPrices extends URLPrices {

	@Override
	public String[] getPrices(Symbol symbol) throws Exception {
		Calendar gc = Calendar.getInstance();
		Date endDate = gc.getTime();
		gc.add(Calendar.WEEK_OF_MONTH, -100);
		Date startDate = gc.getTime();

		YSymbol ys = new YSymbol(symbol.getSymbol());
		ArrayList<YHistoric> historics = yapi.getHistoric(ys, startDate,
				endDate, Yapi.HIST_WEEKLY).getHistorics();

		if (historics.size() == 0)
			throw new Exception("Could not find symbol: " + ys.getCode());

		String[] lines = new String[historics.size()];
		DateFormat inFormatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outFormatter = new SimpleDateFormat("MM/dd/yy");
		for (YHistoric historic : historics) {
			String line = "";
			Date yapiDate = (Date) inFormatter.parse(historic.getDate());
			line += outFormatter.format(yapiDate) + ",";
			line += historic.getOpen().doubleValue() + ",";
			line += historic.getHigh().doubleValue() + ",";
			line += historic.getLow().doubleValue() + ",";
			line += historic.getClose().doubleValue() + ",";
			lines[historics.indexOf(historic)] = line;
		}
		return lines;
	}

}
