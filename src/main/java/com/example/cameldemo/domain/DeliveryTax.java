package com.example.cameldemo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DeliveryTax {

    private final Double price;

    @JsonCreator
    public DeliveryTax(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
}
