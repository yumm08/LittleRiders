package kr.co.littleriders.backend.domain.teacher.service;

import kr.co.littleriders.backend.domain.teacher.TeacherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public boolean notExistsById(Long id) {
        return !teacherRepository.existsById(id);
    }
}
