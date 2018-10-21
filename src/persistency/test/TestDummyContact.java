package persistency.test;

import model.BusinessTypeEnum;
import model.Contact;
import model.Customer;
import model.test.DummyContact;
import model.test.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.ContactController;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 */


public class TestDummyContact {

    private Customer customer;
    private Contact contact;

    @BeforeAll
    protected void setUp() {
        //super.setUp();
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        contact = DummyContact.createContact(customer.getIdCus());

    }

    /**
     * Create 1 customer contact via SP createContact()
     */
    @Test
    public void testCreateContact() {
        if (!ContactController.createContact(contact)) throw new AssertionError();
    }
}
