/**
 * 
 */
package model.test;

import model.Quote;
import persistency.controller.NumberController;
import persistency.logging.BaseLogger;
import utilities.Date;
import utilities.DatumException;

/**
 * @author Mathy
 */
public class DummyQuote extends Dummy {

    private static final String[] status = {"O", "P", "C"};
    private static final String[] type = {"Mat", "Uur"};

    /**
     * @return
     */
    public static Quote createQuote(final String cusID, final String addressID) {
        Quote quote = null;
        Date toDay;
        try {
            toDay = new Date();
            toDay.veranderDatum(getRandom().nextInt(30));
            final Date deliveryDay = toDay;

            deliveryDay.setMaand(deliveryDay.getMaand() + 3); // delivery =
            // today + 3
            // months
            quote = new Quote(// new java.rmi.dgc.VMID().toString(),
                    // Integer.toHexString(DummyNumber.getNewNumber()),
                    NumberController.readLastNumber("Qte", new Date().getJaar()).toString(), // idQuote
                    // |
                    // char(15)
                    cusID, // qtecusid | char(15)
                    addressID, // qtedlvaddid | char(15)
                    toDay, // qtecrtdate | datetime
                    Integer.toHexString(1), // qtecrtuserid | char(15)
                    toDay, // qteupddate | datetime
                    Integer.toHexString(1), // qteupduserid | char(15)
                    toDay, // qtereqdlvdate | date
                    status[getRandom().nextInt(status.length)], // qteStatus |
                    // // char(5)
                    type[getRandom().nextInt(type.length)], // qteType | char(5)
                    true, // qteVat | tinyint(1)
                    "Dit is commentaar", true // Active | tinyint(1)
            );
        } catch (final DatumException e) {
            BaseLogger.logMsg(e.getMessage());
        }
        return quote;
    }
}
