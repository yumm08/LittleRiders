package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PendingRepository extends JpaRepository<Pending, Long>, PendingCustomRepository {

	boolean existsByAcademyAndChild(Academy academy, Child child);

	Pending findByAcademyAndChild(Academy academy, Child child);
}