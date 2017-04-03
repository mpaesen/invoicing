package model;

import java.math.BigDecimal;
import java.util.Locale;

import utilities.Constants;

public class Amount implements Business
{
	private BigDecimal amount;

	public Amount(final double amount)
	{
		this.amount = new BigDecimal(amount);
	}

	public Amount(final BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(final BigDecimal amount)
	{
		this.amount = amount;
	}

	public void add(final Amount amount)
	{
		if (amount.getAmount() != null)
		{
			this.amount = this.amount.add(amount.getAmount());
		}
	}

	@Override
	public String toString()
	{
		final StringBuilder bldr = new StringBuilder();
		bldr.append(Constants.EURO);
		bldr.append(String.format(Locale.FRANCE, "%,.2f", amount));

		return bldr.toString();
	}
}
