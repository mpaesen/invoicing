package persistency.test;


import junit.framework.TestCase;
import model.BusinessTypeEnum;
import model.Price;
import model.Product;
import model.test.DummyFactory;
import model.test.DummyPrice;
import persistency.controller.ProductController;

import utilities.Date;
import utilities.DatumException;

public class TestDummyPrice extends TestCase {
	private Product product;
	private Price price;
	private Date date;

	@Override
	public void setUp() {
		product = (Product) DummyFactory.createBusiness(BusinessTypeEnum.PRODUCT);
		ProductController.createProduct(product);	
		price = DummyPrice.createPrice(product.getIdProd());

	}


	public void testCreatePrice() throws DatumException{
//		assertTrue(PriceController.createPrice(price));
		System.out.println(price);
		assertTrue(price.getPrifrom().equals(new Date()));
	}
	
	
}
