package com.example.springbootbackend.controller;

import com.example.springbootbackend.dto.ProductDto;
import com.example.springbootbackend.security.auth.RegisterRequest;
import com.example.springbootbackend.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource({"classpath:application-test.properties"})
@AutoConfigureMockMvc
@WithMockUser(roles = {"USER"})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() {
        try {
            MvcResult result = this.mockMvc
                    .perform(get("/api/v1/products")
                            .contentType(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            List<ProductDto> productDtoList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<ProductDto>>() {});
            assertEquals(110, productDtoList.size());
        }catch (Exception e) {fail();}
    }

    @Test
    void getProductById() {
        try {
            ProductDto productDto = productService.findAllProducts().get(new Random().nextInt(110));
            MvcResult result = this.mockMvc
                    .perform(get("/api/v1/products" + "/" + productDto.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            assertTrue(result.getResponse().getContentAsString().length() > 0);
            ProductDto productDtoResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ProductDto>() {});
            assertEquals(productDto.getId(), productDtoResult.getId());
            assertEquals(productDto.getProductName(), productDtoResult.getProductName());
            assertEquals(productDto.getProductVendor(), productDtoResult.getProductVendor());
            assertEquals(productDto.getBuyPrice(), productDtoResult.getBuyPrice());
        }catch (Exception e) {fail();}
    }

    @Test
    void postProduct() {
        try{
            ProductDto productDto = productService.findAllProducts().get(new Random().nextInt(110));
            productDto.setId("xxx_xx");

            MvcResult result = this.mockMvc
                    .perform(post("/api/v1/products")
                            .content(objectMapper.writeValueAsString(productDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            //if product will not exist method will throw exception
            ProductDto createdProductDto = productService.findProductById("xxx_xx");

            //clean
            productService.deleteProduct(productDto);

            assertEquals(110, productService.findAllProducts().size());

        }catch (Exception e) {fail();}
    }

    @Test
    void putProduct() {
        try{
            ProductDto productDto = productService.findAllProducts().get(new Random().nextInt(110));
            String oldName = productDto.getProductName();
            String newName = "new name";
            productDto.setProductName(newName);

            MvcResult result = this.mockMvc
                    .perform(put("/api/v1/products" + "/" + productDto.getId())
                            .content(objectMapper.writeValueAsString(productDto))
                            .contentType(MediaType.APPLICATION_JSON))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            ProductDto productDtoResult = productService.findProductById(productDto.getId());
            assertEquals(productDtoResult.getProductName(), newName);

            //clean
            productDtoResult.setProductName(oldName);
            productService.updateProduct(productDtoResult.getId(), productDtoResult);
            assertEquals(productService.findProductById(productDto.getId()).getProductName(), oldName);

        }catch (Exception e) {fail();}
    }
}