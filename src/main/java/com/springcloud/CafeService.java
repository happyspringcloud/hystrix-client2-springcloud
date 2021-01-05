package com.springcloud;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CafeService {
	private final CoffeeComponent coffeeComponent;
	private final JuiceComponent juiceComponent;
	
	public List<String> getCoffee(String param) {
		return coffeeComponent.getCoffee(param);
	}
	
	public List<String> getJuice() {
		return juiceComponent.getJuice();
	}
	
}
