package kr.co.littleriders.backend.domain.family.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.family.entity.Family;

@Repository
interface FamilyRepository extends JpaRepository<Family, Long> {
}
