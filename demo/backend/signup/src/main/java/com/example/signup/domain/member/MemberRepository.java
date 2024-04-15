package com.example.signup.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
