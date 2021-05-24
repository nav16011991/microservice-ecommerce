package com.nagarro.nagp.inventorymanagement.coreapi.exception;

public class DuplicateProductException  extends IllegalStateException {

    public DuplicateProductException(String name) {
        super("Cannot duplicate product  [" + name + "]");
    }
}
