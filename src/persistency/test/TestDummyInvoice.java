package persistency.test;

import model.Address;
import model.BusinessTypeEnum;
import model.Customer;
import model.Invoice;
import model.test.DummyAddress;
import model.test.DummyFactory;
import model.test.DummyInvoice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.AddressController;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceController;

public class TestDummyInvoice {

    private Invoice invoice;
    private Customer customer;
    private Address address;

    @BeforeAll
    protected void setUp() {
        //super.setUp();
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        address = DummyAddress.createAddress(customer.getIdCus());
        AddressController.createAddress(address);
        invoice = DummyInvoice.createInvoice(customer.getIdCus(),
                address.getIdAddress());
    }

    @Test
    public void testCreateInvoice() throws AssertionError {

        if (!(InvoiceController.createInvoice(invoice))) throw new AssertionError();
    }
}
