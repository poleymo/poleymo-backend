package com.auth.dto;

import com.auth.entity.UserAuth;
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
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String userEmail;
        private Boolean active;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private Long authSeq;
        private String userEmail;
        private String password;
        private Boolean active;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Delete {
        private Long authSeq;
        private String userEmail;
        private String password;
        private Boolean active;
    }

    public static Response of(UserAuth userAuth) {
        return new Response(userAuth.getUserEmail(), userAuth.getActive());
    }
}
