package com.auth.jwt;

import com.auth.dto.CustomAuthDetails;
import com.auth.dto.UserAuthDto;
import com.auth.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtService jwtService;// jwt 생성 서비스
    private final ObjectMapper objectMapper;

    public JwtLoginFilter(AuthenticationManager manager, JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        setAuthenticationManager(manager);
        setFilterProcessesUrl("/login");//로그인시 사용할 경로
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserAuthDto.Request authRequest
                    = objectMapper.readValue(request.getInputStream(), UserAuthDto.Request.class);
            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getUserEmail(), authRequest.getPassword());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to authenticate user", e);
        }
    }

    //todo : role 관리
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        CustomAuthDetails principal = (CustomAuthDetails) authResult.getPrincipal();

        String at = jwtService.createAccessToken(principal.getAuthSeq(), principal.getUsername(), "ROLE_USER");
        String rt = jwtService.createRefreshToken(principal.getAuthSeq());
        ResponseCookie refreshTokenCookie = createRefreshTokenCookie(rt);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        Map.of("accessToken", at)
                )
        );
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refresh_token", refreshToken)
                .maxAge(Duration.ofDays(30))
                .httpOnly(true)//개발자 콘솔에서 읽지 못하게
                .secure(false)//https 필수 옵션인데 지금 없으니 일단 false
                .sameSite("None")//요청부와 응답부 도메인이 같아야하는가?
                .path("/auth/refresh")//이 경로로 사작하는 요청에만 이 쿠키를 자동으로 포함 시킴
                .build();
    }
}
