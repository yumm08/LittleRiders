package com.example.signup.domain.verification;

import com.example.signup.domain.verification.entity.Verification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface VerificationRepository extends CrudRepository<Verification,String> {
    Optional<Verification> findByEmail(String email);
}
