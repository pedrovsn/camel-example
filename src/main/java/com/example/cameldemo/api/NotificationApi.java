package com.example.cameldemo.api;

import com.example.cameldemo.domain.Order;
import com.example.cameldemo.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationApi {

    private final NotificationService deliveryService;

    public NotificationApi(NotificationService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/api/notification")
    public void getDeliveryTax(@RequestBody final Order cart) throws InterruptedException {
        deliveryService.sendNotification(cart);
    }
}
