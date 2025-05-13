package com.soms.gateway_service.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
        boolean isOpenEndpoint = openEndpoints.stream().anyMatch(path::startsWith);

        if (isOpenEndpoint) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return chain.filter(exchange); // or reject request
        }

        String token = authHeader.substring(7); // Remove "Bearer "

        try {
            DecodedJWT jwt = JWT.decode(token);

            String userId = String.valueOf(jwt.getClaim("userId").asInt());
            String role = jwt.getClaim("role").asString();
            String email = jwt.getClaim("emailId").asString();
            String userName = jwt.getClaim("userName").asString();

            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User-Id", userId)
                    .header("X-User-Role", role)
                    .header("X-User-Email", email)
                    .header("X-User-Username", userName)
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);

        } catch (Exception e) {
            // Optionally log and reject malformed token
            return chain.filter(exchange);
        }
    }
}
