package com.example.cameldemo.camel.route;

import com.example.cameldemo.domain.Cart;
import com.example.cameldemo.domain.Product;
import com.example.cameldemo.integration.client.OrderClient;
import com.example.cameldemo.integration.client.ProductClient;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlaceOrderRoute extends RouteBuilder {

    public static final String PLACE_INIT_ROUTE = "direct:init";
    public static final String GET_FIRST_PRODUCT_ROUTE = "direct:getFirstProduct";
    public static final String GET_SECOND_PRODUCT_ROUTE = "direct:getSecondProduct";
    public static final String PLACE_ORDER_ROUTE = "direct:placeOrder";

    @Override
    public void configure() throws Exception {

        from(PLACE_INIT_ROUTE)
                .multicast(new AggregationStrategy() {
                    @Override
                    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                        if (oldExchange == null) {
                            final var product = newExchange.getIn().getBody(Product.class);
                            newExchange.getIn().setBody(new Cart(Collections.singletonList(product)), Cart.class);
                            return newExchange;
                        } else {
                            final var productList = oldExchange.getIn().getBody(List.class);
                            final var newProduct = newExchange.getIn().getBody(Product.class);
                            final List<Product> mergedList = new ArrayList<>();
                            mergedList.addAll(productList);
                            mergedList.add(newProduct);
                            final var cart = new Cart(mergedList);
                            oldExchange.getIn().setBody(cart, Cart.class);
                            return oldExchange;
                        }
                    }
                })
                .to(GET_FIRST_PRODUCT_ROUTE, GET_SECOND_PRODUCT_ROUTE)
                .end()
        .to(PLACE_ORDER_ROUTE);

        from(GET_FIRST_PRODUCT_ROUTE)
                .log("Getting first product")
                .bean(ProductClient.class, "getProductById")
                .end();

        from(GET_SECOND_PRODUCT_ROUTE)
                .log("Getting second product")
                .bean(ProductClient.class, "getProductById")
                .end();

        from(PLACE_ORDER_ROUTE)
                .log("Placing order")
                .log("Body: ${body}")
                .bean(OrderClient.class, "placeOrder")
                .end();
    }
}
