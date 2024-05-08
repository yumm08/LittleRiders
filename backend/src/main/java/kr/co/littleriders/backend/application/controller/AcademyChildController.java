package kr.co.littleriders.backend.application.controller;

import kr.co.littleriders.backend.application.dto.request.AcademyChildUpdateRequest;
import kr.co.littleriders.backend.application.dto.request.CreateAcademyChildRequest;
import kr.co.littleriders.backend.application.dto.response.AcademyChildDetailResponse;
import kr.co.littleriders.backend.application.dto.response.AcademyChildResponse;
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
        List<AcademyChildResponse> academyChildList = academyChildFacade.getAcademyChildListByAcademyId(academyId);

        return ResponseEntity.ok().body(academyChildList);
    }

    @GetMapping("/{academyChildId}")
    public ResponseEntity<AcademyChildDetailResponse> getAcademyChildDetail(@Auth AuthAcademy authAcademy,
                                                                            @PathVariable(value = "academyChildId") Long academyChildId) {

        Long academyId = authAcademy.getId();
        AcademyChildDetailResponse academyChildDetailResponse = academyChildFacade.getAcademyChildDetail(academyId, academyChildId);

        return ResponseEntity.ok().body(academyChildDetailResponse);
    }


    //TODO - HOTFIX-이윤지 필요없음(김도현)
//    @GetMapping("/{childHistoryId}/image")
//    public ResponseEntity<Resource> getAcademyChildImage(@Auth AuthAcademy authAcademy,
//                                                         @PathVariable(value = "childHistoryId") Long childHistoryId) {
//
//        Long academyId = authAcademy.getId();
//
//        Map<String, Object> image = academyChildFacade.readAcademyChildImage(academyId, childHistoryId);
//
//        Resource imageResource = (Resource) image.get("resource");
//        MediaType mediaType = (MediaType) image.get("mediaType");
//
//        HttpHeaders headers = new HttpHeaders();
//        if (mediaType != null) {
//            headers.setContentType(mediaType);
//        } else {
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        }
//
//        return ResponseEntity.ok().headers(headers).body(imageResource);
//    }

    @PutMapping("/{academyChildId}")
    public ResponseEntity<Long> editAcademyChild(@Auth AuthAcademy authAcademy,
                                                @PathVariable(value = "academyChildId") Long academyChildId,
                                                @RequestBody AcademyChildUpdateRequest academyChildUpdateRequest) {

        Long academyId = authAcademy.getId();
        Long updateChildId = academyChildFacade.updateAcademyChild(academyId, academyChildId, academyChildUpdateRequest.getStatus());

        return ResponseEntity.ok().body(updateChildId);
    }


    //TODO - HOTFIX-이윤지 수정 필요 - 학원 아이 등록
    @PostMapping("")
    public ResponseEntity<Void> createAcademyChild(@Auth AuthAcademy authAcademy, @RequestBody CreateAcademyChildRequest createAcademyChildRequest) {

        Long academyId = authAcademy.getId();
        academyChildFacade.createAcademyChild(academyId, createAcademyChildRequest);
        return ResponseEntity.ok().build();
    }


}
