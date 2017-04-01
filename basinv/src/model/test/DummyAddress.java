package model.test;

import persistency.controller.NumberController;
import model.Address;

public class DummyAddress extends Dummy {

	private static String[] street = { "Riekskensweg", "Dorpstraat",
			"Hoogstraat", "Achter\'t heukske", "Tessenstraat",
			"Bondgenotenlaan", "Diestsestraat", "Meeuwenlaan",
			"Weg op bocholt", "Brusselsesteenweg", "Mechelsesteenweg",
			"Gemeentestraat", "Weg op bree", "Weg naar genk", "Peerderstraat",
			"Diestesteenweg"};
	
	private static String[] city = {"Bree", "Meeuwen", "Brussel", "Leuven", "Antwerpen", "Genk", "Hasselt", "Gent"};
	private static String[] zip = {"3960", "3670", "1000", "3000", "2000", "3600", "3500", "9000"};
	
	private static String[] type = {"I","D","O"};


	public static Address createAddress(String addRef) {
		int i = getRandom().nextInt(city.length);
		return new Address(//new java.rmi.dgc.VMID().toString(), 	// this.idAddress
				//Integer.toHexString(DummyNumber.getNewNumber()),
				NumberController.readLastNumber("Add", 0).toString(),
				addRef, 										// this.addRef = addRef;
				street[getRandom().nextInt(street.length)],		// this.addStreet = addStreet;
				""+getRandom().nextInt(999),						// this.addNumber = addNumber;
				""+getRandom().nextInt(20),						// this.addBox = addBox;
				zip[i],											// this.addZip = addZip;
				city[i],										// this.addCity = addCity;
				"België",
				type[getRandom().nextInt(type.length)],			// this.addType = addType;
				true // this.active = active;
		);
	}
}
