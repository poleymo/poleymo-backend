package com.auth.controller;

import com.auth.dto.AuthenticationDto;
import com.auth.entity.UserAuth;
import com.auth.service.JwtService;
import com.auth.service.impl.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final UserAuthService userAuthService;

    @PostMapping("refresh")
    public AuthenticationDto.Response refresh(@RequestBody AuthenticationDto.Request request) {
        //토큰 조화
        String authSeq = jwtService.findAuthSeqByRefreshTokenId(request.getRefreshToken());

        //사용자 정보 조회
        UserAuth auth = userAuthService.findAuthById(Long.parseLong(authSeq));

        //at, rt 생성
        String at = jwtService.createAccessToken(auth.getAuthSeq(), auth.getUserEmail(), "ROLE_USER");
        String rt = jwtService.createRefreshToken(auth.getAuthSeq());
        jwtService.deleteToken(request.getRefreshToken());
        return new AuthenticationDto.Response(at, rt);
    }
}
