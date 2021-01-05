package com.springcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		ApiInfo api_info = new ApiInfoBuilder()
				.title("Client service to test Hystrix")
				.description("This is sample service for Hystrix")
				.version("1.0.0") 
				.build();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(api_info)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.springcloud"))
				.paths(PathSelectors.any())
				.build();
	}
	
}
