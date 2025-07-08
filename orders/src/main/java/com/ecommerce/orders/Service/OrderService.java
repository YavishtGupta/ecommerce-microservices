package com.ecommerce.orders.Service;

import com.ecommerce.orders.Dto.OrderRequest;
import com.ecommerce.orders.Dto.OrderResponse;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
}
