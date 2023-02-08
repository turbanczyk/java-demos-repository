package com.example.springbootbackend.service;


import com.example.springbootbackend.dto.ProductDto;
import com.example.springbootbackend.entity.Product;
import com.example.springbootbackend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public List<ProductDto> findAllProducts(){
        var productDtoList = productRepository.findAll()
                .stream()
                .map(k -> {return modelMapper.map(k, ProductDto.class);})
                .collect(Collectors.toList());

        return productDtoList;
    }

    public ProductDto findProductById(String id){
        Product p = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Product by id" + id + " was not found"));
        return modelMapper.map(p, ProductDto.class);
    }

    public Product addProduct(ProductDto productDto){
        return productRepository.save(modelMapper.map(productDto, Product.class));
    }

    public void updateProduct(String id, ProductDto productDto){
        productRepository.save(modelMapper.map(productDto, Product.class));
    }

    public void deleteProduct(ProductDto productDto){
        productRepository.deleteById(productDto.getId());
    }

}
