package com.fa.sonagi.record.meal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.meal.dto.AllPumpingBreastResDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.service.PumpingBreastCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PumpingBreast", description = "유축 api")
@RequestMapping("/api/pumpingBreastRecords")
@RestController
@RequiredArgsConstructor
public class PumpingBreastCategoryController {

	private final PumpingBreastCategoryService pumpingBreastCategoryService;

	/**
	 * 유축 카테고리 조회
	 */
	@GetMapping
	public ResponseEntity<?> getPumpingBreastRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		List<MealResDto> pumpingBreastList = pumpingBreastCategoryService.findAllPumpingBreast(babyId, createdDate);

		AllPumpingBreastResDto allPumpingBreastResDto = new AllPumpingBreastResDto();
		allPumpingBreastResDto.setPumpingBreasts(pumpingBreastList);

		return ResponseEntity.ok().body(allPumpingBreastResDto);
	}
}
