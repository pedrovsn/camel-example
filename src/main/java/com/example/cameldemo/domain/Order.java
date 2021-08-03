package com.example.cameldemo.domain;

import java.util.List;

public class Order {

    private final List<Product> products;

    private final Double amount;

    public Order(List<Product> products, Double amount) {
        this.products = products;
        this.amount = amount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Double getAmount() {
        return amount;
    }
}
