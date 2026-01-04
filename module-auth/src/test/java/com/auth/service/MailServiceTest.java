package com.auth.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendMail() {

        assertThatCode(() -> mailService.sendMail("kyuyoungk@naver.com", "test", "`123"))
                .doesNotThrowAnyException();
    }
}