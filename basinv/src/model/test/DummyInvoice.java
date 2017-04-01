/**
 * 
 */
package model.test;

import utilities.Date;
import utilities.DatumException;

import persistency.controller.NumberController;

import model.Invoice;

/**
 * @author Mathy
 * 
 */
public class DummyInvoice extends Dummy {

	private static String[] status = { "O", "P", "C" };
	private static String[] type = { "M", "W" };

	/**
	 * @return
	 */
	public static Invoice createInvoice(String cusID, String addressID) {
		Invoice invoice = null;
		try {
			
			invoice = new Invoice(// new java.rmi.dgc.VMID().toString(),
					//Integer.toHexString(DummyNumber.getNewNumber()), // idInvoice |
																		// char(15)
					NumberController.readLastNumber("Inv", 2011).toString(),
					cusID, // invcusid | char(15)
					addressID, // invaddid | char(15)
					addressID, // invdlvaddid | char(15)
					new Date(), // invcrtdate | datetime
					Integer.toHexString(1), // invcrtuserid | char(15)
					new Date(), // invupddate | datetime
					Integer.toHexString(1), // invupduserid | char(15)
					new Date(), // invdate | date
					new Date(), // invduedate | date
					status[getRandom().nextInt(status.length)], // invStatus | // char(5)
					type[getRandom().nextInt(type.length)], // invType | char(5)
					true, // invVat | tinyint(1)
					"Default commentaar",
					true // Active | tinyint(1)
			);
		} catch (DatumException e) {
			e.printStackTrace();
		}
		return invoice;
	}
}
