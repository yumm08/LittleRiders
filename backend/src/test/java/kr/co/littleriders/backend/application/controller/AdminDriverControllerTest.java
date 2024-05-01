package kr.co.littleriders.backend.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyDriverResponse;
import kr.co.littleriders.backend.application.facade.AdminDriverFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
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
class AdminDriverControllerTest {

	@Autowired
	private AdminDriverFacade adminDriverFacade;

	@Autowired
	private AcademyService academyService;

	@Autowired
	private DriverService driverService;

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
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111",33.12,55.12);
			academyService.save(academy);
			DriverRegistRequest regist = new DriverRegistRequest("테스트", "010", null);
			Driver driver = regist.toEntity(academy);
			// Long driverId = driverService.save(driver);

			mockMvc.perform(
					post("/admin/driver")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(1L)))
				.andDo(print());
		}

		@Test
		@DisplayName("실패")
		void whenFailed() throws Exception {
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111",33.12,53.12);
			// academyService.save(academy);
			DriverRegistRequest regist = new DriverRegistRequest("테스트", "010", null);
			Driver driver = regist.toEntity(academy);
			// Long driverId = driverService.save(driver);

			mockMvc.perform(
					post("/admin/driver")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isNotFound())
				.andDo(print());

		}
	}

	@Nested
	@DisplayName("기사 목록 조회 기능")
	class getDriverList {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {

			// 학원 생성
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111", 3, 4);
			academyService.save(academy);

			// 기사 생성
			Driver driver = Driver.of("기사", "010-1111-1110", academy, DriverStatus.WORK);
			driverService.save(driver);

			Driver driver1 = Driver.of("기사1", "010-1111-1111", academy, DriverStatus.RESIGN);
			driverService.save(driver1);

			Driver driver2 = Driver.of("기사2", "010-1111-1112", academy, DriverStatus.WORK);
			driverService.save(driver2);


			List<AcademyDriverResponse> driverList = new ArrayList<>();
			AcademyDriverResponse driverResponse = AcademyDriverResponse.from(driver);
			AcademyDriverResponse driverResponse1 = AcademyDriverResponse.from(driver1);
			AcademyDriverResponse driverResponse2 = AcademyDriverResponse.from(driver2);
			driverList.add(driverResponse);
			driverList.add(driverResponse2);
			driverList.add(driverResponse1);


			mockMvc.perform(
							get("/admin/driver")
					)
					.andExpect(status().isOk())
					.andExpect(content().string(String.valueOf(driverList)))
					.andDo(print());

		}
	}

}