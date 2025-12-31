package com.auth.service;

import com.auth.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptor {
    private final PasswordEncoder passwordEncoder;

    public UserAuthDto.Request encrypt(UserAuthDto.Request dto) {
        return UserAuthDto.Request.builder()
                .userEmail(dto.getUserEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
}
