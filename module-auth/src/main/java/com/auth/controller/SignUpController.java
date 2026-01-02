package com.auth.controller;

import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import com.auth.service.PasswordEncryptor;
import com.auth.service.UserAuthRedisService;
import com.auth.service.impl.UserAuthService;
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
    private final UserAuthService userAuthService;
    private final PasswordEncryptor passwordEncryptor;

    @PostMapping
    public String signUp(UserAuthDto.Request dto) throws JsonProcessingException {
        //todo: 이메일 전송
        return userAuthRedisService.saveTempAuth(passwordEncryptor.encrypt(dto));
    }

    @GetMapping
    public UserAuthDto.Response verify(String token) throws JsonProcessingException {
        userAuthRedisService.getTempAuth(token);
        UserAuthDto.Request dto = userAuthRedisService.getTempAuth(token);
        UserAuth userAuth = userAuthService.saveAuth(dto);

        return UserAuthDto.of(userAuth);
    }
}
