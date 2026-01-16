package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthenticationDto {
    @Getter
    @AllArgsConstructor
    public static class Response {
        private String accessToken;
    }
}
