package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.TeacherRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.entity.TeacherStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum TeacherFixture {


    CHOO("추선생", "01000000000"),
    PARK("박선생", "01011111111"),
    SHIN("신선생", "010222222222"),
    CHOI("최선생", "010333333333"),
    KIM("김선생", "01044444444"),
    HONG("홍선생", "01055555555"),
    CHA("차선생", "01066666666"),
    NAM("남선생", "01077777777"),
    HA("하선생", "01088888888"),
    NAM_GOONG("남궁선생", "01099999999");


    private String name;
    private String phoneNumber;

    public Teacher toTeacher(Academy academy, TeacherStatus status) {
        return Teacher.of(
                name,
                phoneNumber,
                academy,
                status
        );
    }

    public TeacherRegistRequest toTeacherRegisterRequest() {
        return new TeacherRegistRequest(name, phoneNumber, null);
    }



}
