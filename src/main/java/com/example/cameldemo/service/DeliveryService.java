package com.example.cameldemo.service;

import com.example.cameldemo.domain.DeliveryTax;
import com.example.cameldemo.domain.Product;
import com.example.cameldemo.domain.dto.DeliveryRequestDto;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    public DeliveryTax getDeliveryForCart(final DeliveryRequestDto deliveryRequestDto) {
        final double totalAmount = deliveryRequestDto
                .getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        return new DeliveryTax(totalAmount * Math.random());
    }
}
