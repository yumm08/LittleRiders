package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.AcademyChildUpdateRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
import kr.co.littleriders.backend.application.dto.response.PendingListResponse;
import kr.co.littleriders.backend.application.facade.AcademyChildFacade;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import kr.co.littleriders.backend.global.auth.dto.AuthAcademy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{childHistoryId}/image")
    public ResponseEntity<Resource> getAcademyChildImage(@Auth AuthAcademy authAcademy,
                                                         @PathVariable(value = "childHistoryId") Long childHistoryId) {

        Long academyId = authAcademy.getId();

        Map<String, Object> image = academyChildFacade.readAcademyChildImage(academyId, childHistoryId);

        Resource imageResource = (Resource) image.get("resource");
        MediaType mediaType = (MediaType) image.get("mediaType");

        HttpHeaders headers = new HttpHeaders();
        if (mediaType != null) {
            headers.setContentType(mediaType);
        } else {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }

        return ResponseEntity.ok().headers(headers).body(imageResource);
    }

    @PutMapping("/{academyChildId}")
    public ResponseEntity<Long> editAcademyChild(@Auth AuthAcademy authAcademy,
                                                @PathVariable(value = "academyChildId") Long academyChildId,
                                                @RequestBody AcademyChildUpdateRequest academyChildUpdateRequest) {

        Long academyId = authAcademy.getId();
        Long updateChildId = academyChildFacade.updateAcademyChild(academyId, academyChildId, academyChildUpdateRequest.getStatus());

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
