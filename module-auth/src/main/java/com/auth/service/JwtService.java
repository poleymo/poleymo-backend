package com.auth.service;

import com.auth.dto.JwtDto;
import com.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final StringRedisTemplate redisTemplate;
    private final JwtProvider jwtProvider;

    //RT 사용 후 삭제 용도
    public void deleteToken(String token) {
        String tokenId = jwtProvider.parseClaims(token).get("tid", String.class);
        redisTemplate.delete(tokenId);
    }

    //todo: 만료 되었는지 확인
    //요청 받은 RT가 서버에 있는지 확인
    public String findToken(String token) {
        String tokenId = jwtProvider.parseClaims(token).get("id", String.class);
        if (tokenId == null) {
            throw new IllegalArgumentException("Token not found");
        }
        return redisTemplate.opsForValue().get(tokenId);
    }


    //액세스 토큰 생성
    public String createAccessToken(Long userSeq, String user, String role) {
        return jwtProvider.createToken(userSeq, user, role);
    }

    //리프레시 토큰 생성 및 저장
    public String createRefreshToken(Long userSeq) {
        JwtDto.RefreshToken dto = jwtProvider.createRefreshToken();//rt 생성
        redisTemplate.opsForValue().set(dto.getId(), String.valueOf(userSeq), Duration.ofDays(30));//uuid -> authSeq
        return dto.getToken();
    }
}
