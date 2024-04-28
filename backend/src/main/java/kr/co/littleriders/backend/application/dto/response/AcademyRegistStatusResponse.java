package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.pending.entity.Pending;

public class AcademyRegistStatusResponse {

    private Long academyId;

    private String academyName;

    private String academyAddress;

    private String academyPhoneNumber;

    private String academyImagePath;

    private String childName;

    private String childImagePath;

    private String allowStatus;

    private AcademyRegistStatusResponse(Long academyId, String academyName, String academyAddress
                                    , String academyPhoneNumber, String academyImagePath
                                    , String childName, String childImagePath, String allowStatus) {

        this.academyId = academyId;
        this.academyName = academyName;
        this.academyAddress = academyAddress;
        this.academyImagePath = academyImagePath;
        this.childName = childName;
        this.childImagePath = childImagePath;
        this.allowStatus = allowStatus;
    }

    public static AcademyRegistStatusResponse from(Pending pending) {
        return new AcademyRegistStatusResponse(pending.getAcademy().getId()
                                            , pending.getAcademy().getName()
                                            , pending.getAcademy().getAddress()
                                            , pending.getAcademy().getPhoneNumber()
                                            , pending.getAcademy().getImagePath()
                                            , pending.getChild().getName()
                                            , pending.getChild().getImagePath()
                                            , pending.getStatus().name());
    }
}
