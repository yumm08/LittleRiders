package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;

import java.util.List;

interface PendingCustomRepository {

    List<Pending> findByChild(List<Child> childList);

    List<Pending> findByAcademy(Academy academy);

    List<Pending> findByIdList(List<Long> pendingList);
}
