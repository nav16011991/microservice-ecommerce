package com.nagarro.nagp.inventorymanagement.commandmodel.product;

import com.nagarro.nagp.core.eventlib.commands.AddProductQuantityCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateProductCommand;
import com.nagarro.nagp.core.eventlib.commands.RemoveProductQuantityCommand;
import com.nagarro.nagp.core.eventlib.events.ProductCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductQuantityAddedEvent;
import com.nagarro.nagp.core.eventlib.events.ProductQuantityRemovedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private Integer availableUnits;

    protected ProductAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        apply(new ProductCreatedEvent(command.getProductId()));
    }

    @CommandHandler
    public void handle(AddProductQuantityCommand command) {
        apply(new ProductQuantityAddedEvent(command.getProductId(), command.getUnit()));
    }

    @CommandHandler
    public void handle(RemoveProductQuantityCommand command) {
        apply(new ProductQuantityRemovedEvent(command.getProductId(), command.getUnit()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.availableUnits = 0;
    }

    @EventSourcingHandler
    public void on(ProductQuantityAddedEvent event) {
        this.availableUnits += event.getUnit();
    }

    @EventSourcingHandler
    public void on(ProductQuantityRemovedEvent event) {
        this.availableUnits += event.getUnit();
    }
}
