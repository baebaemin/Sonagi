package com.fa.sonagi.record.meal.service;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.BabyFood;
import com.fa.sonagi.record.meal.repository.BabyFoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BabyFoodsServiceImpl implements BabyFoodsService {

  private final BabyFoodsRepository babyFoodsRepository;

  /**
   * 이유식 기록 아이디로 조회
   */
  @Override
  public MealResDto findBabyFoodById(Long id) {
    BabyFood babyFood = babyFoodsRepository.findById(id).orElseThrow();

    MealResDto mealResDto = MealResDto.builder()
        .amount(babyFood.getAmount())
        .memo(babyFood.getMemo())
        .createdTime(babyFood.getCreatedTime())
        .build();
    
    return mealResDto;
  }

  /**
   * 이유식 기록 등록
   */
  @Override
  @Transactional
  public void registBabyFood(MealPostDto mealPostDto) {
    BabyFood babyFood = BabyFood.builder()
        .userId(mealPostDto.getUserId())
        .babyId(mealPostDto.getBabyId())
        .createdTime(mealPostDto.getCreatedTime())
        .createdDate(mealPostDto.getCreatedDate())
        .amount(mealPostDto.getAmount())
        .memo(mealPostDto.getMemo())
        .build();

    babyFoodsRepository.save(babyFood);
  }

  /**
   * 이유식 기록 수정
   */
  @Override
  @Transactional
  public void updateBabyFood(MealPutDto mealPutDto) {
    BabyFood babyFood = babyFoodsRepository.findById(mealPutDto.getId()).orElseThrow();

    babyFood.updateBabyFood(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
  }

  /**
   * 이유식 기록 삭제
   */
  @Override
  @Transactional
  public void deleteBabyFoodById(Long id) {
    BabyFood babyFood = babyFoodsRepository.findById(id).orElseThrow();

    babyFoodsRepository.delete(babyFood);
  }
}
