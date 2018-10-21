package persistency.test;

import model.BusinessTypeEnum;
import model.Customer;
import model.test.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.CustomerController;

/**
 * @author Mathy
 */
public class TestDummyCustomer {

    private Customer customer;

    @BeforeAll
    protected void setUp() {
        //super.setUp();
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
    }

    /**
     * Create 1 customer via SP createCustomer()
     */
    @Test
    public void testCreateCustomer() {
        if (!CustomerController.createCustomer(customer)) throw new AssertionError();
    }

}
