package persistency.test;

import model.BusinessTypeEnum;
import model.Price;
import model.Product;
import model.test.DummyFactory;
import model.test.DummyPrice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.ProductController;
import utilities.Date;
import utilities.DatumException;

public class TestDummyPrice {
    private Product product;
    private Price price;
    private Date date;

    @BeforeAll
    public void setUp() {
        product = (Product) DummyFactory
                .createBusiness(BusinessTypeEnum.PRODUCT);
        ProductController.createProduct(product);
        price = DummyPrice.createPrice(product.getIdProd());

    }

    @Test
    public void testCreatePrice() throws DatumException {
        // assertTrue(PriceController.createPrice(price));
        System.out.println(price);
        if (!price.getPrifrom().equals(new Date())) throw new AssertionError();
    }

}
