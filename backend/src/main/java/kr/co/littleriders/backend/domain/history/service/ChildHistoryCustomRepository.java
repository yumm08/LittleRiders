package kr.co.littleriders.backend.domain.history.service;

import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;

import java.time.LocalDateTime;

interface ChildHistoryCustomRepository {
    ChildHistory findByCreatedAt(Child child, LocalDateTime updatedAt);
}
