package com.management.users.infrastructure.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtConfig {

    public String generateToken(String email) {
        String SECRET_KEY = "TTRuNEczbTNOdCtVczNScytUMGszbitUMHArUzNjUjN0";
        return Jwts.builder()
                .subject(email)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getKeyFromPassword(SECRET_KEY))
                .compact();
    }

    public Key getKeyFromPassword(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }

}