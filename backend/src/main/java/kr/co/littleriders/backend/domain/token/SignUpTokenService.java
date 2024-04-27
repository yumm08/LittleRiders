package kr.co.littleriders.backend.domain.token;

import kr.co.littleriders.backend.domain.token.entity.SignUpToken;

public interface SignUpTokenService {


    SignUpToken findFamilySignUpTokenByEmailAndToken(String email, String token);

    void save(SignUpToken signUpToken);

    void delete(SignUpToken signUpToken);
}
