package kr.co.littleriders.backend.domain.pending.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.pending.entity.Pending;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.littleriders.backend.domain.pending.entity.QPending.pending;

@Repository
@RequiredArgsConstructor
class PendingCustomRepositoryImpl implements PendingCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Pending> findByChildId(List<Child> childList) {
        return jpaQueryFactory
                .selectFrom(pending)
                .where(pending.child.in(childList))
                .fetch();
    }
}
