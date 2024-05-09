package kr.co.littleriders.backend.application.client;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SmsSendClientRequest {

    private final String to;
    @Setter
    private String from;
    private final String text;
    private final String type = "LMS";
    private final String subject = "리틀라이더즈 알림";

    private SmsSendClientRequest(String to, String text) {
        this.to = to;
        this.text = text;
    }

    public static SmsSendClientRequest of(AcademyChild academyChild, String text) {
        return new SmsSendClientRequest(academyChild.getPhoneNumber(), text);
    }

    public static SmsSendClientRequest toBoardMessage(String uuid, AcademyChild academyChild,Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String childName = academyChild.getName();
        String academyName = academy.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();
        String licenseNumber = shuttle.getLicenseNumber();
        String text = rideMessageTemplate(uuid,childName,"승차",academyName,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);
        return SmsSendClientRequest.of(academyChild,text);
    }

    public static SmsSendClientRequest toDropMessage(String uuid, AcademyChild academyChild, Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String childName = academyChild.getName();
        String academyName = academy.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();
        String licenseNumber = shuttle.getLicenseNumber();
        String text = rideMessageTemplate(uuid,childName,"하차",academyName,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);
        return SmsSendClientRequest.of(academyChild,text);

    }

    public static SmsSendClientRequest toStartDriveMessage(String uuid, AcademyChild academyChild, Teacher teacher, Driver driver, Shuttle shuttle) {
        Academy academy = academyChild.getAcademy();
        String academyName = academy.getName();
        String academyPhoneNumber = academy.getPhoneNumber();
        String driverPhoneNumber = driver.getPhoneNumber();
        String teacherPhoneNumber = teacher.getPhoneNumber();
        String licenseNumber = shuttle.getLicenseNumber();
        String text = shuttleStartTemplate(uuid,academyName,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);
        return SmsSendClientRequest.of(academyChild,text);
    }

    private static String shuttleStartTemplate(
            String uuid,
            String academyName,
            String academyPhoneNumber,
            String driverPhoneNumber,
            String teacherPhoneNumber,
            String licenseNumber
    ) {
        return "[운행 안내]\n"+
                String.format("%s 셔틀 운행이 시작되었습니다.\n",academyName)+
                academyAndDriveAndTeacherAndShuttleAndUuidTemplate(uuid,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);

    }

    private static String rideMessageTemplate(
            String uuid,
            String childName,
            String status,
            String academyName,
            String academyPhoneNumber,
            String driverPhoneNumber,
            String teacherPhoneNumber,
            String licenseNumber) {

        return  String.format("[%s 안내]\n", status) +
                String.format("%s 어린이가 %s 셔틀에 %s 했습니다.\n", childName, academyName, status) +
                academyAndDriveAndTeacherAndShuttleAndUuidTemplate(uuid,academyPhoneNumber,driverPhoneNumber,teacherPhoneNumber,licenseNumber);

    }

    private static String academyAndDriveAndTeacherAndShuttleAndUuidTemplate(
            String uuid,
            String academyPhoneNumber,
            String driverPhoneNumber,
            String teacherPhoneNumber,
            String licenseNumber){

        return String.format("학원 연락처 : %s\n", academyPhoneNumber) +
                String.format("기사님 연락처 : %s\n", driverPhoneNumber) +
                String.format("선생님 연락처 : %s\n", teacherPhoneNumber) +
                String.format("차량 번호  : %s\n", licenseNumber) +
                "- 자세히 보러가기 -\n" +
                String.format("https://littleriders.co.kr/parent-view/%s", uuid);
    }

}
