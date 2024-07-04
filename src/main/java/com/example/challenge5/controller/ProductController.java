package com.example.challenge5.controller;


import com.example.challenge5.dto.request.ProductCreationRequest;
import com.example.challenge5.dto.request.ProductUpdateRequest;
import com.example.challenge5.dto.response.ApiResponse;
import com.example.challenge5.dto.response.ProductResponse;
import com.example.challenge5.entity.Product;
import com.example.challenge5.services.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/products")
@Tag(name = "Product")
public class ProductController {
    ProductServiceImpl productServiceImpl;

    @GetMapping
    public ApiResponse<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productServiceImpl.getProducts();
        return ApiResponse.<List<ProductResponse>>builder()
                .message("Get all products")
                .responseData(products)
                .status(products != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProduct(@PathVariable("id") int id) {
        ProductResponse productResponse = productServiceImpl.getProduct(id);
        return ApiResponse.<ProductResponse>builder()
                .message("Get product")
                .responseData(productResponse)
                .status(productResponse != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        ProductResponse productResponse = productServiceImpl.createProduct(request);
        return ApiResponse.<ProductResponse>builder()
                .message("Create product")
                .responseData(productResponse)
                .status(productResponse != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable("id") int id, @RequestBody ProductUpdateRequest request) {
        ProductResponse productResponse = productServiceImpl.updateProduct(id, request);
        return ApiResponse.<ProductResponse>builder()
                .message("Update product")
                .responseData(productResponse)
                .status(productResponse != null ? "success" : "failure")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable("id") int id) {
        productServiceImpl.deleteProduct(id);
        return ApiResponse.<Void>builder()
                .message("Delete product")
                .status("success")
                .timeStamp(LocalDateTime.now())
                .violation(null)
                .build();
    }

}
