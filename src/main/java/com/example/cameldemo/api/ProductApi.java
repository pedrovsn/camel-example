package com.example.cameldemo.api;

import com.example.cameldemo.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    @GetMapping("/api/product/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return new Product(id, "TV 74'", Math.random());
    }
}
