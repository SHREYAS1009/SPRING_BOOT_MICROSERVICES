package com.example.PaymentService.service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Log4j2

public class PaymentServiceImpl implements PaymentService{

    @Autowired(required = true)
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest)
    {
        log.info("Payment details recording:",paymentRequest);
        TransactionDetails transactionDetails=TransactionDetails.builder()
        .paymentDate(Instant.now())
         .paymentMode(paymentRequest.getPaymentMode().name())
         .paymentStatus("SUCCESS")
         .orderId(paymentRequest.getOrderId())
        .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())

                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction completed:",transactionDetails.getId());
        return transactionDetails.getId();



    }
}
