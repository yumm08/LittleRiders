package kr.co.littleriders.backend.application.dto.response;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AcademyListResponse {

    private List<AcademyList> academyList;

    private Integer page;

    private Boolean hasNext;

    private AcademyListResponse(List<AcademyList> academyList, int number, boolean hasNext) {
        this.academyList = academyList;
        this.page = number;
        this.hasNext = hasNext;
    }

    public static AcademyListResponse of(List<AcademyList> academyList, int number, boolean hasNext) {
        return new AcademyListResponse(academyList, number, hasNext);
    }
}