package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.family.entity.Family;
import kr.co.littleriders.backend.global.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FamilySignUpRequest {


    @NotBlank(message = "이메일")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    public Family toFamily(PasswordUtil passwordUtil) {
        return Family.of(this.email, passwordUtil.encrypt(this.password), this.name,this.address, this.phoneNumber);
    }
}
