package org.averni.fmd.prices;

import org.averni.fmd.domain.Symbol;

public class FuturesPrices extends URLPrices {

	@Override
	public String[] getPrices(Symbol symbol) throws Exception {
		String urlString = "http://futures.tradingcharts.com/chart/"
				+ symbol.getSymbol()
				+ "/W/?saveprefs=t&xshowdata=t&xCharttype=b&xhide_specs=f&xhide_analysis=f&xhide_survey=f&xhide_news=f";

		return getSymbolDatafromURL(urlString);
	}

}
