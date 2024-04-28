package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AdminChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/child")
@RequiredArgsConstructor
public class AdminChildController {

    private final AdminChildFacade adminChildFacade;

    @GetMapping("/pending")
    public ResponseEntity<List<PendingListResponse>> getPendingList(@Auth AuthAcademy authAcademy) {

        Long academyId = authAcademy.getId();
        List<PendingListResponse> pendingList = adminChildFacade.readPendingList(academyId);

        return ResponseEntity.ok().body(pendingList);
    }


}
