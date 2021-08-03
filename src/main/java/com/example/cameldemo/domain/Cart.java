package com.example.cameldemo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Cart {

    private final List<Product> products;

    @JsonCreator
    public Cart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
