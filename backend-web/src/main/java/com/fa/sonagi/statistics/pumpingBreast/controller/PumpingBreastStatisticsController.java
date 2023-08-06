package com.fa.sonagi.statistics.pumpingBreast.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsResDto;
import com.fa.sonagi.statistics.pumpingBreast.service.PumpingBreastStatisticsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PumpingBreastStatistics", description = "유축 통계 API")
@RequestMapping("/api/pumpingBreastStatistics")
@RestController
@RequiredArgsConstructor
public class PumpingBreastStatisticsController {
	private final PumpingBreastStatisticsService pumpingBreastStatisticsService;

	/**
	 * 유축 통계 조회
	 */
	@GetMapping
	public ResponseEntity<?> getPumpingBreastStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			PumpingBreastStatisticsResDto pumpingBreastStatisticsResDto = pumpingBreastStatisticsService.getPumpingBreastStatisticsDay(
				babyId, createdDate);
			return ResponseEntity.ok().body(pumpingBreastStatisticsResDto);
		} else {
			PumpingBreastStatisticsResDto pumpingBreastStatisticsResDto = pumpingBreastStatisticsService.getPumpingBreastStatisticsDay(
				babyId, createdDate);
			return ResponseEntity.ok().body(pumpingBreastStatisticsResDto);
		}
	}
}
