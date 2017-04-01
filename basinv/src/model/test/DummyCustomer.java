package model.test;

import model.Customer;
import persistency.controller.NumberController;

public class DummyCustomer extends Dummy {

	private static String[] name = { "Axor", "Hulsbosch", "ACD", "G.Heyninkx",
			"Dethier", "Breka", "Reko", "Cladding", "M.E. Construct",
			"Siersteenfabriek", "Aendekerk", "Stassen", "Intermetal",
			"Eddy Jehoul", "Casters", "Franckaerts", "Gerrits", "Edibo",
			"Ankersmidt", "Sotec", "Tancer Constructie", "Limota", "Keramo",
			"Langens" };

	public static String[] type = { "C", "P", "S" };
	public static String[] cclass = { "A", "B", "C", "D" };
	public static String[] language = { "Nl", "Fr", "De", "En", "It"};
	public static String[] currency = { "Eur", "Gbp", "Chf", "Usd" };
	public static String[] payment = { "30d", "60d", "90d", "cnt" };

	public static Customer createCustomer() {
		String lName = name[getRandom().nextInt(name.length)];
		return new Customer(
				//new java.rmi.dgc.VMID().toString(), // this.idCus =
				//Integer.toHexString(DummyNumber.getNewNumber()),												// idCus;
				NumberController.readLastNumber("Cus", 0).toString(),
				lName, // this.cusName = cusName;
				"BE0"
						+ (Dummy.VAT_MINIMUM + getRandom().nextInt(
								Dummy.VAT_LENGTH)), // this.cusVat = cusVat;
				"0"
						+ (Dummy.PHONE_ZONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_ZONE_MAXIMUM))
						+ "/"
						+ (Dummy.PHONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_MAXIMUM)), // this.cusPhone =
														// cusPhone;
				"0"
						+ (Dummy.MOBILE_PROVIDER_MINIMUM + getRandom().nextInt(
								Dummy.MOBILE_PROVIDER_MAXIMUM))
						+ "/"
						+ (Dummy.PHONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_MAXIMUM)), // this.cusMobile =
														// cusMobile;
				"0"
						+ (Dummy.PHONE_ZONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_ZONE_MAXIMUM))
						+ "/"
						+ (Dummy.PHONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_MAXIMUM)), // this.cusFax = cusFax;
				"info@" + lName + ".be", // this.cusEMail = cusEMail;
				"http://www." + lName + ".be", // this.cusWebsite = cusWebsite;
				type[getRandom().nextInt(type.length)], // this.cusType =
														// cusType;
				cclass[getRandom().nextInt(cclass.length)], // this.cusClass =
															// cusClass;
				language[getRandom().nextInt(language.length)], // this.cusLang
																// = cusLang;
				currency[getRandom().nextInt(currency.length)], // this.cusCur =
																// cusCur;
				payment[getRandom().nextInt(payment.length)], // this.cusCur =
				"Services",
				"BE40652820977063",
				"Info",
				true // this.active = active;
		);
	}
}
