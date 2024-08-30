package com.dailycodebuffer.ProductService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class ProductRequest
{

    @Id
    private Long productId;
    private String name;
    private long price;
    private long quantity;

}
