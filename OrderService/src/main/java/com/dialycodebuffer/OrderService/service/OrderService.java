package com.dialycodebuffer.OrderService.service;

import com.dialycodebuffer.OrderService.model.OrderRequest;
import com.dialycodebuffer.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
