package com.nagarro.nagp.core.eventlib.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class CreateInvoiceCommand{

    @TargetAggregateIdentifier
    private final String paymentId;
    private final String orderId;

    public CreateInvoiceCommand(String paymentId, String orderId) {
        this.paymentId = paymentId;
        this.orderId = orderId;
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
        CreateInvoiceCommand that = (CreateInvoiceCommand) o;
        return paymentId.equals(that.paymentId) &&
                orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderId);
    }

    @Override
    public String toString() {
        return "CreateInvoiceCommand{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
