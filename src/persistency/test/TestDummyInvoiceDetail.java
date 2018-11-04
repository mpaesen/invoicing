package persistency.test;

import junit.framework.TestCase;
import model.*;
import model.test.*;
import persistency.controller.*;

public class TestDummyInvoiceDetail extends TestCase {

    private Invoice invoice;
    private Customer customer;
    private Address address;
    private InvoiceDetail detail;
    private Product product;
    private Price price;

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
        InvoiceController.createInvoice(invoice);
        product = (Product) DummyFactory
                .createBusiness(BusinessTypeEnum.PRODUCT);
        ProductController.createProduct(product);
        price = DummyPrice.createPrice(product.getIdProd());
        PriceController.createPrice(price);
        detail = DummyInvoiceDetail.createInvoiceDetail(invoice.getIdInvoice(),
                product.getIdProd());
    }

    public void testCreateInvoiceDetail() {
        assertTrue(InvoiceDetailController.createInvoiceDetail(detail));
    }
}
