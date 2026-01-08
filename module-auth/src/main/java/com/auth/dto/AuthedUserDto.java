package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthedUserDto {

    private Long authSeq;
    private String userEmail;
    private String role;
}
