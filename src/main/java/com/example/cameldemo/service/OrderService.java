package com.example.cameldemo.service;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.Order;
import com.example.cameldemo.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order placeOrder(Cart cart) {
        return new Order(cart.getProducts(), cart.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum());
    }
}
