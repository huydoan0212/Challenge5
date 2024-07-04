package com.example.challenge5.services.impl;


import com.example.challenge5.dto.request.ProductCreationRequest;
import com.example.challenge5.dto.request.ProductUpdateRequest;
import com.example.challenge5.dto.response.ProductResponse;
import com.example.challenge5.entity.Product;
import com.example.challenge5.exception.CustomException;
import com.example.challenge5.exception.Error;
import com.example.challenge5.repo.ProductRepository;
import com.example.challenge5.services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse createProduct(ProductCreationRequest request) {
        Product product = new Product();

        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setDescription(request.getDescription());

        productRepository.save(product);

        return ProductResponse.builder()
                .code(request.getCode())
                .name(request.getName())
                .category(request.getCategory())
                .brand(request.getBrand())
                .type(request.getType())
                .description(request.getDescription())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(int id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException(Error.PRODUCT_NOT_FOUND));

        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setDescription(request.getDescription());

        productRepository.save(product);

        return ProductResponse.builder()
                .code(request.getCode())
                .name(request.getName())
                .category(request.getCategory())
                .brand(request.getBrand())
                .type(request.getType())
                .description(request.getDescription())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    public List<ProductResponse> getProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            productResponses.add(ProductResponse.builder()
                    .code(product.getCode())
                    .brand(product.getBrand())
                    .category(product.getCategory())
                    .description(product.getDescription())
                    .type(product.getType())
                    .name(product.getName())
                    .build());
        }

        return productResponses;
    }
    @PreAuthorize("hasRole('USER')")
    public ProductResponse getProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new CustomException(Error.PRODUCT_NOT_FOUND));
        return ProductResponse.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .type(product.getType())
                .description(product.getDescription())
                .build();
    }

}
