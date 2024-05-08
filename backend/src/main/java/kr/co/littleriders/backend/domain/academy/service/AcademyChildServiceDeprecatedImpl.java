package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.AcademyChildServiceDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class AcademyChildServiceDeprecatedImpl implements AcademyChildServiceDeprecated {
    private final AcademyChildRepositoryDeprecated academyChildRepositoryDeprecated;

    @Override
    public AcademyChildDeprecated findById(long id) {
        return academyChildRepositoryDeprecated.findById(id).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily) {
        return academyChildRepositoryDeprecated.existsByAcademyFamilyAndAttending(academyFamily);
    }

    @Override
    public List<AcademyChildDeprecated> findByAcademy(Academy academy) {
        return academyChildRepositoryDeprecated.findByAcademy(academy);
    }

    @Override
    public List<AcademyChildDeprecated> findByChild(Child child) {
        return academyChildRepositoryDeprecated.findByChild(child);
    }

    @Override
    public AcademyChildDeprecated findByChildAndAcademy(Child child, Academy academy) {
        return academyChildRepositoryDeprecated.findByChildAndAcademy(child, academy);
    }

    @Override
    public boolean existsByChildAndAcademy(Child child, Academy academy) {
        return academyChildRepositoryDeprecated.existsByChildAndAcademy(child, academy);
    }

    @Override
    public boolean existsById(long id) {
        return academyChildRepositoryDeprecated.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !academyChildRepositoryDeprecated.existsById(id);
    }

    @Override
    public AcademyChildDeprecated findByCardNumber(String cardNumber) {
        return academyChildRepositoryDeprecated.findByCardNumber(cardNumber).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );

    }

    @Override
    public long save(AcademyChildDeprecated academyChildDeprecated) {
        return academyChildRepositoryDeprecated.save(academyChildDeprecated).getId();
    }


}
