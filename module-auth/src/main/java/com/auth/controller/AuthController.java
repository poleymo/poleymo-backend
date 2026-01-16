package com.auth.controller;

import com.auth.dto.AuthenticationDto;
import com.auth.dto.JwtDto;
import com.auth.entity.UserAuth;
import com.auth.service.JwtService;
import com.auth.service.impl.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final UserAuthService userAuthService;

    @PostMapping("refresh")
    public ResponseEntity<AuthenticationDto.Response> refresh(@CookieValue("refresh_token") String refreshToken) {
        //토큰 조화
        String authSeq = jwtService.findAuthSeqByRefreshTokenId(refreshToken);

        //사용자 정보 조회
        UserAuth auth = userAuthService.findAuthById(Long.parseLong(authSeq));

        //at, rt 생성
        String at = jwtService.createAccessToken(auth.getAuthSeq(), auth.getUserEmail(), "ROLE_USER");
        JwtDto.RefreshToken rt = jwtService.createRefreshToken(auth.getAuthSeq());


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, rt.getTokenString())
                .body(new AuthenticationDto.Response(at));
    }
}
