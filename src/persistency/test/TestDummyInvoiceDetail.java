package persistency.test;

import model.*;
import model.test.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.*;

public class TestDummyInvoiceDetail {

    private Invoice invoice;
    private Customer customer;
    private Address address;
    private InvoiceDetail detail;
    private Product product;
    private Price price;

    @BeforeAll
    protected void setUp() {
        // super.setUp();

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

    @Test
    public void testCreateInvoiceDetail() {
        if (!InvoiceDetailController.createInvoiceDetail(detail)) {
            throw new AssertionError();
        }
    }
}
