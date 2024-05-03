package kr.co.littleriders.backend.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyList;
import kr.co.littleriders.backend.application.dto.response.ChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamilyStatus;
import kr.co.littleriders.backend.domain.academy.entity.CardType;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyChildControllerTest {

	@Autowired
	private FamilyChildFacade familyChildFacade;

	@Autowired
	private AcademyFamilyService academyFamilyService;

	@Autowired
	private AcademyChildService academyChildService;

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
			Family family = Family.of("test@com", "password", "테스트부모", "테스트시 테스트동", "010-1111");
			familyService.save(family);
			ChildRegistRequest regist = new ChildRegistRequest("테스트", LocalDate.of(2024, 4,26), "MALE", null);
			Child child = regist.toEntity(family);
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
			Family family = Family.of("test@com", "password", "테스트부모", "테스트시 테스트동", "010-1111");
			familyService.save(family);
			ChildRegistRequest regist1 = new ChildRegistRequest("테스트1", LocalDate.of(2024, 4,26), "MALE", null);
			ChildRegistRequest regist2 = new ChildRegistRequest("테스트2", LocalDate.of(2024, 4,26), "FEMALE", null);
			familyChildFacade.insertChild(regist1, 1L);
			familyChildFacade.insertChild(regist2, 1L);

			ChildListResponse child1 = new ChildListResponse(1L, "테스트1", LocalDate.of(2024, 4,26), "MALE", null);
			ChildListResponse child2 = new ChildListResponse(2L, "테스트2", LocalDate.of(2024, 4,26), "FEMALE", null);
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
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111", 3, 2);
			academyService.save(academy);

			// academy 생성
			Academy academy1 = Academy.of("test1@com", "password", "테스트학원1", "테스트시 테스트동", "010-1111", 3, 2);
			academyService.save(academy1);

			// family 생성
			Family family = Family.of("test2@com", "password", "테스트부모1", "테스트시 테스트동", "010-1112");
			familyService.save(family);

			// child 생성
			Child child = Child.of("테스트아이", LocalDate.of(2024, 4,26), Gender.MALE, family);
			childService.save(child);

			// academyFamily 생성
			AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
			academyFamilyService.save(academyFamily);

			// academyChild 생성
			AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
			academyChildService.save(academyChild);

			// academyFamily 생성
			AcademyFamily academyFamily1 = AcademyFamily.of(family, academy1, AcademyFamilyStatus.AVAIL);
			academyFamilyService.save(academyFamily1);

			// academyChild 생성
			AcademyChild academyChild1 = AcademyChild.of(child, academy1, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
			academyChildService.save(academyChild1);

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