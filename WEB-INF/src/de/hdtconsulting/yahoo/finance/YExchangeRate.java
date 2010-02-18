package de.hdtconsulting.yahoo.finance;

/**
 * ExchangeRate
 * @author hdt
 *
 */
public class YExchangeRate {

	private YCurrency fromCurrency;
	
	private YCurrency toCurrency;
	
	public YExchangeRate(YCurrency from, YCurrency to) {
		this.fromCurrency = from;
		this.toCurrency = to;
	}

	@Override
	public int hashCode() {
		String code = this.fromCurrency.getCode() + this.toCurrency.getCode();
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		YExchangeRate rate;
	
		if (o instanceof YExchangeRate) {
			rate = (YExchangeRate) o ;
		} else {
			return false;
		}

		if(!this.fromCurrency.getCode().equals(rate.getFromCurrency().getCode())||!this.toCurrency.getCode().equals(rate.getToCurrency().getCode()) ) {
			return false;
		}
		
		return true;

	}

	public YCurrency getFromCurrency() {
		return fromCurrency;
	}

	public YCurrency getToCurrency() {
		return toCurrency;
	}
	
	public YSymbol getSymbol() {
		YSymbol symbol = new YSymbol(this.fromCurrency.getCode()+this.toCurrency.getCode()+"=X");
		return symbol;
	}

}
