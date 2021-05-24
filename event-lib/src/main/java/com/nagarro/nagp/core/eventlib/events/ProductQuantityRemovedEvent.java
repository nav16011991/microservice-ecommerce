package com.nagarro.nagp.core.eventlib.events;

import java.util.Objects;

public class ProductQuantityRemovedEvent {

    private final String productId;

    private final Integer unit;

    public ProductQuantityRemovedEvent(String productId, Integer unit) {
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
        ProductQuantityRemovedEvent that = (ProductQuantityRemovedEvent) o;
        return productId.equals(that.productId) &&
                unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, unit);
    }

    @Override
    public String toString() {
        return "ProductQuantityRemovedEvent{" +
                "productId='" + productId + '\'' +
                ", unit=" + unit +
                '}';
    }
}