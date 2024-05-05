
package kr.co.littleriders.backend.application.controller;


import kr.co.littleriders.backend.application.facade.FamilyShuttleFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/family/shuttle")
@RequiredArgsConstructor
@Slf4j
public class FamilyShuttleController {


    private final FamilyShuttleFacade familyShuttleFacade;




    //TODO - 김도현 - 해당 API 엔드포인트는 SSE 가 적용된다면 필요 없어짐.
//    @Deprecated
//    @GetMapping("/{shuttleId}/location")
//    public ResponseEntity<LatitudeLongitudeResponse> findShuttleLocationByFamilyIdAndShuttleId(@Auth AuthFamily authFamily, @PathVariable long shuttleId){
//
//
//        LatitudeLongitudeResponse latitudeLongitudeResponse = familyShuttleFacade.findShuttleLocationByFamilyIdAndShuttleId(authFamily.getId(),shuttleId);
//        return ResponseEntity.ok(latitudeLongitudeResponse);
//    }

    @GetMapping("/{shuttleId}/location")
    public ResponseEntity<SseEmitter> locationSubscribe(@PathVariable(name = "shuttleId") long shuttleId) { //AuthFamily 추가
        SseEmitter emitter = familyShuttleFacade.subscribeShuttle(1L,shuttleId);
        log.info("hhuhuhu");
        log.info("emitter = {}",emitter);
        return ResponseEntity.ok(emitter);
    }




    //TODO - 김도현 - 해당 API 엔드포인트는 SSE 가 적용된다면 필요 없어짐.
//    @Deprecated
//    @GetMapping("/{shuttleId}")
//    public ResponseEntity<LatitudeLongitudeResponse> findShuttleDriveInfoByShuttleIdAndFamily(@Auth AuthFamily authFamily, @PathVariable long shuttleId){
//        LatitudeLongitudeResponse latitudeLongitudeResponse = familyShuttleFacade.findShuttleDriveInfoByShuttleIdAndFamily(authFamily.getId(),shuttleId);
//        return ResponseEntity.ok(latitudeLongitudeResponse);
//    }
}
