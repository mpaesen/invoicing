package persistency.test;

import junit.framework.TestCase;
import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.Invoice;
import model.test.DummyAddress;
import model.test.DummyFactory;
import model.test.DummyInvoice;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceController;

public class TestDummyInvoice extends TestCase {

    private Invoice invoice;
    private Customer customer;
    private Address address;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        address = DummyAddress.createAddress(customer.getIdCus());
        AddressController.createAddress(address);
        invoice = DummyInvoice.createInvoice(customer.getIdCus(),
                address.getIdAddress());
    }

    public void testCreateInvoice() {

        assertTrue(InvoiceController.createInvoice(invoice));
    }
}
