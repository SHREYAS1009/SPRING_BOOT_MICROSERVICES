package com.example.PaymentService.service;

import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.PaymentResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService
{

  public long doPayment(@RequestBody PaymentRequest paymentRequest);
  PaymentResponse getPaymentDetailsByOrderId(@RequestBody String orderId);

}
