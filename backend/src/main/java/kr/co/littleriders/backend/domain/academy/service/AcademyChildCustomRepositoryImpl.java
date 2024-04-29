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
    public boolean existsByAcademyFamilyAndAttending(AcademyFamily academyFamily) {

        long count = jpaQueryFactory
            .selectFrom(academyChild)
            .where(academyChild.academyFamily.eq(academyFamily),
                academyChild.status.in(AcademyChildStatus.ATTENDING))
            .fetchCount();

        return count > 0;
    }
}
