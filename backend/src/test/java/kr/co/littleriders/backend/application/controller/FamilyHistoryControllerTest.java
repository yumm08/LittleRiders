package kr.co.littleriders.backend.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.littleriders.backend.application.dto.response.ChildBoardHistory;
import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildDetailHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildListResponse;
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
import kr.co.littleriders.backend.domain.history.BoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistoryStatus;
import kr.co.littleriders.backend.global.entity.Gender;

@SpringBootTest
@AutoConfigureMockMvc
class FamilyHistoryControllerTest {

	@Autowired
	private ChildService childService;

	@Autowired
	private FamilyService familyService;

	@Autowired
	private AcademyService academyService;

	@Autowired
	private AcademyFamilyService academyFamilyService;

	@Autowired
	private AcademyChildService academyChildService;

	@Autowired
	private BoardDropHistoryService boardDropHistoryService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("자녀 탑승 목록 조회 기능")
	class readBoardingList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			// academy 생성
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111");
			academyService.save(academy);

			// family 생성
			Family family = Family.of("test1@com", "password", "테스트부모1", "테스트시 테스트동", "010-1112");
			familyService.save(family);
			Long familyId = family.getId();

			// child 생성
			Child child = Child.of("테스트아이", LocalDate.of(2024, 4,26), Gender.MALE, family);
			childService.save(child);

			// academyFamily 생성
			AcademyFamily academyFamily = AcademyFamily.of(family, academy, AcademyFamilyStatus.AVAIL);
			academyFamilyService.save(academyFamily);

			// academyChild 생성
			AcademyChild academyChild = AcademyChild.of(child, academy, academyFamily, AcademyChildStatus.ATTENDING, CardType.BEACON);
			academyChildService.save(academyChild);

			// history 생성
			BoardDropHistory history = BoardDropHistory.of(academy, academyChild, 37.50142, 127.0396, BoardDropHistoryStatus.BOARD);
			boardDropHistoryService.save(history);

			// history1 생성
			BoardDropHistory history1 = BoardDropHistory.of(academy, academyChild, 37.5, 127.0396, BoardDropHistoryStatus.DROP);
			boardDropHistoryService.save(history1);

			// history2 생성
			BoardDropHistory history2 = BoardDropHistory.of(academy, academyChild, 37.442, 127.0396, BoardDropHistoryStatus.BOARD);
			boardDropHistoryService.save(history2);

			ChildBoardHistory childBoardHistory = ChildBoardHistory.from(history);
			ChildBoardHistory childBoardHistory1 = ChildBoardHistory.from(history1);
			ChildBoardHistory childBoardHistory2 = ChildBoardHistory.from(history2);
			List<ChildBoardHistory> childBoardHistoryList = new ArrayList<>();
			childBoardHistoryList.add(childBoardHistory);
			childBoardHistoryList.add(childBoardHistory1);
			childBoardHistoryList.add(childBoardHistory2);

			ChildBoardHistoryResponse childBoardHistoryResponse = ChildBoardHistoryResponse.of(childBoardHistoryList, 0, false);

			// test
			mockMvc.perform(
					get("/family/history/child/" + child.getId())
						// .param("page", S)
				)
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(childBoardHistoryResponse)))
				.andDo(print());
		}

		@Test
		@DisplayName("실패")
		void whenFailed() throws Exception {

			// family 생성

			// family1 생성

			// family1 - child 생성

			// history 생성
			// history1 생성
			// history2 생성

			// test
		}

	}

	@Nested
	@DisplayName("자녀 탑승 상세 조회 기능")
	class readBoardingDetail {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			// family 생성

			// child 생성

			// history 생성



			// test
		}

		@Test
		@DisplayName("실패")
		void whenFailed() throws Exception {

			// family 생성

			// family1 생성

			// family1 - child 생성

			// history - child 생성


			// test
		}

	}

}