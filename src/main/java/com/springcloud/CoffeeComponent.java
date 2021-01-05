package com.springcloud;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoffeeComponent {
	private final RestTemplate coffeeRestTemplate;
	
	@HystrixCommand(fallbackMethod="getCoffeeFallback")
	public List<String> getCoffee(String param) {
		return coffeeRestTemplate
				.exchange(
						"http://localhost:8003/api/coffees/"+param, 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List <String>>() {}
				).getBody();
	}
	
	public List<String> getCoffeeFallback(String param, Throwable t) {
		System.err.println("#######################");
		log.info(t.toString());
		System.err.println("#######################");
		
		return Collections.emptyList();
	}
}
