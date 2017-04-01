package model.test;

import java.math.BigDecimal;
import utilities.Date;
import utilities.DatumException;

import persistency.controller.NumberController;

import model.Price;


public class DummyPrice extends Dummy {
	private static String[] uMeasure= {"m", "m²", "m³", "kg", "ton"};
	
	/**
	 * @param prodID
	 * @return
	 */
	public static Price createPrice(String prodID){
		Price price = null;
		try {
			price = new Price(//new java.rmi.dgc.VMID().toString(), 	//idPrice    | char(15)
					//Integer.toHexString(DummyNumber.getNewNumber()),
					NumberController.readLastNumber("Prc", 0).toString(),
					prodID,	//priProdid  | char(15)
					new Date() ,	//Prifrom    | datetime
					new BigDecimal(getRandom().nextDouble()) ,	//priunit    | decimal(9,2)
					uMeasure[getRandom().nextInt(uMeasure.length)],	//Primeasure | char(5)
					true	//active     | tinyint(1)
			
			);
		} catch (DatumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
		
	}
}
