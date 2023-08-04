package com.fa.sonagi.statistics.diaper.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsResDto;
import com.fa.sonagi.statistics.diaper.service.DiaperStatisticsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "DiaperCategory", description = "기저귀 통계 API")
@RequestMapping("/api/diaperStatistics")
@RestController
@RequiredArgsConstructor
public class DiaperStatisticsController {
	private final DiaperStatisticsService diaperStatisticsService;

	/**
	 * 기저귀 통계 조회
	 */
	@GetMapping
	public ResponseEntity<?> getDiaperStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			DiaperStatisticsResDto diaperStatisticsResDto = diaperStatisticsService.getDiaperStatisticsDay(babyId, createdDate);
			return ResponseEntity.ok().body(diaperStatisticsResDto);
		}
		else {
			DiaperStatisticsResDto diaperStatisticsResDto = diaperStatisticsService.getDiaperStatisticsDay(babyId, createdDate);
			return ResponseEntity.ok().body(diaperStatisticsResDto);
		}
	}
}
