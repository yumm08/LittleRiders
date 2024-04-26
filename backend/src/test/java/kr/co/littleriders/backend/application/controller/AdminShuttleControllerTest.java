package kr.co.littleriders.backend.application.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;

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
	@DisplayName("운전자 추가 기능")
	class addDriver {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111");
			academyService.save(academy);
			ShuttleRegistRequest regist = new ShuttleRegistRequest("테스트번호License", "차종류", "1호차테스트", null);
			Shuttle shuttle = Shuttle.of(regist, academy, ShuttleStatus.USE);
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
}