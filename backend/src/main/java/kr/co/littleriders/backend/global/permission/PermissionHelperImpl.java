package kr.co.littleriders.backend.global.permission;


import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.global.error.code.LittleRidersErrorCode;
import kr.co.littleriders.backend.global.error.exception.LittleRidersException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PermissionHelperImpl implements PermissionHelper {


    private final ShuttleService shuttleService;
    private final AcademyChildService academyChildService;


    private final PermissionException permissionException = PermissionException.createException();



    public boolean check(Family family, Shuttle shuttle) {
        return check(family, shuttle.getAcademy());
    }


    @Override
    public boolean check(Family family, Academy academy) {

        List<Child> childList = family.getChildList();
        List<AcademyChild> academyChildList = academyChildService.findByChildInAndAcademyAndStatus(childList, academy,AcademyChildStatus.ATTENDING);
        if (academyChildList.isEmpty()) {
            throw permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Family family, Child child) {
        if (child.getFamily() != family) {
            throw permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Family family, Driver driver) {
        Academy academy = driver.getAcademy();
        return check(family,academy);
    }

    @Override
    public boolean check(Family family, Teacher teacher) {
        Academy academy = teacher.getAcademy();
        return check(family,academy);
    }

    @Override
    public boolean check(Teacher teacher, Family family){
        return check(family,teacher);
    }

    @Override
    public boolean check(Academy academy, Shuttle shuttle) {
        if(shuttle.getAcademy() != academy){
            throw permissionException;
        }

        return true;
    }
    @Override
    public boolean check(Shuttle shuttle, Academy academy) {
        return check(academy,shuttle);
    }

    @Override
    public boolean check(Academy academy, ShuttleLocation shuttleLocation) {
        Shuttle shuttle = shuttleService.findById(shuttleLocation.getShuttleId());
        return check(academy,shuttle);
    }

    @Override
    public boolean check(Academy academy, Teacher teacher) {
        if(teacher.getAcademy() != academy){
            throw permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Academy academy, Driver driver) {

        if(driver.getAcademy() != academy){
            throw permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Shuttle shuttle, Teacher teacher) {
        if(shuttle.getAcademy() != teacher.getAcademy()){
            throw  permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Shuttle shuttle, Driver driver) {
        if(shuttle.getAcademy() != driver.getAcademy()){
            throw permissionException;
        }
        return true;
    }



    @Override
    public boolean check(Shuttle shuttle, AcademyChild academyChild) {
        if(shuttle.getAcademy() != academyChild.getAcademy()){
            throw permissionException;
        }
        return true;
    }

    @Override
    public boolean check(Shuttle shuttle, Route route){
        if(shuttle.getAcademy() != route.getAcademy()){
            throw permissionException;
        }
        return true;
    }
    @Override
    public boolean check(Route route, Shuttle shuttle){
        return check(shuttle,route);
    }


    private static class PermissionException extends LittleRidersException {

        protected PermissionException(LittleRidersErrorCode errorCode) {
            super(errorCode);
        }

        public static PermissionException createException() {
            return new PermissionException(new LittleRidersErrorCode() {
                @Override
                public HttpStatus getStatus() {
                    return HttpStatus.FORBIDDEN;
                }

                @Override
                public String getCode() {
                    return "PERMISSION_001";
                }

                @Override
                public String getMessage() {
                    return "권한이 부족합니다";
                }
            });
        }
    }


}
