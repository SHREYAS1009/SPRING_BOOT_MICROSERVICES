package com.dialycodebuffer.OrderService.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse
{
    private String productName;
    private long productId;
    private long quantity;
    private long price;

}
