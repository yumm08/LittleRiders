package kr.co.littleriders.backend.global.permission;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.route.entity.Route;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleLocation;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;

public interface PermissionHelper {

    boolean check(Family family, Shuttle shuttle);

    boolean check(Family family, Academy academy);

    boolean check(Family family, Child child);

    boolean check(Family family, Driver driver);

    boolean check(Family family, Teacher teacher);

    boolean check(Teacher teacher, Family family);

    boolean check(Academy academy, Shuttle shuttle);

    boolean check(Academy academy, ShuttleLocation shuttleLocation);

    boolean check(Academy academy, Teacher teacher);

    boolean check(Academy academy, Driver driver);

    boolean check(Shuttle shuttle, Teacher teacher);

    boolean check(Shuttle shuttle, Driver driver);

    boolean check(Shuttle shuttle, Academy academy);

    boolean check(Shuttle shuttle, AcademyChild academyChild);


    boolean check(Shuttle shuttle, Route route);

    boolean check(Route route, Shuttle shuttle);
}
