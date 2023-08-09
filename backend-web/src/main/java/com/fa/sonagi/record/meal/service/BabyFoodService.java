package com.fa.sonagi.record.meal.service;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;

public interface BabyFoodService {
	MealResDto findBabyFoodById(Long id);

	void registBabyFood(MealPostDto mealPostDto);

	void updateBabyFood(MealPutDto mealPutDto);

	void deleteBabyFoodById(Long id);
}
