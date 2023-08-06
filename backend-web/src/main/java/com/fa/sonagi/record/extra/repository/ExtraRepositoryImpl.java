package com.fa.sonagi.record.extra.repository;

import static com.fa.sonagi.record.extra.entity.QExtra.*;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExtraRepositoryImpl implements ExtraRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public ExtraResDto findExtraByDay(Long extraId) {
		ExtraResDto extras = queryFactory
			.select(Projections.bean(ExtraResDto.class,
				extra.id,
				extra.createdTime,
				extra.memo))
			.from(extra)
			.where(extra.id.eq(extraId))
			.fetchOne();
		return extras;
	}

	@Override
	public List<ExtraStatisticsQueryDto> findExtraByDay(Long babyId, LocalDate createdDate) {
		List<ExtraStatisticsQueryDto> extraStatisticsQueryDto = queryFactory
			.select(Projections.bean(ExtraStatisticsQueryDto.class,
				extra.createdTime))
			.from(extra)
			.where(extra.babyId.eq(babyId), extra.createdDate.eq(createdDate))
			.fetch();

		return extraStatisticsQueryDto;
	}

	@Override
	public Long findExtraCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(extra.count())
			.from(extra)
			.where(extra.babyId.eq(babyId), extra.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}
}
