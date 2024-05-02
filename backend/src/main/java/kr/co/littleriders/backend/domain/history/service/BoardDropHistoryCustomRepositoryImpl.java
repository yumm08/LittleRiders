package kr.co.littleriders.backend.domain.history.service;

import static kr.co.littleriders.backend.domain.history.entity.QBoardDropHistory.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import kr.co.littleriders.backend.domain.history.entity.BoardDropHistory;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class BoardDropHistoryCustomRepositoryImpl implements BoardDropHistoryCustomRepository{

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Slice<BoardDropHistory> findByAcademyChild(List<AcademyChild> academyChildList, Pageable pageable) {

		List<BoardDropHistory> historyList = jpaQueryFactory
			.selectFrom(boardDropHistory)
			.where(boardDropHistory.academyChild.in(academyChildList))
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
