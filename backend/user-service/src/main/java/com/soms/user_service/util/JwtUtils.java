package com.soms.user_service.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET = "qwertyuioplkjhgfdsazxcvbnmmnbvcxzasdfgytrewq";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private static final String ISSUER = "codeWithShehzab";

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .sign(algorithm);
    }
}
