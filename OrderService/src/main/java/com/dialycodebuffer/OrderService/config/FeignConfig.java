package com.dialycodebuffer.OrderService.config;

import com.dialycodebuffer.OrderService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder()
    {
       return new CustomErrorDecoder();
    }
}
