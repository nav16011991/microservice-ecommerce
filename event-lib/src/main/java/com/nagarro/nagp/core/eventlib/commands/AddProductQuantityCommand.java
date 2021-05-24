package com.nagarro.nagp.core.eventlib.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class AddProductQuantityCommand {

    @TargetAggregateIdentifier
    private final String productId;

    private final Integer unit;

    public AddProductQuantityCommand(String productId, Integer unit) {
        this.productId = productId;
        this.unit = unit;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddProductQuantityCommand that = (AddProductQuantityCommand) o;
        return productId.equals(that.productId) &&
                unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, unit);
    }

    @Override
    public String toString() {
        return "AddProductQuantityCommand{" +
                "productId='" + productId + '\'' +
                ", unit=" + unit +
                '}';
    }
}