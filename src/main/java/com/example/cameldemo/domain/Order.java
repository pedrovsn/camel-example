package com.example.cameldemo.domain;

import java.util.List;

public class Order {

    private final String id;

    private final List<Product> products;

    private final DeliveryTax deliveryTax;

    private final Double amount;

    public Order(String id, List<Product> products, DeliveryTax deliveryTax, Double amount) {
        this.id = id;
        this.products = products;
        this.deliveryTax = deliveryTax;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public DeliveryTax getDeliveryTax() {
        return deliveryTax;
    }

    public Double getAmount() {
        return amount;
    }
}
