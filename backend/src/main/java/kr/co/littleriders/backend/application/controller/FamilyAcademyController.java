package kr.co.littleriders.backend.application.controller;


import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.FamilyAcademyRegistRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyRegistStatusResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyListResponse;
import kr.co.littleriders.backend.application.facade.FamilyAcademyFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family/acadmey")
@RequiredArgsConstructor
public class FamilyAcademyController {

    private final FamilyAcademyFacade familyAcademyFacade;

    @GetMapping("/status")
    public ResponseEntity<List<AcademyRegistStatusResponse>> getAcademyStatusList(@Auth AuthFamily authFamily) {

        List<AcademyRegistStatusResponse> academyList = familyAcademyFacade.readAcademyRegistStatusList(authFamily);

        return ResponseEntity.ok().body(academyList);
    }

    @GetMapping
    public ResponseEntity<AcademyListResponse> getAcademyList(@RequestParam(value = "name") String name
                                                            , @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        AcademyListResponse academyList = familyAcademyFacade.readAcademyList(name, pageable);

        return ResponseEntity.ok().body(academyList);
    }

    @PostMapping
    public ResponseEntity<Void> addAcademyPending(@Auth AuthFamily authFamily
                                             , @RequestBody @Valid FamilyAcademyRegistRequest familyAcademyRegistRequest) {


        familyAcademyFacade.insertAcademyJoin(authFamily, familyAcademyRegistRequest);

        return ResponseEntity.ok().build();
    }

}
