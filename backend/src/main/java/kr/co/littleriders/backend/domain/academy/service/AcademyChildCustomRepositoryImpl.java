package kr.co.littleriders.backend.domain.academy.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import kr.co.littleriders.backend.domain.academy.entity.AcademyFamily;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.littleriders.backend.domain.academy.entity.QAcademyChild.academyChild;

@Repository
@RequiredArgsConstructor
class AcademyChildCustomRepositoryImpl implements AcademyChildCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AcademyChild> searchByAcademyAndAttending(Academy academy) {

        return jpaQueryFactory
                .selectFrom(academyChild)
                .where(academyChild.academy.eq(academy)
                        , academyChild.status.eq(AcademyChildStatus.ATTENDING))
                .orderBy(academyChild.id.asc())
                .fetch();
    }

    @Override
    public List<AcademyChild> searchByAcademyAndNotAttending(Academy academy) {

        return jpaQueryFactory
                .selectFrom(academyChild)
                .where(academyChild.academy.eq(academy),
                        academyChild.status.in(AcademyChildStatus.LEAVE, AcademyChildStatus.GRADUATE))
                .orderBy(academyChild.id.asc())
                .fetch();
    }

    @Override
    public boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily) {

        long count = jpaQueryFactory
            .selectFrom(academyChild)
            .where(academyChild.academyFamily.eq(academyFamily),
                academyChild.status.in(AcademyChildStatus.ATTENDING))
            .fetchCount();

        return count > 0;
    }
}
