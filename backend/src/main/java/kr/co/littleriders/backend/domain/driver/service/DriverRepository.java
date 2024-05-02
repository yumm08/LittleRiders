package kr.co.littleriders.backend.domain.driver.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByAcademy(Academy academy);
}
