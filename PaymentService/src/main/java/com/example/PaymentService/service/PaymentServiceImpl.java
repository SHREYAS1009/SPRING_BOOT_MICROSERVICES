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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService
{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
   @Override

    public long doPayment(@RequestBody PaymentRequest paymentRequest)
   {
       log.info("Payment processing......");
       TransactionDetailsRepository transactionDetailsRepository=null;
       TransactionDetails transactionDetails =TransactionDetails.builder()
           .orderId(paymentRequest.getOrderId())
           .paymentMode(paymentRequest.getPaymentMode().name())
           .referenceNumber(paymentRequest.getReferenceNumber())
           .paymentDate(Instant.now())
           .paymentStatus("SUCCESS")
           .amount(paymentRequest.getAmount())
        .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Detail completed");
        return transactionDetails.getId();

   }
    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId)
    {
        PaymentMode paymentMode;
        log.info("Getting payment details for the orderId");
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));
        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        return paymentResponse;

    }
}
