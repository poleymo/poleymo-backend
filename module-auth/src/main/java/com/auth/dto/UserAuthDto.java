package com.auth.dto;

import lombok.*;

public class UserAuthDto {
    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String userEmail;
        private String password;
        private Boolean active;
    }
}
