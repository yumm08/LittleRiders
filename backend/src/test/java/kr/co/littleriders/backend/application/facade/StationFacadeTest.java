package kr.co.littleriders.backend.application.facade;

import kr.co.littleriders.backend.application.dto.request.StationCreateRequest;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.station.StationService;
import kr.co.littleriders.backend.domain.station.entity.Station;
import kr.co.littleriders.backend.domain.station.error.code.StationErrorCode;
import kr.co.littleriders.backend.domain.station.error.exception.StationException;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@SpringBootTest
public class StationFacadeTest {

    @Autowired
    private StationFacade stationFacade;

    @Autowired
    private StationService stationService;

    @Autowired
    private AcademyService academyService;

    private Academy academy;
    private AuthAcademy authAcademy;

    @BeforeEach
    void setUp() {
        academy = Academy.of("test@gmail", "1234", "test학원", "test주소", "010-1111-1111");
        academyService.save(academy);
        authAcademy = AuthAcademy.from(academy);
    }

    @Nested
    @DisplayName("정류장 등록 테스트")
    class createStationTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() throws Exception {
            // given
            StationCreateRequest stationCreateRequest = new StationCreateRequest("역삼역", 53.2, 55.6);

            // when
            stationFacade.createStation(authAcademy, stationCreateRequest);

            // then
            List<Station> stationList = stationService.findAllByAcademyIdAndName(authAcademy.getId(), "역삼역");
            assertThat(stationList).hasSize(1);
            assertThat(stationList.get(0).getName()).isEqualTo("역삼역");
        }

        @Test
        @DisplayName("정류장 이름 중복 테스트")
        void whenFail() {
            // 이미 존재하는 정류장 이름으로 등록 시 예외 발생

            // given
            StationCreateRequest stationCreateRequest1 = new StationCreateRequest("역삼역", 53.2, 55.6);
            stationFacade.createStation(authAcademy, stationCreateRequest1);

            StationCreateRequest stationCreateRequest2 = new StationCreateRequest("역삼역", 22, 33);

            // when, then
            assertThatThrownBy(() -> stationFacade.createStation(authAcademy, stationCreateRequest2))
                    .isInstanceOf(StationException.class)
                    .hasMessageContaining(StationErrorCode.DUPLICATE_NAME.getMessage());
        }
    }

//    @Nested
//    @DisplayName("정류장 목록 조회 테스트")
//    class searchByNameTest {
//        @Test
//        @DisplayName("성공")
//        void whenSuccess() {
//            StationCreateRequest stationCreateRequest1 = new StationCreateRequest("강남역", 53.2, 55.6);
//            stationFacade.createStation(authAcademy, stationCreateRequest1);
//
//            StationCreateRequest stationCreateRequest2 = new StationCreateRequest("역삼역", 22, 33);
//            stationFacade.createStation(authAcademy, stationCreateRequest2);
//
//            // Given
//            String name = "역";
//            Station station1 = Station.of(academy, "강남역", 37.123, 127.456);
//            Station station2 = Station.of(academy, "역삼역", 37.456, 127.789);
//
//            when(stationService.findAllByAcademyIdAndName(authAcademy.getId(), name))
//                    .thenReturn(Arrays.asList(station1, station2));
//
//            // When
//            List<StationResponse> result = stationFacade.searchByName(name, authAcademy);
//
//            // Then
//            assertThat(result).isNotNull();
//            assertThat(result).hasSize(2);
//            assertThat(result.get(0).getName()).isEqualTo("강남역");
//            assertThat(result.get(1).getName()).isEqualTo("역삼역");
//        }
//    }
}



