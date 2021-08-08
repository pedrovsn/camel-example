package com.example.cameldemo.api;

import com.example.cameldemo.domain.Product;
import com.example.cameldemo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    private final ProductService productService;

    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/product/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return productService.getDeliveryForCart(id);
    }
}