package persistency.test;


import junit.framework.TestCase;
import model.BusinessTypeEnum;
import model.Product;
import model.test.DummyFactory;
import persistency.controller.ProductController;

public class TestDummyProduct extends TestCase {
	private Product product;


	@Override
	public void setUp() {
		product = (Product) DummyFactory.createBusiness(BusinessTypeEnum.PRODUCT);
	}

	public void testCreateProduct() {

		assertTrue(ProductController.createProduct(product));
	}

}
