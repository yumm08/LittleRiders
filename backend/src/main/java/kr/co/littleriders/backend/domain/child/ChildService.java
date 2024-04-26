package kr.co.littleriders.backend.domain.child;

import java.util.List;

import kr.co.littleriders.backend.domain.child.entity.Child;

public interface ChildService {

	Long save(Child child);

	List<Child> findByFamilyId(Long familyId);
}
