package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.response.ChildBoardHistory;
import kr.co.littleriders.backend.application.dto.response.ChildBoardHistoryResponse;
import kr.co.littleriders.backend.application.dto.response.ChildDetailHistoryResponse;
import kr.co.littleriders.backend.common.fixture.AcademyFixture;
import kr.co.littleriders.backend.common.fixture.ChildFixture;
import kr.co.littleriders.backend.common.fixture.FamilyFixture;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyFamilyService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.*;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.history.BoardDropHistoryService;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistoryStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
			Academy academy = AcademyFixture.BOXING.toAcademy();
			academyService.save(academy);

			// family 생성
			Family family = FamilyFixture.KIM.toFamily();
			familyService.save(family);
			Long familyId = family.getId();

			// child 생성
			Child child = ChildFixture.KIM_MALE.toChild(family);
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

		}

	}

	@Nested
	@DisplayName("자녀 탑승 상세 조회 기능")
	class readBoardingDetail {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {


			// academy 생성
			Academy academy = AcademyFixture.BOXING.toAcademy();
			academyService.save(academy);

			// family 생성
			Family family = FamilyFixture.KIM.toFamily();
			familyService.save(family);
			Long familyId = family.getId();

			// child 생성
			Child child = ChildFixture.KIM_MALE.toChild(family);
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

//			// history1 생성
//			BoardDropHistory history1 = BoardDropHistory.of(academy, academyChild, 37.5, 127.0396, BoardDropHistoryStatus.DROP);
//			boardDropHistoryService.save(history1);
//
//			// history2 생성
//			BoardDropHistory history2 = BoardDropHistory.of(academy, academyChild, 37.442, 127.0396, BoardDropHistoryStatus.BOARD);
//			boardDropHistoryService.save(history2);

			ChildDetailHistoryResponse childDetailHistoryResponse = ChildDetailHistoryResponse.from(history);

			// test
			mockMvc.perform(
							get("/family/history/" + history.getId())
							// .param("page", S)
					)
					.andExpect(status().isOk())
					.andExpect(content().json(objectMapper.writeValueAsString(childDetailHistoryResponse)))
					.andDo(print());
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