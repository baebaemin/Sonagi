package com.fa.sonagi.record.diaper.dto;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperPostDto {

  @NotNull
  private Long userId;

  @NotNull
  private Long babyId;

  @NotNull
  private LocalDate createdDate;

  @NotNull
  private Time createdTime;
  private String memo;
}