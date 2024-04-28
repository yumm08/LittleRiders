package kr.co.littleriders.backend.domain.academy.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.littleriders.backend.domain.academy.entity.QAcademy.academy;

@Repository
@RequiredArgsConstructor
class AcademyCustomRepositoryImpl implements AcademyCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Academy> findByName(String name, Pageable pageable) {
        List<Academy> academyList = jpaQueryFactory
                                    .selectFrom(academy)
                                    .where(academy.name.like(name))
                                    .offset(pageable.getOffset())
                                    .limit(pageable.getPageSize() + 1)
                                    .fetch();

        return new SliceImpl<>(academyList.stream()
                                    .limit(pageable.getPageSize())
                                    .collect(Collectors.toList()),
                                pageable,
                        academyList.size() > pageable.getPageSize());
    }
}

