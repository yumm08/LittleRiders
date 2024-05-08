package kr.co.littleriders.backend.domain.academy.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.child.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AcademyChildRepositoryDeprecated extends JpaRepository<AcademyChildDeprecated, Long>, AcademyChildCustomRepository {
    List<AcademyChildDeprecated> findByAcademy(Academy academy);

    Optional<AcademyChildDeprecated> findByCardNumber(String cardNumber);

    List<AcademyChildDeprecated> findByChild(Child child);

    AcademyChildDeprecated findByChildAndAcademy(Child child, Academy academy);

    boolean existsByChildAndAcademy(Child child, Academy academy);
}
