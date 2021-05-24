package com.nagarro.nagp.shippingservice.commandmodel.shipping;

import com.nagarro.nagp.core.eventlib.commands.CreateShippingCommand;
import com.nagarro.nagp.core.eventlib.events.OrderShippingCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShippingAggregate {

    private String shippingId;

    @AggregateIdentifier
    private String orderId;

    private String paymentId;

    public ShippingAggregate() {
    }

    @CommandHandler
    public ShippingAggregate(CreateShippingCommand createShippingCommand){
        AggregateLifecycle.apply(new OrderShippingCreatedEvent(createShippingCommand.getShippingId(), createShippingCommand.getOrderId(), createShippingCommand.getPaymentId()));
    }

    @EventSourcingHandler
    protected void on(OrderShippingCreatedEvent orderShippingCreatedEvent){
        this.shippingId = orderShippingCreatedEvent.getShippingId();
        this.orderId = orderShippingCreatedEvent.getOrderId();
        this.paymentId = orderShippingCreatedEvent.getPaymentId();
    }

//    @CommandHandler
//    public ShippingAggregate(ShipOrderCommand shipOrderCommand){
//        AggregateLifecycle.apply(new OrderShippedEvent(shipOrderCommand.getOrderId()));
//    }
//
//    @EventSourcingHandler
//    protected void on(OrderShippedEvent orderShippedEvent){
//        this.orderId = orderShippedEvent.getOrderId();
//    }
}
