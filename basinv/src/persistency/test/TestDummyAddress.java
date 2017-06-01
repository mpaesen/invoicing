package persistency.test;

import junit.framework.TestCase;
import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.test.DummyAddress;
import model.test.DummyFactory;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 * 
 */
public class TestDummyAddress extends TestCase {

	private Customer customer;
	private Address address;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		customer = (Customer) DummyFactory
				.createBusiness(BusinessTypeEnum.CUSTOMER);
		CustomerController.createCustomer(customer);
		address = DummyAddress.createAddress(customer.getIdCus());

	}

	/**
	 * Create 1 customer address via SP createAddress()
	 */
	public void testCreateAddress() {
		assertTrue(AddressController.createAddress(address));
	}
}
