package com.fa.sonagi.record.allCategory.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.allCategory.dto.AllCategoryResDto;
import com.fa.sonagi.record.allCategory.service.AllCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "AllCategory", description = "모든 카테고리 API")
@RequestMapping("/api/allRecords")
@RestController
@RequiredArgsConstructor
public class AllCategoryController {
	private final AllCategoryService allCategoryService;

	/**
	 * 모든 카테고리 조회
	 */
	@GetMapping
	public ResponseEntity<?> getAllRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		AllCategoryResDto allData = allCategoryService.getCombinedCategory(babyId, createdDate);

		return ResponseEntity.ok().body(allData);
	}
}
