package kr.co.littleriders.backend.domain.academy;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;

public interface AcademyChildService {

    AcademyChild findById(Long id);

    boolean existsById(Long id);

    boolean notExistsById(Long id);

    AcademyChild findByCardNumber(String cardNumber);
}
