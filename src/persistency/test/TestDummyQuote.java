/**
 *
 */
package persistency.test;

import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.Quote;
import model.test.DummyAddress;
import model.test.DummyFactory;
import model.test.DummyQuote;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;
import persistency.controller.QuoteController;
import sun.jvm.hotspot.utilities.AssertionFailure;

/**
 * @author Mathy
 */
public class TestDummyQuote {
    private Quote quote;
    private Customer customer;
    private Address address;

    @BeforeAll
    protected void setUp() {
        /* super.setUp(); */
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        address = DummyAddress.createAddress(customer.getIdCus());
        AddressController.createAddress(address);
        quote = DummyQuote.createQuote(customer.getIdCus(),
                address.getIdAddress());
    }

    @Test
    public void testCreateQuote() throws AssertionError {
        if (!QuoteController.createQuote(quote)) throw new AssertionFailure();
    }

}
