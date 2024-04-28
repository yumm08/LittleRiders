package kr.co.littleriders.backend.application.dto.request;

import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FamilySignUpRequest {

    //TODO: Validator 추가
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;

    public Family toFamily(PasswordUtil passwordUtil) {
        return Family.of(this.email, passwordUtil.encrypt(this.password), this.name,this.address, this.phoneNumber);
    }
}
