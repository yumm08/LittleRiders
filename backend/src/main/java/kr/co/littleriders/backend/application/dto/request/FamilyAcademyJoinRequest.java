package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyAcademyJoinRequest {

    @NotNull
    private Long academyId;

    @NotNull
    private Long childId;

}
