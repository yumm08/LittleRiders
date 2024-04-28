package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;

import java.util.List;

public interface PendingCustomRepository {

    List<Pending> findByChildId(List<Child> childList);
}
