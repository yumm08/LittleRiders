package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.ChildRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.global.entity.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public enum AcademyChildFixture {


    KIM("김여자","경기 양주시 은현면 용암리 460-3", LocalDate.of(2014, 10, 7), Gender.FEMALE,"kim.png","9792730e-8271-4790-9955-c3fd13702f9e","010-1111-1111","김씨메모"),
    PARK("김남자","경기 성남시 분당구 새마을로177번길 81", LocalDate.of(2014, 10, 7), Gender.MALE,"park.png","05d2d903-04b4-4c2b-9227-07b1445fb996","010-2222-2222","박씨메모"),
    CHOI("최여자","경기도 구리시 인창2로 177", LocalDate.of(2021, 9, 19), Gender.FEMALE,"choi.png","7b2cb741-42d2-4793-a11e-b6c0c08f86aa","010-3333-3333","최씨메모"),
    KANG("강남자","경기도 고양시 덕양구 혜음로 215", LocalDate.of(2023, 5, 1), Gender.MALE,"kang.png","a82d5c26-fd6a-4ebe-8f3b-81b2f002a737","010-4444-4444","강씨메모");


    private String name;
    private String address;
    private LocalDate birthDate;
    private Gender gender;
    private String imagePath;
    private String beaconNumber;
    private String phoneNumber;
    private String memo;


    public ChildRegistRequest toChildRegistRequest() {
        return new ChildRegistRequest(name, birthDate, gender.name(), null);
    }

    public AcademyChild toAcademyChild(Academy academy, AcademyChildStatus status) {
        return AcademyChild.of(academy,name,address,birthDate,gender,imagePath,beaconNumber,phoneNumber,status,memo);
    }

}
