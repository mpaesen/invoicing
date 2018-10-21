package model.test;

import model.InvoiceDetail;
import model.Price;
import persistency.controller.PriceController;
import utilities.Date;
import utilities.DatumException;

import java.math.BigDecimal;
import java.util.List;

public class DummyInvoiceDetail extends Dummy {
    private static String[] uMeasure = {"wk", "u", "stk", "md", "dg"};
    private static String[] vat = {new String("0.0"), new String("6.0"), new String("12.0"), new String("21.0")};
    private static int i;

    /**
     * @param prodID
     * @return
     */
    public static InvoiceDetail createInvoiceDetail(final String InvoiceID, final String prodID) {
        List<Price> prices = null;
        try {
            prices = PriceController.getAllValidProductPrice(prodID, new Date());
        } catch (final DatumException e) {
            e.printStackTrace();
        }
        return new InvoiceDetail(InvoiceID, // idInvoice | char(15)
                new Integer(++i), // qteDetLine | int(3,0)
                prodID, // qteprodid | char(15)
                new BigDecimal(1.0 + getRandom().nextDouble()), // qteQty |
                // decimal(7,2)
                uMeasure[getRandom().nextInt(uMeasure.length)], // qtemeasure |
                // char(5)
                prices.get(getRandom().nextInt(prices.size())).getPriUnit(), // qtePrice | decimal(9,2)
                vat[getRandom().nextInt(vat.length)], "Test comments", true // active
                // |
                // boolean
        );

    }
}
