package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyListResponse {

    private Long academyId;

    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String image;

    public AcademyListResponse(Long id, String name, String address, String phoneNumber, String email, String imagePath) {
        this.academyId = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = imagePath;
    }

    public static AcademyListResponse from(Academy academy) {
        return new AcademyListResponse(academy.getId()
                , academy.getName()
                , academy.getAddress()
                , academy.getPhoneNumber()
                , academy.getEmail()
                , academy.getImagePath());
    }
}
