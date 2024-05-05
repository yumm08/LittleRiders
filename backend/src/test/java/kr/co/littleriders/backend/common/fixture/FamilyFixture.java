package kr.co.littleriders.backend.common.fixture;


import kr.co.littleriders.backend.application.dto.request.FamilySignUpRequest;
import kr.co.littleriders.backend.application.dto.request.SignInRequest;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor
@Getter
public enum FamilyFixture {



    LEE("lee@example.com","lee","이가네","인천 계양구 계산새로 88","01000000000"),
    PARK("park@example.com","park","박가네","서울 관악구 관악로 145","01011111111"),
    KIM("kim@example.com","kim","김가네","충남 아산시 배방읍 희망로 90","01022222222"),
    HONG("hong@example.com","hong","홍가네","광주 서구 내방로 111","01033333333");

    

    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private static final PasswordUtil passwordUtil = new PasswordUtil();

    public Family toFamily(){
        String password = passwordUtil.encrypt(this.password);
        return Family.of(email,password,name,address,phoneNumber);
    }

    public FamilySignUpRequest tofamilySignUpRequest(){
        return new FamilySignUpRequest(email,password,name,address,phoneNumber);
    }

    public SignInRequest toSignInRequest(){
        return new SignInRequest(email,password);
    }



}
