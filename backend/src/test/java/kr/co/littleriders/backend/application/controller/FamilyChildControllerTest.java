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
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
import kr.co.littleriders.backend.application.facade.FamilyChildFacade;
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
			Child child = Child.of(regist, Gender.MALE, null);
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
}