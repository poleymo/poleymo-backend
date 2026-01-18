package com.auth.controller;

import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import com.auth.service.MailService;
import com.auth.service.PasswordEncryptor;
import com.auth.service.UserAuthRedisService;
import com.auth.service.impl.UserAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("signup")
@RequiredArgsConstructor
public class SignUpController {
    private final UserAuthRedisService userAuthRedisService;
    private final UserAuthService userAuthService;
    private final PasswordEncryptor passwordEncryptor;
    private final MailService mailService;

    @PostMapping
    public String signUp(@RequestBody UserAuthDto.Request dto) throws JsonProcessingException, MessagingException {
        String token = userAuthRedisService.saveTempAuth(passwordEncryptor.encrypt(dto));
        mailService.sendMail(dto.getUserEmail(), "폴리모 회원가입 인증 메일", token);
        return token;
    }

    @GetMapping
    public UserAuthDto.Response verify(String token) throws JsonProcessingException {
        userAuthRedisService.getTempAuth(token);
        UserAuthDto.Request dto = userAuthRedisService.getTempAuth(token);
        UserAuth userAuth = userAuthService.saveAuth(dto);
        userAuthRedisService.deleteTemp(token);

        return UserAuthDto.of(userAuth);
    }
}
