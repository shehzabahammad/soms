package com.soms.user_service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.soms.user_service.entity.CredentialEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.issuer}")
    private String issuer;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String generateToken(CredentialEntity credentialEntity) {
        return JWT.create()
                .withSubject(credentialEntity.getUserName())
                .withClaim("userId", credentialEntity.getId())
                .withClaim("role", credentialEntity.getRole())
                .withClaim("userName", credentialEntity.getUserName())
                .withClaim("emailId", credentialEntity.getEmailId())
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .sign(algorithm);
    }
}
