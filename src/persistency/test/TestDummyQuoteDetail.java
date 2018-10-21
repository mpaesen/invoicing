/**
 *
 */
package persistency.test;

import model.*;
import model.test.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.*;

/**
 * @author Mathy
 */
public class TestDummyQuoteDetail {

    private Quote quote;
    private QuoteDetail detail;
    private Product product;
    private Customer customer;
    private Address address;
    private Price price;

    @BeforeAll
    protected void setUp() {
        //super.setUp();
        customer = (Customer) DummyFactory
                .createBusiness(BusinessTypeEnum.CUSTOMER);
        CustomerController.createCustomer(customer);
        address = DummyAddress.createAddress(customer.getIdCus());
        AddressController.createAddress(address);
        quote = DummyQuote.createQuote(customer.getIdCus(),
                address.getIdAddress());
        QuoteController.createQuote(quote);
        product = (Product) DummyFactory
                .createBusiness(BusinessTypeEnum.PRODUCT);
        ProductController.createProduct(product);
        price = DummyPrice.createPrice(product.getIdProd());
        PriceController.createPrice(price);
        detail = DummyQuoteDetail.createQuoteDetail(quote.getIdQuote(),
                product.getIdProd());
    }

    @Test
    public void testCreateQuoteDetail() {
        if (!QuoteDetailController.createQuoteDetail(detail)) throw new AssertionError();
    }
}
