package com.fa.sonagi.record.diaper.entity;

import jakarta.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poop",
    indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class Poops {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "poop_id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "baby_id")
  private Long babyId;

  @Column(name = "created_date")
  private LocalDate createdDate;

  @Column(name = "created_time")
  private Time createdTime;

  @Column(name = "memo")
  private String memo;

  public void updatePoops(Time createdTime, String memo) {
    this.createdTime = createdTime;
    this.memo = memo;
  }

}
