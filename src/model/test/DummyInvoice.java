/**
 * 
 */
package model.test;

import model.Invoice;
import persistency.controller.NumberController;
import persistency.logging.BaseLogger;
import utilities.Date;
import utilities.DatumException;

/**
 * @author Mathy
 */
public class DummyInvoice extends Dummy {

    private static final String[] status = {"O", "P", "C"};
    private static final String[] type = {"I", "C"};

    /**
     * @return
     */
    public static Invoice createInvoice(final String cusID, final String addressID) {
        Invoice invoice = null;
        try {
            final Date invDate = new Date();
            final Date invDueDate = new Date(invDate);
            invDueDate.veranderDatum(30);

            invoice = new Invoice(
                    // new java.rmi.dgc.VMID().toString(),
                    // Integer.toHexString(DummyNumber.getNewNumber()), //
                    // idInvoice |
                    // char(15)
                    NumberController.readLastNumber("Inv", new Date().getJaar()).toString(), cusID, // invcusid | char(15)
                    addressID, // invaddid | char(15)
                    addressID, // invdlvaddid | char(15)
                    new Date(), // invcrtdate | datetime
                    Integer.toHexString(1), // invcrtuserid | char(15)
                    new Date(), // invupddate | datetime
                    Integer.toHexString(1), // invupduserid | char(15)
                    invDate, // invdate | date
                    invDueDate, // invduedate | date
                    status[getRandom().nextInt(status.length)], // invStatus |
                    // // char(5)
                    type[getRandom().nextInt(type.length)], // invType | char(5)
                    true, // invVat | tinyint(1)
                    "Default commentaar", true // Active | tinyint(1)
            );
        } catch (final DatumException e) {
            BaseLogger.logMsg(e.getMessage());
        }
        return invoice;
    }
}
