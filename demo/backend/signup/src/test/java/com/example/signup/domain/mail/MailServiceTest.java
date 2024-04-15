package com.example.signup.domain.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;


    @Nested
    @DisplayName("메일 발송 테스트")
    class mail {

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            mailService.sendEmail("test@ruu.kr","제목","내용");
        }


    }
}