package kr.co.littleriders.backend.domain.family.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.family.entity.Family;

import java.util.Optional;

@Repository
interface FamilyRepository extends JpaRepository<Family, Long> {

    Optional<Family> findById(long id);

    boolean existsByEmail(String email);

    boolean existsById(long id);

    Optional<Family> findByEmail(String email);
}
