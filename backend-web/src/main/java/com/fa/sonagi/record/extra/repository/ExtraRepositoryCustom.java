package com.fa.sonagi.record.extra.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsQueryDto;

public interface ExtraRepositoryCustom {
	ExtraResDto findExtraByDay(Long extraId);

	List<ExtraStatisticsQueryDto> findExtraByDay(Long babyId, LocalDate createdDate);

	Long findExtraCnt(Long babyId, LocalDate createdDate);

	Map<LocalDate, Long> findExtraCnt(Long babyId, LocalDate monday, LocalDate sunday);

	Long findExtraCntByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
