package model;

import java.math.BigDecimal;
import java.util.Locale;

import utilities.Constants;

public class Amount implements Business {
	private BigDecimal amount;
	
	public Amount(double amount){
		this.amount = new BigDecimal(amount);
	}
	public Amount(BigDecimal amount){
		this.amount = amount;
	}
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public void add(Amount amount){
		this.amount = this.amount.add(amount.getAmount());
	}
	
	public String toString(){
		StringBuilder bldr = new StringBuilder();
		bldr.append(Constants.EURO);
		bldr.append(String.format(Locale.FRANCE, "%,.2f", amount));
		
		return bldr.toString();
	}
}
