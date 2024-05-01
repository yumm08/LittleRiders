package kr.co.littleriders.backend.domain.teacher.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByAcademy(Academy academy);
}
