package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyAcademyRegistRequest {

    @NotNull
    private Long academyId;

    @NotNull
    private Long childId;

}
