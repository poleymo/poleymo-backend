package com.auth.service;

import com.auth.dto.UserAuthDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserAuthRedisServiceTest {


    @Autowired
    private UserAuthRedisService userService;

    @Test
    void test() throws JsonProcessingException {
        UserAuthDto.Request ua = UserAuthDto.Request.builder()
                .userEmail("user@auth.com")
                .password("password")
                .active(true)
                .build();

        String s = userService.saveTempAuth(ua);

        UserAuthDto.Request tempAuth = userService.getTempAuth(s);
        System.out.println("tempAuth = " + tempAuth);

    }
}