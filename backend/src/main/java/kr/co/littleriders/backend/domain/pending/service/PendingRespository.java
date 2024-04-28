package kr.co.littleriders.backend.domain.pending.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.littleriders.backend.domain.pending.entity.Pending;

@Repository
interface PendingRespository extends JpaRepository<Pending, Long>, PendingCustomRepository {

}