package com.soms.gateway_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${gateway.open-endpoints}")
    private List<String> openEndpoints;

    @Bean
    public JwtAuthenticationManager jwtAuthenticationManager() {
        return new JwtAuthenticationManager();
    }

    @Bean
    public JwtSecurityContext jwtSecurityContext() {
        return new JwtSecurityContext();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, JwtAuthenticationManager jwtAuthenticationManager, JwtSecurityContext jwtSecurityContext) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(openEndpoints.toArray(new String[0])).permitAll()
                        .anyExchange().authenticated())
                .authenticationManager(jwtAuthenticationManager)
                .securityContextRepository(jwtSecurityContext)
                .build();
    }
}
