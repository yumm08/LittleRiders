package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyChildResponse {

    private Long academyChildId;

    private String name;

    private String birthDate;

    private String gender;

    private String imagePath;

    private String childStatus;

    private AcademyChildResponse(Long academyChildId, String name, String birthDate, String gender, String imagePath, String childStatus) {
        this.academyChildId = academyChildId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.imagePath = imagePath;
        this.childStatus = childStatus;
    }

    public static AcademyChildResponse from(AcademyChild academyChild) {
        return new AcademyChildResponse(academyChild.getId()
                                        , academyChild.getChild().getName()
                                        , academyChild.getChild().getBirthDate().toString()
                                        , academyChild.getChild().getGender().name()
                                        , academyChild.getChild().getImagePath()
                                        , academyChild.getStatus().name());
    }

    public static AcademyChildResponse of(AcademyChild academyChild, ChildHistory childHistory) {

        String imagePath = "/api/academy/child/" + childHistory.getId() + "/image";

        return new AcademyChildResponse(academyChild.getId()
                                        , childHistory.getName()
                                        , childHistory.getBirthDate().toString()
                                        , childHistory.getGender().name()
                                        , imagePath
                                        , academyChild.getStatus().name());
    }
}
