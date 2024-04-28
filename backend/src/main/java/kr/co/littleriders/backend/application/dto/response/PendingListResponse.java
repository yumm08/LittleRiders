package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.pending.entity.Pending;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PendingListResponse {

    private Long pendingId;

    private String childName;

    private String childBirthDate;

    private String childGender;

    private String address;

    private String phoneNumber;

    public PendingListResponse(Long pendingId, String childName, String childBirthDate, String childGender, String address, String phoneNumber) {
        this.pendingId = pendingId;
        this.childName = childName;
        this.childBirthDate = childBirthDate;
        this.childGender = childGender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static PendingListResponse from(Pending pending) {
        return new PendingListResponse(pending.getId()
                                    , pending.getChild().getName()
                                    , pending.getChild().getBirthDate().toString()
                                    , pending.getChild().getGender().name()
                                    , pending.getChild().getFamily().getAddress()
                                    , pending.getChild().getFamily().getPhoneNumber());
    }
}
