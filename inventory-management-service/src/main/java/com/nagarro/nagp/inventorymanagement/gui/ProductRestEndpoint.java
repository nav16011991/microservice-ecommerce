package com.nagarro.nagp.inventorymanagement.gui;

import com.nagarro.nagp.core.eventlib.commands.AddProductQuantityCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateProductCommand;
import com.nagarro.nagp.core.eventlib.commands.RemoveProductQuantityCommand;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.FindAllProductQuery;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.FindProductByIdQuery;
import com.nagarro.nagp.inventorymanagement.coreapi.queries.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProductRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ProductRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/product")
    public CompletableFuture<String> createProduct() {
        return createProduct(UUID.randomUUID().toString());
    }

    @PostMapping("/product/{product-id}")
    public CompletableFuture<String> createProduct(@PathVariable("product-id") String productId) {
        return commandGateway.send(new CreateProductCommand(productId));
    }

    @PostMapping("/product/{product-id}/add-unit/{unit}")
    public CompletableFuture<String> addProduct(@PathVariable("product-id") String productId, @PathVariable("unit") Integer unit) {
        return commandGateway.send(new AddProductQuantityCommand(productId, unit));
    }

    @PostMapping("/product/{product-id}/remove-unit/{unit}")
    public CompletableFuture<String> removeProduct(@PathVariable("product-id") String productId, @PathVariable("unit") Integer unit) {
        return commandGateway.send(new RemoveProductQuantityCommand(productId, unit));
    }

    @GetMapping("/all-products")
    public CompletableFuture<List<Product>> findAllOrders() {
        return queryGateway.query(new FindAllProductQuery(), ResponseTypes.multipleInstancesOf(Product.class));
    }

    @GetMapping("/product")
    public CompletableFuture<List<Product>> findAllOrders(@RequestHeader("product-id") String productId) {
        return queryGateway.query(new FindProductByIdQuery(productId), ResponseTypes.multipleInstancesOf(Product.class));
    }
}
