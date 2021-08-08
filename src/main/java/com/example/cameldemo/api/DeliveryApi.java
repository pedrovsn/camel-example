package com.example.cameldemo.api;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.DeliveryTax;
import com.example.cameldemo.domain.dto.DeliveryRequestDto;
import com.example.cameldemo.service.DeliveryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryApi {

    private final DeliveryService service;

    public DeliveryApi(DeliveryService service) {
        this.service = service;
    }

    @PostMapping("/api/delivery/calculate")
    public DeliveryTax getDeliveryTax(@RequestBody final DeliveryRequestDto dto) {
        return service.getDeliveryForCart(dto);
    }
}
