package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class UserDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private Long userSeq;
        private String userName;
        private String userEmail;
        private String userPhone;
        private Date birthday;

        private Integer grade; //학년
        private Long studentId; //학번
        private String subject; //학과
        private String campus;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long userSeq;
        private String userName;
        private String userEmail;
        private String userPhone;
        private Date birthday;

        private Integer grade; //학년
        private Long studentId; //학번
        private String subject; //학과
        private String campus;
    }
}
