package com.ecommerce.orders.Events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class OrderPlacedEvent {
    private final Long customerId;
    private final String customerName;
    private final List<Long> productId;
}