package com.komikive.cloud.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

	public MyGatewayFilterFactory(){
		super(Config.class);
	}
	@Override
	public GatewayFilter apply(Config config) {
		return new GatewayFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				ServerHttpRequest request = exchange.getRequest();
				System.out.println("进入自定义MyGatewayFilterFactory,status:"+config.getStatus());

				if(request.getQueryParams().containsKey("komikive")){
					return chain.filter(exchange);
				}else {
					exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
					return exchange.getResponse().setComplete();
				}
			}
		};
	}

	public List<String> shortcutFieldOrder() {
		return Arrays.asList("status");

	}

	public static class Config {

		@Getter@Setter
		private String status;//设定状态值，他等于多少，匹配才能访问

	}

}
