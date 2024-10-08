package com.dialycodebuffer.OrderService.external.request;


import com.dialycodebuffer.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest
{




    private long orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;




}
