package com.example.cameldemo.service;

import com.example.cameldemo.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public Product getDeliveryForCart(final String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Product(id, "TV 74'", Math.random() * 100);
    }
}
