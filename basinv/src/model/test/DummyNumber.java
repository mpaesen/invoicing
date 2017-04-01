package model.test;

import model.Number;


public class DummyNumber extends Dummy{
	// private static int number;
	// public static int getNewNumber(){
	// return ++number;
	// }
	private static String[] category = {"Inv", "Qte"};
	 
	public static Number createNumber() {
		String year = "2011";
		return new Number(category[getRandom().nextInt(category.length)], // nbrCategory | char(5)
				new Integer(year), // year | int(4,0)
				year +"00000", // nbrStrValue | char(15)
				"", // nbrLstValue | char(15)
				true // active | boolean
		);
	}
	
}
