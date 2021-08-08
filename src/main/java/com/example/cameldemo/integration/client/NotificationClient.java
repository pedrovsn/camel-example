package com.example.cameldemo.integration.client;

import com.example.cameldemo.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationClient", url = "http://localhost:8080/api")
public interface NotificationClient {

    @PostMapping(path = "/notification")
    void notificate(@RequestBody Order order);
}
