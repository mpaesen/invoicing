package persistency.test;

import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.test.DummyAddress;
import model.test.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 */
public class TestDummyAddress {

    private Customer customer;
    private Address address;

    @BeforeAll
    protected void setUp() {

        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        address = DummyAddress.createAddress(customer.getIdCus());

    }

    /**
     * Create 1 customer address via SP createAddress()
     */
    @Test
    public void testCreateAddress() {
        if ((!AddressController.createAddress(address))) throw new AssertionError();
    }
}
