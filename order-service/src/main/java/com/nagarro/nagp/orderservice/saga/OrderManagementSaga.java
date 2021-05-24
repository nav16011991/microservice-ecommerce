package com.nagarro.nagp.orderservice.saga;

import com.nagarro.nagp.core.eventlib.commands.CreateInvoiceCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateShippingCommand;
import com.nagarro.nagp.core.eventlib.events.InvoiceCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.OrderConfirmedEvent;
import com.nagarro.nagp.core.eventlib.events.OrderShippingCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Saga
public class OrderManagementSaga {

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderConfirmedEvent orderConfirmedEvent){
        String paymentId = UUID.randomUUID().toString();
        System.out.println("Saga invoked");

        //associate Saga
        SagaLifecycle.associateWith("paymentId", paymentId);

        System.out.println("order id" + orderConfirmedEvent.getOrderId());

        //send the commands
        commandGateway.send(new CreateInvoiceCommand(paymentId, orderConfirmedEvent.getOrderId()));
    }


    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String shippingId = UUID.randomUUID().toString();

        System.out.println("Saga continued");

        //associate Saga with shipping
        SagaLifecycle.associateWith("shippingId", shippingId);

        //send the create shipping command
        commandGateway.send(new CreateShippingCommand(shippingId, invoiceCreatedEvent.orderId, invoiceCreatedEvent.paymentId));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippingCreatedEvent orderShippedEvent){
        SagaLifecycle.end();
    }

}
