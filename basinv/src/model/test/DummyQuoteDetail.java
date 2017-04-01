package model.test;

import java.math.BigDecimal;
import java.util.List;

import model.Price;
import model.QuoteDetail;
import persistency.controller.PriceController;
import utilities.Date;
import utilities.DatumException;


public class DummyQuoteDetail extends Dummy {
	private static String[] uMeasure= {"m", "m²", "m³", "kg", "ton"};
	private static int i;
	/**
	 * @param prodID
	 * @return
	 */
	public static QuoteDetail createQuoteDetail(String quoteID, String prodID){	
		List<Price> prices = null;
		try {
			prices = PriceController.getAllValidProductPrice(prodID, new Date());
		} catch (DatumException e) {
			e.printStackTrace();
		}
		return new QuoteDetail(
				 quoteID,	//idQuote    | char(15)
				 new Integer(++i) ,			//qteDetLine | int(3,0)
				 prodID,	//qteprodid  | char(15)
				 new BigDecimal(1.0+ getRandom().nextDouble()),	//qteQty     | decimal(7,2)
				 uMeasure[getRandom().nextInt(uMeasure.length)], //qtemeasure | char(5)
				 prices.get(0).getPriUnit(),//qtePrice   | decimal(9,2)
				 "Test commentaar"	,
				 true				//active | boolean
		);
		
		
	}
}
