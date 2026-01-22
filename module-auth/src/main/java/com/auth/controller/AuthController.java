package com.auth.controller;

import com.auth.dto.AuthedUserDto;
import com.auth.dto.AuthenticationDto;
import com.auth.dto.JwtDto;
import com.auth.dto.UserAuthDto;
import com.auth.entity.UserAuth;
import com.auth.service.JwtService;
import com.auth.service.impl.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final UserAuthService userAuthService;

    @PostMapping("refresh")
    public ResponseEntity<AuthenticationDto.Response> refresh(@CookieValue("refresh_token") String refreshToken) {
        //토큰 조회
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

    //인증 pk가 바뀌었기 때문에 재로그인 되어야함
    //프론트에서 제어하는게 적절할 듯
    @PatchMapping
    public ResponseEntity<Void> updatePassword(@AuthenticationPrincipal AuthedUserDto user,
                                                 UserAuthDto.Update dto) {
        userAuthService.changePassword(user, dto);
        return ResponseEntity.noContent().build();
    }
}
