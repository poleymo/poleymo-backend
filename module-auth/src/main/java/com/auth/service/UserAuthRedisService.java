package com.auth.service;

import com.auth.dto.UserAuthDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthRedisService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    //회원가입 인증전 임시저장
    public String saveTempAuth(UserAuthDto.Request dto) throws JsonProcessingException {
        String token = UUID.randomUUID().toString();
        String key = "auth:temp:" + token;//키로 어떤걸 위해 저장한건지 구분하기 위해 헤더 추가

        //사용자 정보를 10분 제한시간으로 임시저장
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(dto), Duration.ofMinutes(10));
        return token;
    }

    //발급한 토큰으로 데이터를 다시 불러옴
    public UserAuthDto.Request getTempAuth(String token) throws JsonProcessingException {
        String key = "auth:temp:" + token;
        String json = redisTemplate.opsForValue().get(key);

        if (json == null) {
            // 만료되었거나 존재하지 않음
            throw new IllegalArgumentException("이메일 인증 실패");
        }
        redisTemplate.delete(key);

        //string으로 저장된걸 json으로 다시 읽기
        return objectMapper.readValue(json, UserAuthDto.Request.class);
    }
}
