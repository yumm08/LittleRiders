package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.ChildFixture;
import kr.co.littleriders.backend.common.fixture.FamilyFixture;
import kr.co.littleriders.backend.domain.academy.AcademyChildServiceDeprecated;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyChildControllerTest {

    @Autowired
    private FamilyChildFacade familyChildFacade;

    @Autowired
    private AcademyFamilyService academyFamilyService;

    @Autowired
    private AcademyChildServiceDeprecated academyChildServiceDeprecated;

    @Autowired
    private AcademyService academyService;

    @Autowired
    private ChildService childService;

    @Autowired
    private FamilyService familyService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Nested
    @DisplayName("자녀 추가 기능 테스트")
    class addChild {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Family family = FamilyFixture.KIM.toFamily();
            familyService.save(family);
            Child child = ChildFixture.KIM_MALE.toChild(family);
            ChildRegistRequest regist = ChildFixture.KIM_MALE.toChildRegistRequest();
            Long childId = childService.save(child);


            mockMvc.perform(
                            post("/family/child")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(regist))
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(childId)))
                    .andDo(print());

        }
    }

    @Nested
    @DisplayName("자녀 목록 조회 기능 테스트")
    class getChildList {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            Family family = FamilyFixture.KIM.toFamily();
            familyService.save(family);
            ChildRegistRequest regist1 = ChildFixture.KIM_MALE.toChildRegistRequest();
            ChildRegistRequest regist2 = ChildFixture.KIM_FEMALE.toChildRegistRequest();
            familyChildFacade.insertChild(regist1, family.getId());
            familyChildFacade.insertChild(regist2, family.getId());

            Child c1 = childService.findById(1);
            Child c2 = childService.findById(2);
            ChildListResponse child1 = ChildListResponse.from(c1);
            ChildListResponse child2 = ChildListResponse.from(c2);

            List<ChildListResponse> childList = new ArrayList<ChildListResponse>();
            childList.add(child1);
            childList.add(child2);

            mockMvc.perform(
                            get("/family/child")
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(childList)))
                    .andDo(print());

        }
    }

    @Nested
    @DisplayName("자녀 상세 조회 기능 테스트")
    class getChildDetail {

        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            // academy 생성
            Academy academy = AcademyFixture.BOXING.toAcademy();
            academyService.save(academy);

            // academy 생성

            Academy academy1 = AcademyFixture.BASEBALL.toAcademy();
            academyService.save(academy1);

            // family 생성
            Family family = FamilyFixture.KIM.toFamily();
            familyService.save(family);

            // child 생성
            Child child = ChildFixture.KIM_MALE.toChild(family);
            childService.save(child);

            // academyFamily 생성
            AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
            academyFamilyService.save(academyFamily);

            // academyChild 생성
            AcademyChildDeprecated academyChildDeprecated = AcademyChildDeprecated.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
            academyChildServiceDeprecated.save(academyChildDeprecated);

            // academyFamily 생성
            AcademyFamily academyFamily1 = AcademyFamily.of(family, academy1, AcademyFamilyStatus.AVAIL);
            academyFamilyService.save(academyFamily1);

            // academyChild 생성
            AcademyChildDeprecated academyChildDeprecated1 = AcademyChildDeprecated.of(child, academy1, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
            academyChildServiceDeprecated.save(academyChildDeprecated1);

            List<AcademyList> academyList = new ArrayList<>();
            AcademyList academyResponse = AcademyList.from(academy);
            AcademyList academyResponse1 = AcademyList.from(academy1);
            academyList.add(academyResponse);
            academyList.add(academyResponse1);

            ChildDetailResponse childDetailResponse = ChildDetailResponse.of(child, null, academyList);

            mockMvc.perform(
                            get("/family/child/" + child.getId())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(childDetailResponse)))
                    .andDo(print());

        }
    }
}