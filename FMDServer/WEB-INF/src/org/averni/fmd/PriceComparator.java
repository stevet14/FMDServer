package org.averni.fmd;

import java.util.Comparator;

import org.averni.fmd.domain.Price;

public class PriceComparator implements Comparator<Price> {

	public int compare(Price price1, Price price2) {
		if (price1.getDate().before(price2.getDate()))
			return 1;
		else if (price1.getDate().after(price2.getDate()))
			return -1;
		else
			return 0;
	}

}
