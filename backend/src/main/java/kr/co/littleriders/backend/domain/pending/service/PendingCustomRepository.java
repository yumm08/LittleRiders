package kr.co.littleriders.backend.domain.pending.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;

import java.util.List;

interface PendingCustomRepository {

    List<Pending> searchByChild(List<Child> childList);

    List<Pending> searchByAcademy(Academy academy);

    List<Pending> searchById(List<Long> pendingList);
}
