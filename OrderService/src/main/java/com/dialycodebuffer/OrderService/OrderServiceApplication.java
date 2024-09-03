package com.dialycodebuffer.OrderService;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaServer
@EnableFeignClients
public class OrderServiceApplication
{

	private String serverPort;
	public static void main(String[] args)
	{

		SpringApplication.run(OrderServiceApplication.class, args);


	}

    @Bean
	@LoadBalanced

	public RestTemplate restTemplate()
	{
		return new RestTemplate();

	}


}
