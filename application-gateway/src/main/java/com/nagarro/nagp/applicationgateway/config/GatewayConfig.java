package com.nagarro.nagp.applicationgateway.config;

import com.nagarro.nagp.applicationgateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("inventory-management-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://inventory-management-service"))

                .route("inventory-management-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://inventory-management-service"))

                .route("inventory-management-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://inventory-management-service"))

                .route("inventory-management-service", r -> r.path("/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://inventory-management-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .build();
    }


}
