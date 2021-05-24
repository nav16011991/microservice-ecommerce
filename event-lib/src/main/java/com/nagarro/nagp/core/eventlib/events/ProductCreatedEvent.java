package com.nagarro.nagp.core.eventlib.events;

import java.util.Objects;

public class ProductCreatedEvent {

    private final String productId;

    public ProductCreatedEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCreatedEvent that = (ProductCreatedEvent) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "ProductCreatedEvent{" +
                "productId='" + productId + '\'' +
                '}';
    }
}