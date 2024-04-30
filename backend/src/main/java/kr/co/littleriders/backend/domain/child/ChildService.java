package kr.co.littleriders.backend.domain.child;

import kr.co.littleriders.backend.domain.child.entity.Child;

import java.util.List;

public interface ChildService {

	long save(Child child);

	List<Child> findByFamilyId(long familyId);

	Child findById(long childId);
}
