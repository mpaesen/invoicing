/**
 * 
 */
package persistency.test;

import junit.framework.TestCase;
import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.Quote;
import model.test.DummyAddress;
import model.test.DummyFactory;
import model.test.DummyQuote;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;
import persistency.controller.QuoteController;

/**
 * @author Mathy
 *
 */
public class TestDummyQuote extends TestCase {
	private Quote quote;
	private Customer customer;
	private Address address;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		customer = (Customer) DummyFactory.createBusiness(BusinessTypeEnum.CUSTOMER);
		CustomerController.createCustomer(customer);
		address = DummyAddress.createAddress(customer.getIdCus());			
		AddressController.createAddress(address);
		quote = DummyQuote.createQuote(customer.getIdCus(), address.getIdAddress());		
	}

	public void testCreateQuote() {
		assertTrue(QuoteController.createQuote(quote));
	}

}
