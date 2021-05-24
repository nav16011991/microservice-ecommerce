package com.nagarro.nagp.core.eventlib.events;

import java.util.Objects;

public class OrderShippingCreatedEvent {

    public final String shippingId;

    public final String orderId;

    public final String paymentId;

    public OrderShippingCreatedEvent(String shippingId, String orderId, String paymentId) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.paymentId = paymentId;
    }

    public String getShippingId() {
        return shippingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderShippingCreatedEvent that = (OrderShippingCreatedEvent) o;
        return shippingId.equals(that.shippingId) &&
                orderId.equals(that.orderId) &&
                paymentId.equals(that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingId, orderId, paymentId);
    }

    @Override
    public String toString() {
        return "OrderShippingCreatedEvent{" +
                "shippingId='" + shippingId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
