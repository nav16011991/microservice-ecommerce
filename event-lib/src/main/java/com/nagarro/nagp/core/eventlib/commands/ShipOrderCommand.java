package com.nagarro.nagp.core.eventlib.commands;

import java.util.Objects;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    public ShipOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipOrderCommand that = (ShipOrderCommand) o;
        return orderId.equals(that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash( orderId);
    }

    @Override
    public String toString() {
        return "ShipOrderCommand{" +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}