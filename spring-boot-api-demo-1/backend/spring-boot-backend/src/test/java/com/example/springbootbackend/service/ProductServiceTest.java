package com.example.springbootbackend.service;

import com.example.springbootbackend.dto.ProductDto;
import com.example.springbootbackend.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.webjars.NotFoundException;
import java.util.Random;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource({"classpath:application-test.properties"})
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllProducts() {
        List<ProductDto> products = productService.findAllProducts();
        assertEquals(110, products.size());
    }

    @Test
    void findProductById() {
        //positive scenario
        ProductDto product = productService.findProductById("S10_2016");
        assertNotNull(product);
        assertEquals("1996 Moto Guzzi 1100i", product.getProductName());
        assertEquals("Highway 66 Mini Classics", product.getProductVendor());
        assertEquals(6625, (int)product.getQuantityInStock());

        //negative scenario
        try {
            product = productService.findProductById("xxx_xxx");
        }catch(NotFoundException e){assertTrue(true);}
    }

    @Test
    void addProduct() {
        ProductDto product = productService.findProductById("S10_2016");
        product.setId("xxx_xxxx");
        productService.addProduct(product);

        List<ProductDto> products = productService.findAllProducts();
        assertEquals(111, products.size());

        //clean
        productService.deleteProduct(product);
        assertEquals(110, productService.findAllProducts().size());
    }

    @Test
    void updateProduct() {
        ProductDto product = productService.findAllProducts().get(new Random().nextInt(110));

        String oldName = product.getProductName();
        String newName = "new name";

        product.setProductName(newName);
        productService.updateProduct(product.getId(), product);

        assertEquals(productService.findProductById(product.getId()).getProductName(), newName);

        //clean
        product.setProductName(oldName);
        productService.updateProduct(product.getId(), product);
        assertEquals(productService.findProductById(product.getId()).getProductName(), oldName);
    }
}