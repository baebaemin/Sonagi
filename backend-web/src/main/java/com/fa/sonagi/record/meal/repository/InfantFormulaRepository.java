package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.meal.entity.InfantFormula;

public interface InfantFormulaRepository extends JpaRepository<InfantFormula, Long> {

	List<InfantFormula> findByBabyIdAndCreatedDate(Long babyId, LocalDate createdDate);
}
