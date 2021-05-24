package com.nagarro.nagp.core.eventlib.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class CreateShippingCommand {

    @TargetAggregateIdentifier
    private final String shippingId;

    private final String orderId;

    private final String paymentId;

    public CreateShippingCommand(String shippingId, String orderId, String paymentId) {
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
        CreateShippingCommand that = (CreateShippingCommand) o;
        return shippingId.equals(that.shippingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingId, orderId, paymentId);
    }

    @Override
    public String toString() {
        return "CreateShippingCommand{" +
                "shippingId='" + shippingId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
