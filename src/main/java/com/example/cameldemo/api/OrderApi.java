package com.example.cameldemo.api;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.Order;
import com.example.cameldemo.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApi {

    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/order")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
