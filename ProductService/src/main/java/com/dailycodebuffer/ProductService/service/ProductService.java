package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ProductService {
    @Autowired

    public long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long ProductId);
    void reduceQuantity(long productId, long quantity);
}
