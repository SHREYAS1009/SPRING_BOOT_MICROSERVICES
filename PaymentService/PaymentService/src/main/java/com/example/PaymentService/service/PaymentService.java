package com.example.PaymentService.service;

import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService
{
     long doPayment(PaymentRequest paymentRequest);

     PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
