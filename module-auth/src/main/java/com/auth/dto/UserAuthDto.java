package com.auth.dto;

import com.auth.entity.User;
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
        private User user;
    }
}
