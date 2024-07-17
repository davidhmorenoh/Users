package com.management.users.infrastructure.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class JwtConfigTest {

    @InjectMocks
    private JwtConfig jwtConfig;

    @Test
    public void testGenerateToken() {
        String email = "test@example.com";
        String token = jwtConfig.generateToken(email);

        assertNotNull(token);

        Claims claims = Jwts
                .parser()
                .setSigningKey(getTestKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(email, claims.getSubject());
        assertNotNull(claims.getId());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    @Test
    public void testGetKeyFromPassword() {
        String encodedKey = "TTRuNEczbTNOdCtVczNScytUMGszbitUMHArUzNjUjN0";
        Key key = jwtConfig.getKeyFromPassword(encodedKey);

        assertNotNull(key);

        String email = "test@example.com";
        String token = Jwts.builder()
                .subject(email)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(email, claims.getSubject());
        assertNotNull(claims.getId());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }

    private Key getTestKey() {
        String encodedKey = "TTRuNEczbTNOdCtVczNScytUMGszbitUMHArUzNjUjN0";
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }

}