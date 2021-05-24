package com.nagarro.nagp.inventorymanagement.coreapi.queries;

import java.util.Objects;

public class FindProductByIdQuery {
    private final String productId;

    public FindProductByIdQuery(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindProductByIdQuery that = (FindProductByIdQuery) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "FindProductByIdQuery{" +
                "productId='" + productId + '\'' +
                '}';
    }
}
