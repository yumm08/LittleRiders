package kr.co.littleriders.backend.global.permission;


import kr.co.littleriders.backend.domain.academy.AcademyChildServiceDeprecated;
import kr.co.littleriders.backend.domain.child.ChildService;
import kr.co.littleriders.backend.domain.family.FamilyService;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.domain.shuttle.ShuttleService;
import kr.co.littleriders.backend.domain.shuttle.entity.Shuttle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionHelper {

    private final FamilyService familyService;
    private final ChildService childService;
    private final ShuttleService shuttleService;
    private final AcademyChildServiceDeprecated academyChildServiceDeprecated;

    public boolean check(Family family, Shuttle shuttle) {
        /*
            가족 -> 아이
            셔틀 -> 학원
            아이 -> 학원 출석 목록
         */
//        List<Child> childList = family.getChildList();
//        Academy academy = shuttle.getAcademy();
//        for (Child c : childList){
//            if(academyChildService.existsByAcademyAndChild(academy,c)){
//                AcademyChild academyChild = academyChildService.findByAcademyAndChild(academy,c);
//                return academyChild.isAttending(); //다니고 있으면 참임
//            }
//        }
//        return false;
        return false;
    }


}
