package persistency.test;

import junit.framework.TestCase;
import model.BusinessTypeEnum;
import model.Customer;
import model.test.DummyFactory;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 * 
 */
public class TestDummyCustomer extends TestCase {

	private Customer customer;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		customer = (Customer) DummyFactory.createBusiness(BusinessTypeEnum.CUSTOMER);
		}


	/**
	 * Create 1 customer via SP createCustomer()
	 */
	public void testCreateCustomer() {	
		assertTrue(CustomerController.createCustomer(customer));		
	}

}
