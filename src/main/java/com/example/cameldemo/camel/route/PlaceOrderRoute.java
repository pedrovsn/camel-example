package com.example.cameldemo.camel.route;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.DeliveryTax;
import com.example.cameldemo.domain.Order;
import com.example.cameldemo.domain.Product;
import com.example.cameldemo.domain.dto.DeliveryRequestDto;
import com.example.cameldemo.integration.client.DeliveryClient;
import com.example.cameldemo.integration.client.NotificationClient;
import com.example.cameldemo.integration.client.OrderClient;
import com.example.cameldemo.integration.client.ProductClient;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class PlaceOrderRoute extends RouteBuilder {

    public static final String PLACE_INIT_ROUTE = "direct:init";
    public static final String GET_FIRST_PRODUCT_ROUTE = "direct:getFirstProduct";
    public static final String GET_SECOND_PRODUCT_ROUTE = "direct:getSecondProduct";
    public static final String CALCULATE_DELIVERY_FEE = "direct:calculateDeliveryFee";
    public static final String NOTIFICATION_ROUTE = "direct:notification";
    public static final String PLACE_ORDER_ROUTE = "direct:placeOrder";

    @Override
    public void configure() throws Exception {

        from(PLACE_INIT_ROUTE)
                .setProperty("requestCart", simple("${body}"))
                .process(exchange -> {
                    final var body = exchange.getIn().getBody(Cart.class);
                    exchange.getIn().setBody(body.getProducts());
                })
                .split(body(), (oldExchange, newExchange) -> {
                    if (oldExchange == null) {
                        final var product = newExchange.getIn().getBody(Product.class);
                        newExchange.getIn().setBody(Collections.singletonList(product), List.class);
                        return newExchange;
                    } else {
                        final var productList = oldExchange.getIn().getBody(List.class);
                        final var newProduct = newExchange.getIn().getBody(Product.class);
                        final List<Product> mergedList = new ArrayList<>();
                        mergedList.addAll(productList);
                        mergedList.add(newProduct);
                        oldExchange.getIn().setBody(mergedList, List.class);
                        return oldExchange;
                    }
                })
                .parallelProcessing()
                .to(GET_FIRST_PRODUCT_ROUTE)
                .end()
                .setProperty("productList", simple("${body}"))
                .process(exchange -> {
                    final List<Product> productList = exchange.getIn().getBody(List.class);
                    final var requestCart = exchange.getProperty("requestCart", Cart.class);

                    exchange.getIn().setBody(new DeliveryRequestDto(productList,
                            requestCart.getPostalCode()));
                })
                .to(CALCULATE_DELIVERY_FEE)
                .process(exchange -> {
                    final List<Product> productList = exchange.getProperty("productList", List.class);
                    final var deliveryTax = exchange.getIn().getBody(DeliveryTax.class);

                    double sum = productList.stream()
                            .mapToDouble(Product::getPrice).sum();
                    double orderAmount = sum + deliveryTax.getPrice();

                    exchange.getIn().setBody(new Order(UUID.randomUUID().toString(),
                            productList,
                            deliveryTax,
                            orderAmount));
                })
                .wireTap(NOTIFICATION_ROUTE)
                .to(PLACE_ORDER_ROUTE)
                .end();

        from(GET_FIRST_PRODUCT_ROUTE)
                .log("*** Getting product")
                .bean(ProductClient.class, "getProductById(${body})")
                .end();

        from(CALCULATE_DELIVERY_FEE)
                .log("*** Calculating delivery fee")
                .bean(DeliveryClient.class, "getDeliveryForCart")
                .end();

        from(NOTIFICATION_ROUTE)
                .log("*** Sending notification to client")
                .bean(NotificationClient.class, "notificate")
                .end();

        from(PLACE_ORDER_ROUTE)
                .log("*** Placing order")
                .bean(OrderClient.class, "placeOrder")
                .end();
    }
}
