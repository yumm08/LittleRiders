package kr.co.littleriders.backend.domain.email.service;

import kr.co.littleriders.backend.domain.email.entity.FamilySignUpEmailVerification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface FamilySignUpEmailVerificationRepository extends CrudRepository<FamilySignUpEmailVerification, String> {
    Optional<FamilySignUpEmailVerification> findByEmail(String email);
}
