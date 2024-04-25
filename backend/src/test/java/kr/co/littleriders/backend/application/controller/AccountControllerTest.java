package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.function.ServerRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    FamilyService familyService;


    @Autowired
    PasswordUtil passwordUtil;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Nested
    @DisplayName("reIssue 테스트")
    class reIssue {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            //회원가입 후
            //로그인 한 다음
            String email = "test@example.com";
            String password = "1234";
            String name = "54";
            String phoneNumber = "123456";
            Family family = new FamilySignUpRequest(
                    email,
                    password,
                    name,
                    phoneNumber,
                    "1234"
            ).toFamily(
                    passwordUtil
            );
            familyService.save(family);
            SignInRequest signInRequest = SignInRequest.of(email, password);
            MvcResult signInResult = mockMvc.perform(
                            post("/family/account/signIn")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(signInRequest))

                    )
                    .andExpect(status().isOk())
                    .andExpect(header().exists("Authorization"))
                    .andDo(print())
                    .andReturn();

            Cookie cookie = signInResult.getResponse().getCookie("refresh-token");
            String acessToken = signInResult.getResponse().getHeader("Authorization");
            log.info("accessToken=[{}]", acessToken);
            MvcResult reIssueResult = mockMvc.perform(
                            get("/account/reIssue")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .cookie(cookie)
                    )
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn();

            String signInRefresh = cookie.getValue();
            String reIssueRefresh = reIssueResult.getResponse().getCookie("refresh-token").getValue();
            assertNotEquals(signInRefresh,reIssueRefresh);
        }
    }
}