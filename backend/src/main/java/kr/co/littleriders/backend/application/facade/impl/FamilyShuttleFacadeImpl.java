package kr.co.littleriders.backend.application.facade.impl;

import kr.co.littleriders.backend.application.facade.FamilyShuttleFacade;
import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.global.permission.PermissionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RequiredArgsConstructor
@Service
class FamilyShuttleFacadeImpl implements FamilyShuttleFacade {

    private final FamilyService familyService;
    private final ShuttleService shuttleService;

    private final PermissionHelper permissionHelper;
    private final ShuttleLocationService shuttleLocationService;

    private final SseFacade sseFacade;


//    @Override
//    public ShuttleLocationResponse findShuttleLocationByFamilyIdAndShuttleId(final long familyId, final long shuttleId) {
//        //그래프탐색 시작
//
//        //부모 -> 아이 -> 학원 -> 셔틀 검증 수행
//
//        //TODO - 김도현 - 프로덕션시 valid 적용
////        Family family = familyService.findById(familyId);
////        Shuttle shuttle = shuttleService.findById(shuttleId);
////        if (!permissionHelper.check(family, shuttle)) {
////            throw PermissionException.from(PermissionErrorCode.DENIED);
////        }
//        ShuttleLocation shuttleLocation = shuttleLocationService.findByIdShuttleId(shuttleId);
//        double latitude = shuttleLocation.getLatitude();
//        double longitude = shuttleLocation.getLongitude();
//        int speed = shuttleLocation.getSpeed();
//        return ShuttleLocationResponse.of(latitude, longitude,speed);
//
//    }

//    @Override
//    public ShuttleLocationResponse findShuttleDriveInfoByShuttleIdAndFamily(final long familyId, final long shuttleId) {
//
//        //TODO - 김도현 - 프로덕션시 valid 적용
////        Family family = familyService.findById(familyId);
////        Shuttle shuttle = shuttleService.findById(shuttleId);
////        if (!permissionHelper.check(family, shuttle)) {
////            throw PermissionException.from(PermissionErrorCode.DENIED);
////        }
//        //location 이 아니라 어디를 봐야할까?
//        //코스 / 라우팅 / 차량 / 기사 / 선탑자 정보
//
//        //코스,
//        //라우팅
//        //차량
//        //기사
//        //선탑자 정보
//
//        return  null;
//
//    }

    @Override
    public SseEmitter subscribeShuttle(long familyId, long shuttleId) {
        //TODO - 김도현 - 프로덕션시 valid 적용
//        Family family = familyService.findById(familyId);
//        Shuttle shuttle = shuttleService.findById(shuttleId);
//        if (!permissionHelper.check(family, shuttle)) {
//            throw PermissionException.from(PermissionErrorCode.DENIED);
//        }

        //TODO - 김도현 - 연결시 현재 랜딩에서 뿌려줄 정보 가져오기
        SseEmitter sseEmitter = sseFacade.subscribeShuttle(shuttleId);
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .name("info")
                .id(String.valueOf("id-1"))
                .data("helloworld")
                .reconnectTime(1000L);
        try {
            sseEmitter.send(event);
        }catch (Exception ignored){
        }

        return sseEmitter;
    }

}
