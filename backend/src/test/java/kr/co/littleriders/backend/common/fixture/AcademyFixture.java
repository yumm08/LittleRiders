package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.AcademySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
public enum AcademyFixture {



    BOXING("boxing@example.com","boxing","복싱클럽","인천 남동구 정각로 29","0320000000"),
    COMPUTER("computer@example.com","computer","컴퓨터 학원","서울 관악구 남부순환로 1721","020000000"),
    SOCCER("soccer@example.com","soccer","제육FC","충남 홍성군 홍북읍 충남대로 21","0410000000"),
    BASEBALL("baseball@example.com","baseball","야구클럽","전남 무안군 삼향읍 오룡길 1","0610000000");



    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private static final PasswordUtil passwordUtil = new PasswordUtil();

    Academy toAcademy(){
        String password = passwordUtil.encrypt(this.password);
        return Academy.of(email,password,name,address,phoneNumber);
    }

    AcademySignUpRequest toAcademySignUpRequest(){
        return new AcademySignUpRequest(email,password,name,address,phoneNumber);
    }

    SignInRequest toSignInRequest(){
        return SignInRequest.of(email,password);
    }


}
