package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
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


    @Deprecated
    public static AcademyChildResponse from(AcademyChildDeprecated academyChildDeprecated) {
        return new AcademyChildResponse(academyChildDeprecated.getId()
                , academyChildDeprecated.getChild().getName()
                , academyChildDeprecated.getChild().getBirthDate().toString()
                , academyChildDeprecated.getChild().getGender().name()
                , academyChildDeprecated.getChild().getImagePath()
                , academyChildDeprecated.getStatus().name());
    }

    @Deprecated
    public static AcademyChildResponse of(AcademyChildDeprecated academyChildDeprecated, ChildHistory childHistory) {

        // String imagePath = "/api/academy/child/" + childHistory.getId() + "/image";

        return new AcademyChildResponse(academyChildDeprecated.getId()
                , childHistory.getName()
                , childHistory.getBirthDate().toString()
                , childHistory.getGender().name()
                , childHistory.getImagePath()
                , academyChildDeprecated.getStatus().name());
    }
}
