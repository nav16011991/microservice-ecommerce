package com.nagarro.nagp.orderservice.coreapi.queries;

import java.util.Objects;

public class FindUserOrderedProductsQuery {
    private final String userId;

    public FindUserOrderedProductsQuery(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindUserOrderedProductsQuery that = (FindUserOrderedProductsQuery) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "FindUserOrderedProductsQuery{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
