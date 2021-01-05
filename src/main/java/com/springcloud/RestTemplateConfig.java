/*
 * Http client를 Spring Bean으로 생성 - coffeeRestTemplate, juiceRestTemplate
 * 참고) Spring Bean이란 ?
 * - 필요한 객체를 미리 만들어놓고, 필요할 때 꺼내 쓰기 위해 사용하며 DB의 connection pool과 유사한 개념임 
 * - Spring IoC(Inversion Of Control) container에 의해 생성되고 관리됨:IoC는 객체 생명주기 관리와 프로그램 흐름 제어를 개발자가 아닌 스피링 컨테이너가 한다는 의미임  
 * - 생성방법: 1) @Bean/@Configuration, 2) @Component/@Autowired : https://cbw1030.tistory.com/54 참조 
 * - @Bean을 이용하면 메소드 이름으로 생성되고, @Component를 이용하면 class이름으로 생성됨: 생성된 Bean class를 보는 방법은 'HystrixClientSpringcloudApplication' class 참조  
 */
package com.springcloud;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	public RestTemplate getRestTemplate(int defaultMaxPerRoute, int maxTotal) {
		return new RestTemplate() {
			{
				setRequestFactory(new HttpComponentsClientHttpRequestFactory(
						HttpClientBuilder.create().setConnectionManager(new PoolingHttpClientConnectionManager() {
							{
								setDefaultMaxPerRoute(defaultMaxPerRoute);
								setMaxTotal(maxTotal);
							}
						}).build()) {
					{
						setConnectTimeout(2000);
						setReadTimeout(5000);
					}
				});
			}
		};

	/*
	 * 위 수행을 좀 더 쉽게 코딩하면 아래와 같습니다.  
	 * 	PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		connManager.setMaxTotal(maxTotal);

		HttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
		factory.setConnectTimeout(2000);
		factory.setReadTimeout(5000);

		return new RestTemplate(factory);

	 */
	}
	
	@Bean
	public RestTemplate coffeeRestTemplate() {
		return getRestTemplate(20, 10);
	}
	@Bean
	public RestTemplate juiceRestTemplate() {
		return getRestTemplate(30, 10);
	}
}
