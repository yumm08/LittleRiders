package kr.co.littleriders.backend.domain.childboarddropinfo.service;


import kr.co.littleriders.backend.domain.childboarddropinfo.entity.ChildBoardDropInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChildBoardDropInfoRepository extends JpaRepository<ChildBoardDropInfo,Long> {
}
