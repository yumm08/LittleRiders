package kr.co.littleriders.backend.domain.pending;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;

import java.util.List;

public interface PendingService {

    long save(Pending pending);

    List<Pending> searchByChild(List<Child> childList);

    List<Pending> searchByAcademy(Academy academy);

    List<Pending> searchById(List<Long> pendingList);

	boolean existsByAcademyAndChild(Academy academy, Child child);

	Pending findByAcadmeyAndChild(Academy academy, Child child);

	void deleteById(Long id);
}
