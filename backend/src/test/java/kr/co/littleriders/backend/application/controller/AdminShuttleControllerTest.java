package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyShuttleResponse;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
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
class AdminShuttleControllerTest {

	@Autowired
	private AcademyService academyService;

	@Autowired
	private ShuttleService shuttleService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("셔틅ㅇ 추가 기능")
	class addShuttle {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111",3,4);
			academyService.save(academy);
			ShuttleRegistRequest regist = new ShuttleRegistRequest("테스트번호License", "차종류", "1호차테스트", null);
			Shuttle shuttle = regist.toEntity(academy);
			Long driverId = shuttleService.save(shuttle);

			mockMvc.perform(
					post("/admin/shuttle")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(driverId)))
				.andDo(print());

		}
	}

	@Nested
	@DisplayName("셔틀 목록 조회 기능")
	class getShuttleList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			// 학원 생성
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111", 3, 4);
			academyService.save(academy);

			// 기사 생성
			Shuttle shuttle = Shuttle.of("TESTLICENSE", "1호차", "차량", academy, ShuttleStatus.USE);
			shuttleService.save(shuttle);

			Shuttle shuttle1 = Shuttle.of("TESTLICENSE1", "2호차", "차량", academy, ShuttleStatus.REPAIRING);
			shuttleService.save(shuttle1);

			Shuttle shuttle2 = Shuttle.of("TESTLICENSE2", "3호차", "차량", academy, ShuttleStatus.USE);
			shuttleService.save(shuttle2);


			List<AcademyShuttleResponse> shuttleList = new ArrayList<>();
			AcademyShuttleResponse shuttleResponse = AcademyShuttleResponse.from(shuttle);
			AcademyShuttleResponse shuttleResponse1 = AcademyShuttleResponse.from(shuttle1);
			AcademyShuttleResponse shuttleResponse2 = AcademyShuttleResponse.from(shuttle2);
			shuttleList.add(shuttleResponse);
			shuttleList.add(shuttleResponse2);
			shuttleList.add(shuttleResponse1);


			mockMvc.perform(
							get("/admin/shuttle")
					)
					.andExpect(status().isOk())
					.andExpect(content().string(String.valueOf(shuttleList)))
					.andDo(print());

		}
	}

}