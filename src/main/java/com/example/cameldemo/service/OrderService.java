package com.example.cameldemo.service;

import com.example.cameldemo.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order placeOrder(Order order) {
        return order;
    }
}
