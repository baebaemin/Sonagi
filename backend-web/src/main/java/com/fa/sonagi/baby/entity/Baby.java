package com.fa.sonagi.baby.entity;

import com.fa.sonagi.record.meal.entity.BabyFood;
import com.fa.sonagi.record.meal.entity.BreastFeeding;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "last_meal_time")
    private LocalDateTime lastMealTime;

    @Column(name = "last_diary_time")
    private LocalDateTime lastDiaryTime;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
