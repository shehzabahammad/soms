package com.soms.gateway_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UserContextFilter implements GlobalFilter {

    @Value("${gateway.context.open-endpoints}")
    private List<String> openEndpoints;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Skip if the path starts with any of the open endpoints
        boolean isOpenEndpoint = openEndpoints.stream().anyMatch(path::startsWith);
        if (isOpenEndpoint) {
            return chain.filter(exchange);
        }

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .switchIfEmpty(Mono.empty())
                .flatMap(authentication -> {
                    if (authentication != null && authentication.isAuthenticated()) {
                        String userId = authentication.getName();

                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(exchange.getRequest().mutate()
                                        .header("X-User-Id", userId)
                                        .build())
                                .build();

                        return chain.filter(mutatedExchange);
                    }

                    return chain.filter(exchange);
                });
    }
}
