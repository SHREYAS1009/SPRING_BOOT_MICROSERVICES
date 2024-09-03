package com.example.PaymentService.service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentMode;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.model.PaymentResponse;
import com.example.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
@Log4j2

public class PaymentServiceImpl implements PaymentService{

    @Autowired(required = true)
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(@RequestBody PaymentRequest paymentRequest)
    {
        log.info("Payment details recording: {}", paymentRequest);
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

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId)
    {
        log.info("Getting payment deatils for Order ID");
        //PaymentResponse paymentResponse = new PaymentResponse();
       // paymentResponse.builder().build();
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .status(transactionDetails.getPaymentStatus())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .amount(transactionDetails.getAmount())
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .build();


        return paymentResponse;
    }
}
