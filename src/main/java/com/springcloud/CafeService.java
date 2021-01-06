package com.springcloud;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CafeService {
	private final CoffeeComponent2 coffeeComponent;
	private final JuiceComponent2 juiceComponent;
	
	public List<String> getCoffee(String param) {
		return coffeeComponent.getCoffee2(param);
	}
	
	public List<String> getJuice() {
		return juiceComponent.getJuice2();
	}
	
}
