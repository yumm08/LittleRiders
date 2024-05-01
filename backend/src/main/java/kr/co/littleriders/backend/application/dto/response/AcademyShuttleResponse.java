package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcademyShuttleResponse {


    private Long shuttleId;

    private String name;

    private String licenseNumber;

    private String type;

    private String imagePath;

    private String terminalNumber;

    private String status;

    private AcademyShuttleResponse(Long id, String name, String licenseNumber, String type, String imagePath, String terminalNumber, String status) {
        this.shuttleId = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.type = type;
        this.imagePath = imagePath;
        this.terminalNumber = terminalNumber;
        this.status = status;
    }

    public static AcademyShuttleResponse from(Shuttle shuttle) {
        return new AcademyShuttleResponse(shuttle.getId()
                                        , shuttle.getName()
                                        , shuttle.getLicenseNumber()
                                        , shuttle.getType()
                                        , shuttle.getImagePath()
                                        , shuttle.getShuttleTerminalAttach().getTerminal().getTerminalNumber()
                                        , shuttle.getStatus().name());

    }
}
