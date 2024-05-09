package kr.co.littleriders.backend.application.facade.impl;


import kr.co.littleriders.backend.application.dto.request.ShuttleLocationRequest;
import kr.co.littleriders.backend.application.dto.response.ShuttleLandingInfoResponse;
import kr.co.littleriders.backend.application.dto.response.ShuttleLocationResponse;
import kr.co.littleriders.backend.application.facade.SseFacade;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.AcademyService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.shuttle.ShuttleBoardService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDriveService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleDropService;
import kr.co.littleriders.backend.domain.shuttle.ShuttleLocationService;
import kr.co.littleriders.backend.domain.shuttle.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class SseFacadeImpl implements SseFacade {





    private final Map<Long, List<SseEmitter>> subscribeMapByShuttleId = new ConcurrentHashMap<>();
    private final Map<String,List<SseEmitter>> subscribeMapByViewerUuid = new ConcurrentHashMap<>();
    private final Map<Long, List<SseEmitter>> subscribeMapByAcademyId = new ConcurrentHashMap<>();


    private final ShuttleDriveService shuttleDriveService;
    private final ShuttleBoardService shuttleBoardService;
    private final ShuttleDropService shuttleDropService;
    private final ShuttleLocationService shuttleLocationService;
    private final AcademyService academyService;

    private final AcademyChildService academyChildService;

    private static final long RECONNECTION_TIMEOUT = 1000L;

    private SseEmitter createSse(){
        SseEmitter emitter = new SseEmitter(300000000L);
        emitter.onTimeout(emitter::complete);
        emitter.onError(e -> {
            emitter.complete();
        });
        emitter.onCompletion(() -> {
        });
        return emitter;
    }

    public void broadcastBoardDropByShuttleId(long shuttleId){
        //TODO - 김도현 - WHO IS USER?

    }

    public SseEmitter createAcademySseConnectionByAcademyId(long academyId){

        Academy academy = academyService.findById(academyId);
        SseEmitter sseEmitter = createSse();
        List<Long> shuttleIdList = academy.getShuttleList().stream().map(Shuttle::getId).toList();
        for(Long shuttleId : shuttleIdList){
            if(!subscribeMapByShuttleId.containsKey(shuttleId)){
                subscribeMapByShuttleId.put(shuttleId,new ArrayList<>());
            }
            subscribeMapByShuttleId.get(shuttleId).add(sseEmitter);


            if(shuttleDriveService.existsByShuttleId(shuttleId)){
                //TODO - 김도현 - repository 에서 shuttelId 로 셔틀 운행 시작 정보를 가져와야함

                ShuttleDrive shuttleDrive = shuttleDriveService.findByShuttleId(shuttleId);

                long teacherId = shuttleDrive.getTeacherId();
                long driverId = shuttleDrive.getDriverId();
                long routeId = shuttleDrive.getRouteId();

                //TODO - 김도현 - repository 에서 shuttleId로 shuttleLocation 정보를 모두 들고와야함
                List<ShuttleLocation> shuttleLocationList = shuttleLocationService.findByShuttleId(shuttleId);


                //TODO - 김도현 - repository 에서 shuttleId로 drop 정보를 모두 들고와야함
                List<ShuttleDrop> shuttleDropList = shuttleDropService.findByShuttleId(shuttleId);
                List<ShuttleLandingInfoResponse.BoardDropInfo> dropInfoList = new ArrayList<>();

                for(ShuttleDrop shuttleDrop : shuttleDropList){
                    AcademyChild academyChild = academyChildService.findById(shuttleDrop.getAcademyChildId());
                    double latitude = shuttleDrop.getLatitude();
                    double longitude = shuttleDrop.getLongitude();
                    LocalDateTime time = shuttleDrop.getTime();

                    ShuttleLandingInfoResponse.Child child = ShuttleLandingInfoResponse.Child.from(academyChild);
                    dropInfoList.add(ShuttleLandingInfoResponse.BoardDropInfo.of(child,latitude,longitude,time));

                }

                List<ShuttleBoard> shuttleBoardList = shuttleBoardService.findByShuttleId(shuttleId);
                List<ShuttleLandingInfoResponse.BoardDropInfo> boardInfoList = new ArrayList<>();
                for(ShuttleBoard shuttleBoard : shuttleBoardList){
                    AcademyChild academyChild = academyChildService.findById(shuttleBoard.getAcademyChildId());
                    double latitude = shuttleBoard.getLatitude();
                    double longitude = shuttleBoard.getLongitude();
                    LocalDateTime time = shuttleBoard.getTime();

                    ShuttleLandingInfoResponse.Child child = ShuttleLandingInfoResponse.Child.from(academyChild);
                    boardInfoList.add(ShuttleLandingInfoResponse.BoardDropInfo.of(child,latitude,longitude,time));
                }


                try{
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            //event 명 (event: event example)
                            .name("init")
                            //event id (id: id-1) - 재연결시 클라이언트에서 `Last-Event-ID` 헤더에 마지막 event id 를 설정
                            .id(String.valueOf("init"))
                            //event data payload (data: SSE connected)
                            .data(ShuttleLandingInfoResponse.of(shuttleId,teacherId,driverId,routeId,shuttleLocationList,boardInfoList,dropInfoList))
                            //SSE 연결이 끊어진 경우 재접속 하기까지 대기 시간 (retry: <RECONNECTION_TIMEOUT>)
                            .reconnectTime(RECONNECTION_TIMEOUT);
                    sseEmitter.send(event);
                }
                catch ( Exception ignored){}
            }


        }
        if(!subscribeMapByAcademyId.containsKey(academyId)){
            subscribeMapByAcademyId.put(academyId,new ArrayList<>());
        }
        subscribeMapByAcademyId.get(academyId).add(sseEmitter);
        return  sseEmitter;
    }





    public void broadcastShuttleLocationByShuttleId(long shuttleId, ShuttleLocationRequest shuttleLocationRequest) {
        if(!subscribeMapByShuttleId.containsKey(shuttleId)){
            return;
        }
        subscribeMapByShuttleId.get(shuttleId).forEach(emitter -> {

            ShuttleLocationResponse shuttleLocationResponse = ShuttleLocationResponse.of(shuttleId,shuttleLocationRequest);
            try {
                emitter.send(SseEmitter.event()
                        .name("location")
                        .id("location")
                        .reconnectTime(RECONNECTION_TIMEOUT)
                        .data(shuttleLocationResponse, MediaType.APPLICATION_JSON));
            } catch (Exception ignored) {
            }
        });
    }

}