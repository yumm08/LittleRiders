package kr.co.littleriders.backend.domain.history.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChildDeprecated;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.littleriders.backend.domain.history.entity.QBoardDropHistory.*;

@Repository
@RequiredArgsConstructor
class BoardDropHistoryCustomRepositoryImpl implements BoardDropHistoryCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<BoardDropHistory> findByAcademyChild(List<AcademyChildDeprecated> academyChildDeprecatedList, Pageable pageable) {

        List<BoardDropHistory> historyList = jpaQueryFactory
                .selectFrom(boardDropHistory)
                .where(boardDropHistory.academyChild.in(academyChildDeprecatedList))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(historyList.stream()
                .limit(pageable.getPageSize())
                .collect(Collectors.toList()),
                pageable,
                historyList.size() > pageable.getPageSize());
    }
}
