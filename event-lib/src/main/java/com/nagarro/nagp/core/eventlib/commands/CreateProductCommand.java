package com.nagarro.nagp.core.eventlib.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String productId;

    public CreateProductCommand(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductCommand that = (CreateProductCommand) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "CreateProductCommand{" +
                "productId='" + productId + '\'' +
                '}';
    }
}