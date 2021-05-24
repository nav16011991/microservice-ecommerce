package com.nagarro.nagp.core.eventlib.events;

import java.util.Objects;

public class OrderCreatedEvent {

    private final String orderId;

    private final String userId;

    public OrderCreatedEvent(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreatedEvent that = (OrderCreatedEvent) o;
        return orderId.equals(that.orderId) &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId);
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}