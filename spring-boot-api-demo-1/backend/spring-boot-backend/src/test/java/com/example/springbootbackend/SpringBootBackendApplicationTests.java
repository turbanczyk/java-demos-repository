package com.example.springbootbackend;

import com.example.springbootbackend.controller.ProductController;
import com.example.springbootbackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource({"classpath:application-test.properties"})
class SpringBootBackendApplicationTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductController productController;

    @Test
    void contextLoads() {
        assertNotNull(productService);
        assertNotNull(productController);
    }

}
