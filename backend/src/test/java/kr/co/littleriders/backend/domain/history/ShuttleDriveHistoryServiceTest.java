package kr.co.littleriders.backend.domain.history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.history.entity.ShuttleDriveHistory;
import kr.co.littleriders.backend.domain.shuttle.dto.ShuttleLocationDTO;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class ShuttleDriveHistoryServiceTest {
    @Autowired
    ShuttleDriveHistoryService shuttleDriveHistoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("save 테스트")
    class save{

        @Test
        @DisplayName("성공")
        void whenSucces() throws JsonProcessingException {
            LocalDateTime start = LocalDateTime.of(2024,3,11,13,12);
            LocalDateTime end = LocalDateTime.now();

            Shuttle shuttle = Shuttle.builder()
                    .id(1L)
                    .name("1호차")
                    .type("스타렉스")
                    .licenseNumber("가1234")
                    .build();

            Driver driver = Driver.builder()
                    .id(1L)
                    .name("김가네")
                    .phoneNumber("0101234")
                    .build();

            Teacher teacher = Teacher.builder()
                    .id(1L)
                    .name("박가네")
                    .phoneNumber("1234")
                    .build();

            List<ShuttleLocationDTO> shuttleLocationDTOList = new ArrayList<>();
            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,2,LocalDateTime.now()));
            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,3,LocalDateTime.now()));
            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,4,LocalDateTime.now()));
            shuttleLocationDTOList.add( ShuttleLocationDTO.of(1,5,LocalDateTime.now()));

            ShuttleDriveHistory shuttleDriveHistory = ShuttleDriveHistory.of(
                    start,
                    end,
                    shuttle,
                    driver,
                    teacher,
                    shuttleLocationDTOList
            );

            String savedId = shuttleDriveHistoryService.save(shuttleDriveHistory);

            ShuttleDriveHistory found = shuttleDriveHistoryService.findById(savedId);
            String s = objectMapper.writeValueAsString(found);

            log.info("saved data -> [{}]",s);

        }

    }
}

//    public static ShuttleDriveHistory of(LocalDateTime start, LocalDateTime end, Shuttle shuttle, Driver driver, Teacher teacher, List<ShuttleLocationDTO> shuttleLocationDTOList)
