package com.example.springbootbackend.controller;

import com.example.springbootbackend.dto.ProductDto;
import com.example.springbootbackend.entity.Product;
import com.example.springbootbackend.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/products")
@Validated
public class ProductController {

    private final Logger logger = LogManager.getLogger("MetricsLogger");
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(){
        logger.info("getProducts");
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") @NotBlank @Size(max = 10) String id){
        logger.info("getProductById");
        return productService.findProductById(id);
    }

    @PostMapping
    public Product postProduct(@Valid @RequestBody ProductDto productDto){
        logger.info("postProduct");
        return productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public void putProduct(@PathVariable("id") @NotBlank @Size(max = 10) String id,
                           @Valid @RequestBody ProductDto productDto){
        logger.info("putProduct");
        //check that id exist
        if(!productService.findProductById(id).getId().equals(id)) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "id does not match");
        productService.updateProduct(id, productDto);
    }
}
