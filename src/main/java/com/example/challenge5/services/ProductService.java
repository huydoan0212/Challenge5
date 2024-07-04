package com.example.challenge5.services;


import com.example.challenge5.dto.request.ProductCreationRequest;
import com.example.challenge5.dto.request.ProductUpdateRequest;
import com.example.challenge5.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreationRequest request);

    ProductResponse updateProduct(int id, ProductUpdateRequest request);

    void deleteProduct(int id);

    List<ProductResponse> getProducts();

    ProductResponse getProduct(int id);
}
