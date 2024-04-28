package kr.co.littleriders.backend.domain.pending;

import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;

import java.util.List;

public interface PendingService {

    Long save(Pending pending);

    List<Pending> findByChildId(List<Child> childList);
}
