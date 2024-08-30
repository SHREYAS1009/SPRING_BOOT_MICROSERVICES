package com.dialycodebuffer.OrderService;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

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

}
