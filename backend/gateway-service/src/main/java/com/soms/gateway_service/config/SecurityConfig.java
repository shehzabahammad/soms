package com.soms.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final List<String> openEndpoints = List.of("/user/api/v1/ping", "/user/api/v1/auth/login");

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, JwtAuthenticationManager jwtAuthenticationManager) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(openEndpoints.toArray(new String[0])).permitAll()
                        .anyExchange().authenticated())
                .authenticationManager(new JwtAuthenticationManager())
                .securityContextRepository(new JwtSecurityContext())
                .build();
    }
}
