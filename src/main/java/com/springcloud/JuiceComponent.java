package com.springcloud;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@RefreshScope
public class JuiceComponent {
	private final RestTemplate juiceRestTemplate;
	
	@Value("${hystrixServiceHost:http://localhost:8003}")
	private String hystrixServiceHost;
	
	@HystrixCommand
	public List<String> getJuice() {
		return juiceRestTemplate
				.exchange(
						hystrixServiceHost+"/api/juices", 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List <String>>() {}
				).getBody();
	}
}
