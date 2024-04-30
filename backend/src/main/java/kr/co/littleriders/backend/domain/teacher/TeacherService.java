package kr.co.littleriders.backend.domain.teacher;

import kr.co.littleriders.backend.domain.teacher.entity.Teacher;

public interface TeacherService {

	Teacher findById(long id);

	boolean existsById(long id);

	boolean notExistsById(long id);

	long save(Teacher teacher);


}
