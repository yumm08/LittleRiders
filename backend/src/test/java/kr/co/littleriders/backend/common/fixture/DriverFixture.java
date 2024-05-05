package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.DriverRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.driver.entity.DriverStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum DriverFixture {


    CHOO("추기사", "01000000000"),
    CHUN("천기사", "01011111111"),
    GANG("강기사", "010222222222"),
    MIN("민기사", "010333333333"),
    UM("엄기사", "01044444444"),
    YOON("윤기사", "01055555555"),
    MOON("문기사", "01066666666"),
    HWANG_BO("황보기사", "01077777777"),
    LIM("임기사", "01088888888"),
    CHE_GAL("제갈기사", "01099999999");


    private String name;
    private String phoneNumber;

    public Driver toDriver(Academy academy, DriverStatus status) {
        return Driver.of(
                name,
                phoneNumber,
                academy,
                status
        );
    }

    public DriverRegistRequest toDriverRegistRequest() {
        return new DriverRegistRequest(name, phoneNumber, null);
    }



}
