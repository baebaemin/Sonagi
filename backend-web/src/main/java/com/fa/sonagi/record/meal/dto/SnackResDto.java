package com.fa.sonagi.record.meal.dto;

import java.sql.Time;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SnackResDto {
  private Long id;
  private String memo;
  private Time createdTime;
}
