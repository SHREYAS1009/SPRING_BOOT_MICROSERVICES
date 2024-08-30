package com.dialycodebuffer.OrderService.service;

import com.dialycodebuffer.OrderService.entity.Order;
import com.dialycodebuffer.OrderService.exception.CustomException;
import com.dialycodebuffer.OrderService.external.client.PaymentService;
import com.dialycodebuffer.OrderService.external.client.ProductService;
import com.dialycodebuffer.OrderService.external.request.PaymentRequest;
import com.dialycodebuffer.OrderService.model.OrderRequest;
import com.dialycodebuffer.OrderService.model.OrderResponse;
import com.dialycodebuffer.OrderService.model.PaymentMode;
import com.dialycodebuffer.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Log4j2
@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Override
    public long placeOrder(@RequestBody  OrderRequest orderRequest)
    {
         //use order entity and save data created
        //call product-service to reduce the quantity of product
        //payment service -> payments->successfull else ->cancelled

        log.info("Placing order request",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Order created");
        Order order = Order.builder().amount(orderRequest.getTotalAmount())
                        .orderStatus("CREATED")
                        .productId(orderRequest.getProductId())
                        .orderDate(Instant.now())
                        .quantity(orderRequest.getQuantity())
                        .build();

        order = orderRepository.save(order);

        log.info("Calling payment service to complete");
        PaymentRequest paymentRequest= PaymentRequest.builder()
        .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();


        String orderStatus=null;
        System.out.println(paymentService.doPayment(paymentRequest));
        try{
           paymentService.doPayment(paymentRequest);
           log.info("Payment Request changed to successfull");
           orderStatus="PLACED";

        }catch(Exception e)
        {
            log.error("Error occurred changing status to PAYMENT_failed");
            orderStatus="PAYMENT_failed";

        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);


        log.info("Order placed successfull",order.getId());
        return order.getId();

    }

    @Override
    public OrderResponse getOrderDetails(long orderId)
    {
        /*OrderResponse orderResponse = null;
        OrderRepository orderRepository = null;
         BeanUtils.copyProperties(orderRepository.findById(orderId),orderResponse);
        return orderResponse; */

        Order order=orderRepository.findById(orderId)
                .orElseThrow(()->new CustomException("Order not found","NOT_FOUND",404));
        
        OrderResponse orderResponse=OrderResponse.builder()
        .orderId(order.getId()).orderStatus(order.getOrderStatus())
                        .amount(order.getAmount())
                                .orderDate(order.getOrderDate())
                .build();

        return orderResponse;

    }
}
