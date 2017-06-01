package persistency.test;

import junit.framework.TestCase;
import model.BusinessTypeEnum;
import model.Contact;
import model.Customer;
import model.test.DummyContact;
import model.test.DummyFactory;
import persistency.controller.ContactController;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 * 
 */
public class TestDummyContact extends TestCase {

	private Customer customer;
	private Contact contact;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		customer = (Customer) DummyFactory
				.createBusiness(BusinessTypeEnum.CUSTOMER);
		CustomerController.createCustomer(customer);
		contact = DummyContact.createContact(customer.getIdCus());

	}

	/**
	 * Create 1 customer contact via SP createContact()
	 */
	public void testCreateContact() {
		assertTrue(ContactController.createContact(contact));
	}
}
