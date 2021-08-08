package com.example.cameldemo.api;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.Order;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cameldemo.camel.route.PlaceOrderRoute.PLACE_INIT_ROUTE;

@RestController
public class CamelApi {

    private final ProducerTemplate producerTemplate;

    public CamelApi(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/api/camel")
    public Order camelProcess(@RequestBody Cart cart) {
        producerTemplate.start();

        final Order order = producerTemplate.requestBody(PLACE_INIT_ROUTE,
                cart,
                Order.class);

        producerTemplate.stop();

        return order;
    }
}
