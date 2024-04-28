package kr.co.littleriders.backend.domain.academy.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.AcademyChildService;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.littleriders.backend.domain.academy.entity.QAcademy.academy;
import static kr.co.littleriders.backend.domain.academy.entity.QAcademyChild.academyChild;

@Repository
@RequiredArgsConstructor
class AcademyChildCustomRepositoryImpl implements AcademyChildCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AcademyChild> findAllByAcademyAndAttending(Academy academy) {

        return jpaQueryFactory
                .selectFrom(academyChild)
                .where(academyChild.academy.eq(academy)
                        , academyChild.status.eq(AcademyChildStatus.ATTENDING))
                .orderBy(academyChild.id.asc())
                .fetch();
    }

    @Override
    public List<AcademyChild> findAllByAcademyAndNotAttending(Academy academy) {

        return jpaQueryFactory
                .selectFrom(academyChild)
                .where(academyChild.academy.eq(academy),
                        academyChild.status.in(AcademyChildStatus.LEAVE, AcademyChildStatus.GRADUATE))
                .orderBy(academyChild.id.asc())
                .fetch();
    }
}
