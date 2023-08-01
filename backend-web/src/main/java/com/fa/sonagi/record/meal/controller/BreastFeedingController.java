package com.fa.sonagi.record.meal.controller;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.service.BreastFeedingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BreastFeeding", description = "유축수유 API")
@RequestMapping("/api/breastFeedings")
@RestController
@RequiredArgsConstructor
public class BreastFeedingController {

  private final BreastFeedingService breastFeedingService;

  /**
   * 유축 수유 기록 조회
   */
  @GetMapping("/{breastFeedingId}")
  public ResponseEntity<?> getBreastFeeding(@PathVariable Long breastFeedingId) {
    MealResDto mealResDto = breastFeedingService.findBreastFeedingById(breastFeedingId);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 유축 수유 기록 등록
   */
  @PostMapping
  public ResponseEntity<?> registBreastFeeding(@RequestBody MealPostDto mealPostDto) {
    breastFeedingService.registBreastFeeding(mealPostDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 유축 수유 기록 수정
   */
  @PutMapping
  public ResponseEntity<?> updateBreastFeeding(@RequestBody MealPutDto mealPutDto) {
    breastFeedingService.updateBreastFeeding(mealPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 유축 수유 기록 삭제
   */
  @DeleteMapping("/{breastFeedingId}")
  public ResponseEntity<?> deleteBreastFeeding(@PathVariable Long breastFeedingId) {
    breastFeedingService.deleteBreastFeedingById(breastFeedingId);
    return ResponseEntity.ok().build();
  }
}
