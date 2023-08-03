package com.fa.sonagi.baby.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baby")
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baby_id")
    private Long id;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false, length = 2)
    private String gender;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "last_meal_time")
    private LocalDateTime lastMealTime;

    @Column(name = "last_diary_time")
    private LocalDateTime lastDiaryTime;

    @Column(name = "deleted_at", nullable = false)
    private LocalDate deletedAt;

    @Column(name = "is_deleted", nullable = false, length = 2)
    private String isDeleted;
}
