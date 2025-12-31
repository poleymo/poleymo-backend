package com.auth.service;

import com.auth.dto.UserAuthDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PasswordEncryptorTest {
    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Test
    void encryptTest() {
        UserAuthDto.Request req = UserAuthDto.Request.builder()
                .userEmail("test@test.com")
                .password("password")
                .build();
        UserAuthDto.Request encrypt = passwordEncryptor.encrypt(req);
        assertThat(req.getPassword()).isNotEqualTo(encrypt.getPassword());
    }
}