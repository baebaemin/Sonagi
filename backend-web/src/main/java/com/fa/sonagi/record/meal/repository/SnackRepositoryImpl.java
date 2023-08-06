package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.statistics.meal.dto.SnackFeedingStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.meal.entity.QSnack.snack;

@RequiredArgsConstructor
public class SnackRepositoryImpl implements SnackRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public SnackResDto findSnackRecord(Long snackId) {
		SnackResDto snacks = queryFactory
			.select(Projections.bean(SnackResDto.class,
				snack.id,
				snack.memo,
				snack.createdTime))
			.from(snack)
			.where(snack.id.eq(snackId))
			.fetchOne();

		return snacks;
	}

	@Override
	public List<SnackFeedingStatisticsQueryDto> findSnackByDay(Long babyId, LocalDate createdDate) {
		List<SnackFeedingStatisticsQueryDto> snackFeedingStatisticsQueryDto = queryFactory
			.select(Projections.bean(SnackFeedingStatisticsQueryDto.class,
				snack.createdTime))
			.from(snack)
			.where(snack.babyId.eq(babyId), snack.createdDate.eq(createdDate))
			.fetch();

		return snackFeedingStatisticsQueryDto;
	}

	@Override
	public Long findSnackCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(snack.count())
			.from(snack)
			.where(snack.babyId.eq(babyId), snack.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}
}