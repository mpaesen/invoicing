package model.test;

import persistency.controller.NumberController;
import model.Contact;

public class DummyContact extends Dummy {

	private static String[] name = { "Achten", "Leppens", "Brebels", "Paesen",
			"Coninckx", "Juncker", "Vandenbussche", "Vandender", "Cortens",
			"Gerits", "Aendekerk", "Stassen", "Vangrootloon", "Jehoul",
			"Casters", "Franckaerts", "Kelchtermans", "Simons", "Ankersmidt",
			"Styven", "Eerdekens", "Vanoosterhout", "Vliegen", "Langens" };

	private static String[] fname = { "Jef", "Lowie", "Jaak", "Rudy", "Erik",
			"Fred", "Chris", "Nancy", "Steffi", "Charlotte", "Steven",
			"Ann-Sophie", "Rudolf", "Patrick", "Francois", "Gert", "Dirk",
			"Luc", "Niko", "Peggy" };

	private static String[] language = { "Nl", "Fr", "De", "En" };
	private static String[] title = { "Dr.", "Hertog", "Baron", "Prof.", "Ing",
			"Ir", "Lic" };
	private static String[] prefix = { "Mr.", "Mw.", "Juf.", "Jhr." };

	public static Contact createContact(String idCus) {
		String lName = name[getRandom().nextInt(name.length)];
		String fName = fname[getRandom().nextInt(fname.length)];
		return new Contact(//new java.rmi.dgc.VMID().toString(), // this.idContact
				//Integer.toHexString(DummyNumber.getNewNumber()),	// = idContact;
				NumberController.readLastNumber("Cnt", 0).toString(),
				idCus, // this.idCus = idCus;
				lName, // this.conLName = conLName;
				fName, // this.conFName = conFName;
				"0"
						+ (Dummy.PHONE_ZONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_ZONE_MAXIMUM))
						+ "/"
						+ (Dummy.PHONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_MAXIMUM)), // this.conPhone =
														// conPhone;
				"0"
						+ (Dummy.MOBILE_PROVIDER_MINIMUM + getRandom().nextInt(
								Dummy.MOBILE_PROVIDER_MAXIMUM))
						+ "/"
						+ (Dummy.PHONE_MINIMUM + getRandom().nextInt(
								Dummy.PHONE_MAXIMUM)), // this.conMobile =
														// conMobile;
				fName + "@gmail.com", // this.conEMail = conEMail;
				title[getRandom().nextInt(title.length)], // this.conTitle =
															// conTitle;
				prefix[getRandom().nextInt(prefix.length)], // this.conPref =
															// conPref;
				language[getRandom().nextInt(language.length)], // this.conLang
																// = conLang;
				true // this.active = active;
		);
	}
}
