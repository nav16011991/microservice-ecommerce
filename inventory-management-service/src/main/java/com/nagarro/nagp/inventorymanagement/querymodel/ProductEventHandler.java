package com.nagarro.nagp.inventorymanagement.querymodel;

import com.nagarro.nagp.core.eventlib.events.ProductCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductQuantityAddedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductQuantityRemovedEvent;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.FindAllProductQuery;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.FindProductByIdQuery;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.Product;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("products")
public class ProductEventHandler {

    private final Map<String, Product> products = new HashMap<>();

    @EventHandler
    public void on(ProductCreatedEvent event) {
        products.put(event.getProductId(), new Product(event.getProductId(), 0));
    }

    @EventHandler
    public void on(ProductQuantityAddedEvent event) {
        products.computeIfPresent(event.getProductId(), (productId, product) -> {
            product.addUnit(event.getUnit());
            return product;
        });
    }

    @EventHandler
    public void on(ProductQuantityRemovedEvent event) {
        products.computeIfPresent(event.getProductId(), (productId, product) -> {
            product.removeUnit(event.getUnit());
            return product;
        });
    }

    @QueryHandler
    public List<Product> handle(FindAllProductQuery query) {
        return new ArrayList<>(products.values());
    }

    @QueryHandler
    public List<Product> handle(FindProductByIdQuery query) {
        return new ArrayList<>(products.values().stream().filter(e->query.getProductId().equals(e.getProductId())).collect(Collectors.toList()));
    }
}
