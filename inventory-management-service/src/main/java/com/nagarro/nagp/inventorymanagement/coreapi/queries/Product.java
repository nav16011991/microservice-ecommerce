package com.nagarro.nagp.inventorymanagement.coreapi.queries;

import java.util.Objects;

public class Product {

    private final String productId;
    private Integer units;

    public Product(String productId, Integer units) {
        this.productId = productId;
        this.units = units;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getUnits() {
        return units;
    }

    public void addUnit(Integer units) {
        this.units += units;
    }

    public void removeUnit(Integer unit) {
        this.units -= units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId) &&
                Objects.equals(units, product.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, units);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", units='" + units + '\'' +
                '}';
    }


}
