package com.nagarro.nagp.orderservice.coreapi.queries;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {

    private final String orderId;
    private final String userId;
    private final Map<String, Integer> products;
    private OrderStatus orderStatus;

    public Order(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = new HashMap<>();
        orderStatus = OrderStatus.CREATED;
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void addProduct(String productId) {
        products.putIfAbsent(productId, 1);
    }

    public void incrementProductInstance(String productId) {
        products.computeIfPresent(productId, (id, count) -> ++count);
    }

    public void decrementProductInstance(String productId) {
        products.computeIfPresent(productId, (id, count) -> --count);
    }


    public void removeProduct(String productId) {
        products.remove(productId);
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId) &&
                userId.equals(order.userId) &&
                products.equals(order.products) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, products, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                "userId='" + userId + '\'' +
                ", products=" + products +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
