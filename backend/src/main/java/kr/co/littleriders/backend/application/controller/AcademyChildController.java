package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academy/child")
@RequiredArgsConstructor
public class AcademyChildController {

    private final AcademyChildFacade academyChildFacade;

    @GetMapping
    public ResponseEntity<List<AcademyChildResponse>> getAcademyChildList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<AcademyChildResponse> academyChildList = academyChildFacade.readAcademyChildList(academyId);

        return ResponseEntity.ok().body(academyChildList);
    }

    @GetMapping("/{academyChildId}")
    public ResponseEntity<AcademyChildDetailResponse> getAcademyChildDetail(@Auth AuthAcademy authAcademy,
                                                                            @PathVariable(value = "academyChildId") Long academyChildId) {

        Long academyId = authAcademy.getId();
        AcademyChildDetailResponse academyChildDetailResponse = academyChildFacade.readAcademyChildDetail(academyId, academyChildId);

        return ResponseEntity.ok().body(academyChildDetailResponse);
    }

    @PutMapping
    public ResponseEntity<Long> editAcademyChild(@Auth AuthAcademy authAcademy
                                                , @RequestParam(value = "academyChildId") Long academyChildId
                                                , @RequestBody String status) {

        Long academyId = authAcademy.getId();
        Long updateChildId = academyChildFacade.updateAcademyChild(academyId, academyChildId, status);

        return ResponseEntity.ok().body(updateChildId);
    }


    @GetMapping("/pending")
    public ResponseEntity<List<PendingListResponse>> getPendingList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<PendingListResponse> pendingList = academyChildFacade.readPendingList(academyId);

        return ResponseEntity.ok().body(pendingList);
    }

    @PostMapping("/pending")
    public ResponseEntity<Void> addAcademyChild(@Auth AuthAcademy authAcademy
                                                , @RequestBody List<Long> pendingList) {

        Long academyId = authAcademy.getId();
        academyChildFacade.insertAcademyChildList(academyId, pendingList);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pending")
    public ResponseEntity<Void> removePendingList(@Auth AuthAcademy authAcademy
                                                , @RequestBody List<Long> pendingList) {

        Long academyId = authAcademy.getId();
        academyChildFacade.deletePendingList(academyId, pendingList);

        return ResponseEntity.ok().build();
    }

}
