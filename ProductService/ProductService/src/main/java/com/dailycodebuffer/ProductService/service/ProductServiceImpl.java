package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.exception.ProductServiceCustomException;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest)
    {
        log.info("Adding Product.........");
        Product  product = Product.builder()
                           .productName(productRequest.getName())
                          .quantity(productRequest.getQuantity())
                          .price(productRequest.getPrice()).build();
        productRepository.save(product);
        log.info("Product Added..............");
        return product.getProductId();
    }
    @Override
    public ProductResponse getProductById(long ProductId)
    {
        log.info("Find Product By ID"+"{id}:  "+ProductId);
        Product product = productRepository.findById(ProductId).orElseThrow(()->new ProductServiceCustomException("Product not Found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity)
    {
        log.info("Reduce quantity {} for {} id",quantity,productId);
        Product product = productRepository.findById(productId).
                orElseThrow(()-> new ProductServiceCustomException("PRODUCT with id NOT FOUND","product_not_found"));

        if(product.getQuantity()<quantity)
            throw new ProductServiceCustomException("Not sufficient products to place order","Insufficient product");

        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product details updated");
    }

}
