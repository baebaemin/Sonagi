package com.fa.sonagi.record.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.meal.entity.Feeding;

public interface FeedingsRepository extends JpaRepository<Feeding, Long> {
}
