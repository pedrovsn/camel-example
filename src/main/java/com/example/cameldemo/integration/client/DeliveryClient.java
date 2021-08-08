package com.example.cameldemo.integration.client;

import com.example.cameldemo.domain.DeliveryTax;
import com.example.cameldemo.domain.dto.DeliveryRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DeliveryClient", url = "http://localhost:8080/api")
public interface DeliveryClient {

    @PostMapping(path = "/delivery/calculate")
    DeliveryTax getDeliveryForCart(@RequestBody DeliveryRequestDto deliveryRequestDto);
}
