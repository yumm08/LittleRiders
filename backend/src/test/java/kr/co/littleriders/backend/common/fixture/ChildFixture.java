package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum ChildFixture {


    KIM_FEMALE("김여자", LocalDate.of(2014, 10, 7), Gender.FEMALE),
    KIM_MALE("김남자", LocalDate.of(2014, 10, 7), Gender.MALE),
    PARK_FEMALE("박여자", LocalDate.of(2022, 2, 22), Gender.FEMALE),
    PARK_MALE("박남자", LocalDate.of(2022, 2, 22), Gender.MALE),
    CHOI_FEMALE("최여자", LocalDate.of(2021, 9, 19), Gender.FEMALE),
    CHOI_MALE("최남자", LocalDate.of(2021, 9, 19), Gender.MALE),
    KANG_FEMALE("강여자", LocalDate.of(2023, 5, 1), Gender.FEMALE),
    KANG_MALE("강남자", LocalDate.of(2023, 5, 1), Gender.MALE);


    private String name;
    private LocalDate birthDate;
    private Gender gender;

    public Child toChild(Family family) {
        return Child.of(
                name,
                birthDate,
                gender,
                family
        );
    }

    public ChildRegistRequest toChildRegistRequest(){
        return new ChildRegistRequest(name,birthDate,gender.name(),null);
    }

}
