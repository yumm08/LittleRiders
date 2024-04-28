package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.Academy;

public class AcademyList {
    private Long academyId;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String image;

    public AcademyList(Long id, String name, String address, String phoneNumber, String email, String imagePath) {
        this.academyId = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = imagePath;
    }

    public static AcademyList from(Academy academy) {
        return new AcademyList(academy.getId()
                , academy.getName()
                , academy.getAddress()
                , academy.getPhoneNumber()
                , academy.getEmail()
                , academy.getImagePath());
    }
}
