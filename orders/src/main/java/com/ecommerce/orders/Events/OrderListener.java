package com.ecommerce.orders.Events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Async
    @EventListener
    public void sendEmail(OrderPlacedEvent event){
        System.out.println("Email sent to "+ event.getCustomerName());
    }

    @Async
    @EventListener
    public void sendMessage(OrderPlacedEvent event){
        System.out.println("Message sent successfully to " + event.getCustomerName());
    }

}
