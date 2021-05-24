package com.nagarro.nagp.orderservice.querymodel;

import com.nagarro.nagp.core.eventlib.events.OrderConfirmedEvent;
import com.nagarro.nagp.core.eventlib.events.OrderCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.OrderShippedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductAddedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductCountDecrementedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductCountIncrementedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductRemovedEvent;
import com.nagarro.nagp.orderservice.coreapi.queries.FindAllOrderedProductsQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindUserOrderedProductsQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.Order;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("orders")
public class OrdersEventHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {

        orders.put(event.getOrderId(), new Order(event.getOrderId(), event.getUserId()));
    }

    @EventHandler
    public void on(ProductAddedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.addProduct(event.getProductId());
            return order;
        });
    }

    @EventHandler
    public void on(ProductCountIncrementedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.incrementProductInstance(event.getProductId());
            return order;
        });
    }

    @EventHandler
    public void on(ProductCountDecrementedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.decrementProductInstance(event.getProductId());
            return order;
        });
    }

    @EventHandler
    public void on(ProductRemovedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.removeProduct(event.getProductId());
            return order;
        });
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.setOrderConfirmed();
            return order;
        });
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.setOrderShipped();
            return order;
        });
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orders.values());
    }

    @QueryHandler
    public List<Order> handle(FindUserOrderedProductsQuery query) {
        return new ArrayList<>(orders.values().stream().filter(e->query.getUserId().equals(e.getUserId())).collect(Collectors.toList()));
    }
}