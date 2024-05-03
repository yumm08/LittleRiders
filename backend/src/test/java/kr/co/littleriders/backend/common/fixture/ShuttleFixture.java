package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.ShuttleRegistRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum ShuttleFixture {


    BOXING_1_USE("01복0000","복싱클럽1호차","스타렉스",ShuttleStatus.USE),
    BOXING_2_USE("02복0000","복싱클럽2호차","스타렉스",ShuttleStatus.USE),
    BOXING_3_USE("03복0000","복싱클럽3호차","스타렉스",ShuttleStatus.USE),
    BOXING_1_NOT_USE("01복0000","복싱클럽1호차","스타렉스",ShuttleStatus.NOT_USE),
    BOXING_2_NOT_USE("02복0000","복싱클럽2호차","스타렉스",ShuttleStatus.NOT_USE),
    BOXING_3_NOT_USE("03복0000","복싱클럽3호차","스타렉스",ShuttleStatus.NOT_USE),
    BOXING_1_REPAIRING("01복0000","복싱클럽1호차","스타렉스",ShuttleStatus.REPAIRING),
    BOXING_2_REPAIRING("02복0000","복싱클럽2호차","스타렉스",ShuttleStatus.REPAIRING),
    BOXING_3_REPAIRING("03복0000","복싱클럽3호차","스타렉스",ShuttleStatus.REPAIRING),



    COMPUTER_1_USE("01컴0000","컴퓨터1호차","스타렉스",ShuttleStatus.USE),
    COMPUTER_2_USE("02컴0000","컴퓨터2호차","스타렉스",ShuttleStatus.USE),
    COMPUTER_3_USE("03컴0000","컴퓨터3호차","스타렉스",ShuttleStatus.USE),
    COMPUTER_1_NOT_USE("01컴0000","컴퓨터1호차","스타렉스",ShuttleStatus.NOT_USE),
    COMPUTER_2_NOT_USE("02컴0000","컴퓨터2호차","스타렉스",ShuttleStatus.NOT_USE),
    COMPUTER_3_NOT_USE("03컴0000","컴퓨터3호차","스타렉스",ShuttleStatus.NOT_USE),
    COMPUTER_1_REPAIRING("01컴0000","컴퓨터1호차","스타렉스",ShuttleStatus.REPAIRING),
    COMPUTER_2_REPAIRING("02컴0000","컴퓨터2호차","스타렉스",ShuttleStatus.REPAIRING),
    COMPUTER_3_REPAIRING("03컴0000","컴퓨터3호차","스타렉스",ShuttleStatus.REPAIRING),

    SOCCER_1_USE("01축0000","제육FC1호차","스타렉스",ShuttleStatus.USE),
    SOCCER_2_USE("02축0000","제육FC2호차","스타렉스",ShuttleStatus.USE),
    SOCCER_3_USE("03축0000","제육FC3호차","스타렉스",ShuttleStatus.USE),
    SOCCER_1_NOT_USE("01축0000","제육FC1호차","스타렉스",ShuttleStatus.NOT_USE),
    SOCCER_2_NOT_USE("02축0000","제육FC2호차","스타렉스",ShuttleStatus.NOT_USE),
    SOCCER_3_NOT_USE("03축0000","제육FC3호차","스타렉스",ShuttleStatus.NOT_USE),
    SOCCER_1_REPAIRING("01축0000","제육FC1호차","스타렉스",ShuttleStatus.REPAIRING),
    SOCCER_2_REPAIRING("02축0000","제육FC2호차","스타렉스",ShuttleStatus.REPAIRING),
    SOCCER_3_REPAIRING("03축0000","제육FC3호차","스타렉스",ShuttleStatus.REPAIRING),

    BASEBALL_1_USE("01야0000","야구클럽1호차","스타렉스",ShuttleStatus.USE),
    BASEBALL_2_USE("02야0000","야구클럽2호차","스타렉스",ShuttleStatus.USE),
    BASEBALL_3_USE("03야0000","야구클럽3호차","스타렉스",ShuttleStatus.USE),
    BASEBALL_1_NOT_USE("01야0000","야구클럽1호차","스타렉스",ShuttleStatus.NOT_USE),
    BASEBALL_2_NOT_USE("02야0000","야구클럽2호차","스타렉스",ShuttleStatus.NOT_USE),
    BASEBALL_3_NOT_USE("03야0000","야구클럽3호차","스타렉스",ShuttleStatus.NOT_USE),
    BASEBALL_1_REPAIRING("01야0000","야구클럽1호차","스타렉스",ShuttleStatus.REPAIRING),
    BASEBALL_2_REPAIRING("02야0000","야구클럽2호차","스타렉스",ShuttleStatus.REPAIRING),
    BASEBALL_3_REPAIRING("03야0000","야구클럽3호차","스타렉스",ShuttleStatus.REPAIRING);






    private String licenseNumber;
    private String name;
    private String type;
    private ShuttleStatus status;

    Shuttle toShuttle(Academy academy){
        return Shuttle.of(licenseNumber,name,type,academy,status);
    }

    ShuttleRegistRequest toShuttleRegistRequest(){
        return new ShuttleRegistRequest(licenseNumber,type,name,null);
    }


}
