/**
 * 
 */
package persistency.test;

import junit.framework.TestCase;
import model.*;
import model.test.*;
import persistency.controller.*;

/**
 * @author Mathy
 *
 */
public class TestDummyQuoteDetail extends TestCase {

    private Quote quote;
    private QuoteDetail detail;
    private Product product;
    private Customer customer;
    private Address address;
    private Price price;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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

    public void testCreateQuoteDetail() {
        assertTrue(QuoteDetailController.createQuoteDetail(detail));
    }
}
