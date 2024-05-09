package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShuttleChildBoardResponse {
    private long academyChildId;
    private String name;
    private String phoneNumber;

    public static ShuttleChildBoardResponse from(AcademyChild academyChild) {
        long academyChildId = academyChild.getId();
        String name = academyChild.getName();
        String phoneNumber = academyChild.getPhoneNumber();

        return new ShuttleChildBoardResponse(academyChildId, name, phoneNumber);
    }

}
