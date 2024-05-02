package kr.co.littleriders.backend.domain.academy.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.child.entity.Child;

import java.util.Optional;

@Repository
interface AcademyChildRepository extends JpaRepository<AcademyChild, Long>, AcademyChildCustomRepository {
	List<AcademyChild> findByAcademy(Academy academy);
    Optional<AcademyChild> findByCardNumber(String cardNumber);


	List<AcademyChild> findByChild(Child child);
}
