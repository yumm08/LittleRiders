package kr.co.littleriders.backend.application.controller;


import jakarta.validation.Valid;
import kr.co.littleriders.backend.application.dto.request.FamilyAcademyJoinRequest;
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

    @GetMapping
    public ResponseEntity<List<AcademyListResponse>> getAcademyList(@RequestParam(value = "name") String name
                                                                   , @PageableDefault(size = 20, sort = "id") Pageable pageable) {

        List<AcademyListResponse> acadmeyList = familyAcademyFacade.readAcademyList(name, pageable);

        return ResponseEntity.ok().body(acadmeyList);
    }

    @PostMapping
    public ResponseEntity<Void> addAcademyPending(@Auth AuthFamily authFamily
                                             , @RequestBody @Valid FamilyAcademyJoinRequest familyAcademyJoinRequest) {


        familyAcademyFacade.insertAcademyJoin(authFamily, familyAcademyJoinRequest);

        return ResponseEntity.ok().build();
    }

}
