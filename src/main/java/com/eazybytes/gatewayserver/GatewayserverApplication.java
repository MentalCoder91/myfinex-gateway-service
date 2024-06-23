package com.eazybytes.gatewayserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myFinExRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/myfinEx/users/**")
						.filters(f -> f.rewritePath("/myfinEx/users/(?<segment>.*)", "/users/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://USER-SERVICE"))
				.route(p -> p
						.path("/myfinEx/budget/**")
						.filters(f -> f.rewritePath("/myfinEx/budget/(?<segment>.*)", "/budget/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://BUDGET-SERVICE"))
				.route(p -> p
						.path("/myfinEx/expense/**")
						.filters(f -> f.rewritePath("/myfinEx/expense/(?<segment>.*)", "/expense/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://EXPENSE-SERVICE"))
				.build();




	}







}
