package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AdminChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/child")
@RequiredArgsConstructor
public class AdminChildController {

    private final AdminChildFacade adminChildFacade;

    @GetMapping
    public ResponseEntity<List<AcademyChildResponse>> getAcademyChildList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<AcademyChildResponse> academyChildList = adminChildFacade.readAcademyChildList(academyId);

        return ResponseEntity.ok().body(academyChildList);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<PendingListResponse>> getPendingList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<PendingListResponse> pendingList = adminChildFacade.readPendingList(academyId);

        return ResponseEntity.ok().body(pendingList);
    }

    @PostMapping("/pending")
    public ResponseEntity<Void> addAcademyChild(@Auth AuthAcademy authAcademy
                                                , @RequestBody List<Long> pendingList) {

        Long academyId = authAcademy.getId();
        adminChildFacade.insertAcademyChildList(academyId, pendingList);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pending")
    public ResponseEntity<Void> removePendingList(@Auth AuthAcademy authAcademy
                                                , @RequestBody List<Long> pendingList) {

        Long academyId = authAcademy.getId();
        adminChildFacade.deletePendingList(academyId, pendingList);

        return ResponseEntity.ok().build();
    }

}
