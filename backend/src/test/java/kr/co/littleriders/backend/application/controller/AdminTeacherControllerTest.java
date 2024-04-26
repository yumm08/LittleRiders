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

import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.application.facade.AdminDriverFacade;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.DriverService;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;

@SpringBootTest
@AutoConfigureMockMvc
class AdminTeacherControllerTest {

	@Autowired
	private AcademyService academyService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Nested
	@DisplayName("선탑자 추가 기능")
	class addTeacher {

		@Test
		@DisplayName("성공")
		void whenSuccess() throws Exception {
			Academy academy = Academy.of("test@com", "password", "테스트학원", "테스트시 테스트동", "010-1111");
			academyService.save(academy);
			TeacherRegistRequest regist = new TeacherRegistRequest("테스트", "010", null);
			Teacher teacher = regist.toEntity(academy);
			Long teacherId = teacherService.save(teacher);

			mockMvc.perform(
					post("/admin/teacher")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(regist))
				)
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(teacherId)))
				.andDo(print());

		}
	}
}