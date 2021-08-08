package com.example.cameldemo.integration.client;

import com.example.cameldemo.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductClient", url = "http://localhost:8080/api")
public interface ProductClient {

    @GetMapping(path = "/product/{id}")
    Product getProductById(@PathVariable("id") String id);
}
