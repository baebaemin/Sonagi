package com.fa.sonagi.record.sleep.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsQueryDto;

public interface SleepRepositoryCustom {
	SleepResDto findSleepRecord(Long sleepId);

	List<SleepStatisticsQueryDto> findSleepByDay(Long babyId, LocalDate createdDate);

	Long findSleepCnt(Long babyId, LocalDate createdDate);
}
