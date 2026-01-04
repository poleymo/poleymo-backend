package com.auth.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {
    private final JwtProvider jwtProvider = new JwtProvider("secretsecretsecretsecretsecretsecretsecretsecretsecretsecret");
    private final String token = jwtProvider.createToken(1L, "GUEST");
    @Test
    void createToken() {
        assertNotNull(token);
    }

    @Test
    void claimToken() {
        Claims claims = jwtProvider.parseClaims(token);
        assertThat(claims.containsValue("GUEST")).isTrue();
    }

    @Test
    void validateToken() {
        assertThat(jwtProvider.validateToken(token)).isTrue();
    }
}