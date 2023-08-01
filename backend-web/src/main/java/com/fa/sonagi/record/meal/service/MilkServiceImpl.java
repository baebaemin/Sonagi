package com.fa.sonagi.record.meal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.Milk;
import com.fa.sonagi.record.meal.repository.MilkRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MilkServiceImpl implements MilkService {

  private final MilkRepository milkRepository;

  /**
   * 우유 기록 아이디로 조회
   */
  @Override
  public MealResDto findMilkById(Long id) {
    Milk milk = milkRepository.findById(id).orElseThrow();

    MealResDto mealResDto = MealResDto.builder()
        .id(milk.getId())
        .amount(milk.getAmount())
        .memo(milk.getMemo())
        .createdTime(milk.getCreatedTime())
        .build();

    return mealResDto;
  }

  /**
   * 우유 기록 등록
   */
  @Override
  @Transactional
  public void registMilk(MealPostDto mealPostDto) {
    Milk milk = Milk.builder()
        .userId(mealPostDto.getUserId())
        .babyId(mealPostDto.getBabyId())
        .createdTime(mealPostDto.getCreatedTime())
        .createdDate(mealPostDto.getCreatedDate())
        .amount(mealPostDto.getAmount())
        .memo(mealPostDto.getMemo())
        .build();

    milkRepository.save(milk);
  }

  /**
   * 우유 기록 수정
   */
  @Override
  @Transactional
  public void updateMilk(MealPutDto mealPutDto) {
    Milk milk = milkRepository.findById(mealPutDto.getId()).orElseThrow();

    milk.updateMilk(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
  }

  /**
   * 우유 기록 삭제
   */
  @Override
  @Transactional
  public void deleteMilkById(Long id) {
    Milk milk = milkRepository.findById(id).orElseThrow();

    milkRepository.delete(milk);
  }
}
