package com.example.signup.domain.registration;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface RegistrationCodeRepository extends CrudRepository<RegistrationCode,String> {

    Optional<RegistrationCode> findByEmail(String email);
}
