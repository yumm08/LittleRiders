package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import kr.co.littleriders.backend.domain.academy.error.code.AcademyChildErrorCode;
import kr.co.littleriders.backend.domain.academy.error.exception.AcademyChildException;
import org.springframework.stereotype.Service;

import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.child.entity.Child;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class AcademyChildServiceImpl implements AcademyChildService {
    private final AcademyChildRepository academyChildRepository;

    @Override
    public AcademyChild findById(long id) {
        return academyChildRepository.findById(id).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily) {
        return academyChildRepository.existsByAcademyFamilyAndAttending(academyFamily);
    }

    @Override
    public List<AcademyChild> findByAcademy(Academy academy) {
        return academyChildRepository.findByAcademy(academy);
    }

    @Override
    public List<AcademyChild> findByChild(Child child) {
        return academyChildRepository.findByChild(child);
    }

    @Override
    public List<AcademyChild> findByChildInAndAcademyAndStatus(List<Child> childList, Academy academy, AcademyChildStatus status) {
        return academyChildRepository.findByChildInAndAcademyAndStatus(childList,academy,status);
    }

    @Override
    public boolean existsById(long id) {
        return academyChildRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(long id) {
        return !academyChildRepository.existsById(id);
    }

    @Override
    public AcademyChild findByCardNumber(String cardNumber) {
        return academyChildRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> AcademyChildException.from(AcademyChildErrorCode.NOT_FOUND)
        );

    }

    @Override
    public long save(AcademyChild academyChild) {
        return academyChildRepository.save(academyChild).getId();
    }


}
