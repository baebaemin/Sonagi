package com.fa.sonagi.record.sleep.repository;

import static com.fa.sonagi.record.sleep.entity.QSleep.*;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SleepRepositoryImpl implements SleepRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public SleepResDto findSleepRecord(Long sleepId) {
		SleepResDto sleeps = queryFactory
			.select(Projections.bean(SleepResDto.class,
				sleep.id,
				sleep.createdTime,
				sleep.endTime,
				sleep.memo))
			.from(sleep)
			.where(sleep.id.eq(sleepId))
			.fetchOne();

		return sleeps;
	}

	@Override
	public List<SleepStatisticsQueryDto> findSleepByDay(Long babyId, LocalDate createdDate) {
		List<SleepStatisticsQueryDto> sleepStatisticsQueryDto = queryFactory
			.select(Projections.bean(SleepStatisticsQueryDto.class,
				sleep.createdTime,
				sleep.endTime))
			.from(sleep)
			.where(sleep.babyId.eq(babyId), sleep.createdDate.eq(createdDate))
			.fetch();

		return sleepStatisticsQueryDto;
	}
}
