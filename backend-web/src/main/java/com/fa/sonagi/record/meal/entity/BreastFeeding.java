package com.fa.sonagi.record.meal.entity;

import com.fa.sonagi.baby.entity.Baby;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Time;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "breast_feeding",
    indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class BreastFeeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "breast_feeding_id")
    private Long id;

    @Column(name = "baby_id")
    private Long babyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "memo")
    private String memo;

    @Column(name = "created_time")
    private Time createdTime;

    @Column(name = "created_date")
    private LocalDate createdDate;

    public void updateBreastFeeding(Long amount, String memo, Time createdTime) {
        this.amount = amount;
        this.memo = memo;
        this.createdTime = createdTime;
    }
}