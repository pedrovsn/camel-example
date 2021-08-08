package com.example.cameldemo.service;

import com.example.cameldemo.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(final Order order) throws InterruptedException {
        Thread.sleep(5000);
        LOGGER.info("Sending notification to client. Order#id {}", order.getId());
    }
}
