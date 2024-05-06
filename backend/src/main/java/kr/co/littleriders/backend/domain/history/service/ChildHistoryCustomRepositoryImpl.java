package kr.co.littleriders.backend.domain.history.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.child.entity.Child;
import kr.co.littleriders.backend.domain.history.entity.ChildHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static kr.co.littleriders.backend.domain.history.entity.QChildHistory.childHistory;

@Repository
@RequiredArgsConstructor
class ChildHistoryCustomRepositoryImpl implements ChildHistoryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ChildHistory findByCreatedAt(Child child, LocalDateTime updatedAt) {
        return jpaQueryFactory.selectFrom(childHistory)
                .where(childHistory.child.eq(child))
                .where(childHistory.child.eq(child),
                        childHistory.createdAt.before(updatedAt))
                .orderBy(childHistory.createdAt.desc())
                .fetchFirst();
    }

    @Override
    public ChildHistory findLatestByChild(Child child) {
        return jpaQueryFactory.selectFrom(childHistory)
                .where(childHistory.child.eq(child))
                .orderBy(childHistory.createdAt.desc())
                .fetchFirst();
    }
}
