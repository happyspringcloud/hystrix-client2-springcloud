package com.springcloud;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JuiceComponent {
	private final RestTemplate juiceRestTemplate;
	
	@HystrixCommand
	public List<String> getJuice() {
		return juiceRestTemplate
				.exchange(
						"http://localhost:8003/api/juices", 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List <String>>() {}
				).getBody();
	}
}
