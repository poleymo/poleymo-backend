package com.auth.controller;

import com.auth.dto.UserAuthDto;
import com.auth.service.PasswordEncryptor;
import com.auth.service.UserAuthRedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {
    private final UserAuthRedisService userAuthRedisService;
    private final PasswordEncryptor passwordEncryptor;

    @PostMapping
    public String signUp(UserAuthDto.Request dto) throws JsonProcessingException {
        return userAuthRedisService.saveTempAuth(passwordEncryptor.encrypt(dto));
    }

    @GetMapping
    public String verify(String token) throws JsonProcessingException {
        userAuthRedisService.getTempAuth(token);
        //여기서 영속화
        return "ok";
    }
}
