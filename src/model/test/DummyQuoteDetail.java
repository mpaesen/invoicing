package model.test;

import model.Price;
import model.QuoteDetail;
import persistency.controller.PriceController;
import utilities.Date;
import utilities.DatumException;

import java.math.BigDecimal;
import java.util.List;

public class DummyQuoteDetail extends Dummy {
    private static String[] uMeasure = {"wk", "u", "stk", "md", "dg"};
    private static int i;

    /**
     * @param prodID
     * @return
     */
    public static QuoteDetail createQuoteDetail(final String quoteID, final String prodID) {
        List<Price> prices = null;
        try {
            prices = PriceController.getAllValidProductPrice(prodID, new Date());
        } catch (final DatumException e) {
            e.printStackTrace();
        }
        return new QuoteDetail(quoteID, // idQuote | char(15)
                new Integer(++i), // qteDetLine | int(3,0)
                prodID, // qteprodid | char(15)
                new BigDecimal(1.0 + getRandom().nextDouble()), // qteQty |
                // decimal(7,2)
                uMeasure[getRandom().nextInt(uMeasure.length)], // qtemeasure |
                // char(5)
                prices.get(getRandom().nextInt(prices.size())).getPriUnit(),// qtePrice | decimal(9,2)
                "Test commentaar", true // active | boolean
        );

    }
}
