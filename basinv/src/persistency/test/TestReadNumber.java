package persistency.test;

import persistency.controller.NumberController;

public class TestReadNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("\nCustomer: %s", NumberController.readLastNumber("Cus", 0).toString());
		System.out.printf("\nContact: %s", NumberController.readLastNumber("Cnt", 0).toString());
		System.out.printf("\nAddress: %s", NumberController.readLastNumber("Add", 0).toString());		
		System.out.printf("\nProduct: %s", NumberController.readLastNumber("Prd", 0).toString());
		System.out.printf("\nPrice: %s", NumberController.readLastNumber("Prc", 0).toString());
		System.out.printf("\nQuote: %s", NumberController.readLastNumber("Qte", 2011).toString());
		System.out.printf("\nInvoice: %s", NumberController.readLastNumber("Inv", 2011).toString());
	}

}
