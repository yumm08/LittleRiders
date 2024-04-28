package kr.co.littleriders.backend.domain.pending.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import kr.co.littleriders.backend.domain.pending.entity.PendingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.littleriders.backend.domain.pending.entity.QPending.pending;

@Repository
@RequiredArgsConstructor
class PendingCustomRepositoryImpl implements PendingCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Pending> findByChild(List<Child> childList) {
        return jpaQueryFactory
                .selectFrom(pending)
                .where(pending.child.in(childList))
                .fetch();
    }

    @Override
    public List<Pending> findByAcademy(Academy academy) {
        return jpaQueryFactory
                .selectFrom(pending)
                .where(pending.academy.eq(academy))
                .where(pending.status.eq(PendingStatus.PENDING))
                .fetch();
    }

    @Override
    public List<Pending> findByIdList(List<Long> pendingList) {
        return jpaQueryFactory
                .selectFrom(pending)
                .where(pending.id.in(pendingList))
                .where(pending.status.eq(PendingStatus.PENDING))
                .fetch();
    }
}
