package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.application.facade.FamilyAccountFacade;
import kr.co.littleriders.backend.global.jwt.JwtToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@AutoConfigureRestDocs
//@AutoConfigureMockMvc
@WebMvcTest(FamilyAccountController.class)
class FamilyAccountControllerTest {

    @MockBean
    FamilyAccountFacade familyAccountFacade;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("sendSignUpVerificationMail 테스트")
    class sendSignUpVerificationMail {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            //given

            mockMvc.perform(get("/family/account/validate")
                            .param("email", "example@example.com")
                            .contentType(MediaType.APPLICATION_JSON))
//                    .andDo(
//                            document("Family/Account",
//                                    preprocessRequest(prettyPrint()),
//                                    preprocessResponse(prettyPrint()),
//                                    queryParameters(
//                                            parameterWithName("email").description("이메일")
//                                    ),
//                                    responseFields(
//                                            fieldWithPath("message").description("실행결과, 성공적으로 메일이 발송되었습니다.")
//                                    )
//                            )
//
//                    )

                    .andExpect(status().isOk())
                    .andDo(print());

        }
    }

    @Nested
    @DisplayName("signIn 테스트")
    class signIn {


        @Test

        @DisplayName("성공")
        void whenSuccess() throws Exception {
            JwtToken jwtToken = JwtToken.of("apple", 1234, "banana", 12345);

            given(familyAccountFacade.signIn(any())).willReturn(
                    jwtToken
            );

            SignInRequest signInRequest = SignInRequest.of("apple", "1234");


            mockMvc.perform(
                            post("/family/account/signIn")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(signInRequest))

                    )
                    .andExpect(status().isOk())
                    .andExpect(header().exists("Authorization"))
                    .andDo(print());


        }


    }


}