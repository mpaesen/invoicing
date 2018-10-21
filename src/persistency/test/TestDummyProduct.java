package persistency.test;

import model.BusinessTypeEnum;
import model.Product;
import model.test.DummyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistency.controller.ProductController;

public class TestDummyProduct {
    private Product product;

    @BeforeAll
    public void setUp() {
        product = (Product) DummyFactory
                .createBusiness(BusinessTypeEnum.PRODUCT);
    }

    @Test
    public void testCreateProduct() {

        if (!ProductController.createProduct(product)) throw new AssertionError();
    }

}
