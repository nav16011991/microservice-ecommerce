package com.nagarro.nagp.shippingservice.gui;

import com.nagarro.nagp.core.eventlib.commands.ShipOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ShippingRestEndpoint {

    private final CommandGateway commandGateway;

    public ShippingRestEndpoint(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/{shipping-id}")
    public CompletableFuture<Void> shipOrder(@PathVariable("shipping-id") String orderId) {
        return commandGateway.send(new ShipOrderCommand(orderId));
    }
}
