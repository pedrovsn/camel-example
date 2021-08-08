package com.example.cameldemo.domain.dto;

import com.example.cameldemo.domain.Product;

import java.util.List;

public class DeliveryRequestDto {

    private final List<Product> products;

    private final String postalCode;

    public DeliveryRequestDto(List<Product> products, String postalCode) {
        this.products = products;
        this.postalCode = postalCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
