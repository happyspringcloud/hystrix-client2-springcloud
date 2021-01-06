package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableCircuitBreaker
public class HystrixClientSpringcloudApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HystrixClientSpringcloudApplication.class, args);
		System.out.println("Beans==>"+String.join(", ", context.getBeanDefinitionNames()));
	}

}
