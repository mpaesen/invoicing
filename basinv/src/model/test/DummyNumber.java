package model.test;

import model.Number;
import utilities.Date;
import utilities.DatumException;

public class DummyNumber extends Dummy
{
	// private static int number;
	// public static int getNewNumber(){
	// return ++number;
	// }
	private static String[] category = { "Inv", "Qte" };

	public static Number createNumber()
	{
		Date current;
		Integer year = 0;
		try
		{
			current = new Date();
			year = current.getJaar();
		}
		catch (final DatumException e)
		{
			e.printStackTrace();
		}
		return new Number(category[getRandom().nextInt(category.length)], // nbrCategory
				// |
				// char(5)
				year, // year | int(4,0)
				year + "00000", // nbrStrValue | char(15)
				"", // nbrLstValue | char(15)
				true // active | boolean
		);

	}

}
