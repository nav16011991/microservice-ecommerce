package com.nagarro.nagp.orderservice.gui;

import com.nagarro.nagp.core.eventlib.commands.*;
import com.nagarro.nagp.orderservice.coreapi.queries.FindAllOrderedProductsQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindUserOrderedProductsQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.Order;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/order")
    public CompletableFuture<String> createOrder(@RequestHeader("user-id") String userId) {
        return createOrder(UUID.randomUUID().toString(), userId);
    }

    @PostMapping("/order/{order-id}")
    public CompletableFuture<String> createOrder(@PathVariable("order-id") String orderId,@RequestHeader("user-id") String userId) {
        return commandGateway.send(new CreateOrderCommand(orderId, userId));
    }

    @PostMapping("/order/{order-id}/product/{product-id}")
    public CompletableFuture<Void> addProduct(@PathVariable("order-id") String orderId,
                                              @PathVariable("product-id") String productId) {
        return commandGateway.send(new AddProductCommand(orderId, productId));
    }

    @PostMapping("/order/{order-id}/product/{product-id}/increment")
    public CompletableFuture<Void> incrementProduct(@PathVariable("order-id") String orderId,
                                                    @PathVariable("product-id") String productId) {
        return commandGateway.send(new IncrementProductCountCommand(orderId, productId));
    }

    @PostMapping("/order/{order-id}/product/{product-id}/decrement")
    public CompletableFuture<Void> decrementProduct(@PathVariable("order-id") String orderId,
                                                    @PathVariable("product-id") String productId) {
        return commandGateway.send(new DecrementProductCountCommand(orderId, productId));
    }

    @PostMapping("/order/{order-id}/confirm")
    public CompletableFuture<Void> confirmOrder(@PathVariable("order-id") String orderId) {
        return commandGateway.send(new ConfirmOrderCommand(orderId));
    }

    @GetMapping("/all-orders")
    public CompletableFuture<List<Order>> findAllOrders() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }

    @GetMapping("/orders")
    public CompletableFuture<List<Order>> findAllOrders(@RequestHeader("user-id") String userId) {
        return queryGateway.query(new FindUserOrderedProductsQuery(userId), ResponseTypes.multipleInstancesOf(Order.class));
    }
}
