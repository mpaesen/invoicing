package model.test;

import model.Price;
import persistency.controller.NumberController;
import utilities.Date;
import utilities.DatumException;

import java.math.BigDecimal;

public class DummyPrice extends Dummy {
    private static String[] uMeasure = {"wk", "u", "stk", "md", "dg"};

    /**
     * @param prodID
     * @return
     */
    public static Price createPrice(final String prodID) {
        Price price = null;
        try {
            final Date vanaf = new Date();
            vanaf.veranderDatum(-90);
            price = new Price(
                    // new java.rmi.dgc.VMID().toString(), //idPrice | char(15)
                    // Integer.toHexString(DummyNumber.getNewNumber()),
                    NumberController.readLastNumber("Prc", 0).toString(), prodID, // priProdid | char(15)
                    vanaf, // Prifrom | datetime
                    new BigDecimal(10.0 * getRandom().nextDouble()), // priunit |
                    // decimal(9,2)
                    uMeasure[getRandom().nextInt(uMeasure.length)], // Primeasure
                    // | char(5)
                    true // active | tinyint(1)

            );
        } catch (final DatumException e) {
            e.printStackTrace();
        }
        return price;

    }
}
