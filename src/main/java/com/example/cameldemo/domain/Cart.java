package com.example.cameldemo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Cart {

    private final List<String> products;

    private final String postalCode;

    @JsonCreator
    public Cart(List<String> products, String postalCode) {
        this.products = products;
        this.postalCode = postalCode;
    }

    public List<String> getProducts() {
        return products;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
