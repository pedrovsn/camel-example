package com.example.cameldemo.api;

import com.example.cameldemo.domain.Product;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.example.cameldemo.camel.route.PlaceOrderRoute.PLACE_INIT_ROUTE;
import static com.example.cameldemo.camel.route.PlaceOrderRoute.PLACE_ORDER_ROUTE;

@RestController
public class CamelApi {

    private final ProducerTemplate producerTemplate;

    public CamelApi(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping("/api/camel")
    public void camelProcess() {
        producerTemplate.start();

        Product product = producerTemplate.requestBody(PLACE_INIT_ROUTE,
                UUID.randomUUID().toString(), // id
                Product.class);

        System.out.println(product);

        producerTemplate.stop();
    }
}
