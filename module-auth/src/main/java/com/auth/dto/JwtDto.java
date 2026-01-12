package com.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class JwtDto {

    @Getter
    @RequiredArgsConstructor
    public static class RefreshToken {
        private final String id;
        private final String token;
    }
}
