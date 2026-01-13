package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AuthenticationDto {

    @Getter
    @AllArgsConstructor
    public static class Request {
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String refreshToken;
        private String accessToken;
    }
}
