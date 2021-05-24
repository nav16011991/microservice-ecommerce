package com.nagarro.nagp.core.eventlib.events;

import java.util.Objects;

public class InvoiceCreatedEvent  {

    public final String paymentId;

    public final String orderId;

    public InvoiceCreatedEvent(String paymentId, String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceCreatedEvent that = (InvoiceCreatedEvent) o;
        return paymentId.equals(that.paymentId) &&
                orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderId);
    }

    @Override
    public String toString() {
        return "InvoiceCreatedEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
