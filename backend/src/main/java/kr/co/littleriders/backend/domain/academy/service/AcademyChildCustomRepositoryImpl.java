package kr.co.littleriders.backend.domain.academy.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class AcademyChildCustomRepositoryImpl implements AcademyChildCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily) {

        long count = jpaQueryFactory
                .selectFrom(academyChildDeprecated)
                .where(academyChildDeprecated.academyFamily.eq(academyFamily),
                        academyChildDeprecated.status.in(AcademyChildStatus.ATTENDING))
                .fetchCount();

        return count > 0;
    }
}
