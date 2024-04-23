package kr.co.littleriders.backend.domain.child.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.child.entity.Child;

@Repository
interface ChildRepository extends JpaRepository<Child, Long> {
}
