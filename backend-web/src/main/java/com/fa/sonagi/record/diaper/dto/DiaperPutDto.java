package com.fa.sonagi.record.diaper.dto;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperPutDto {

  private Long id;
  private LocalTime createdTime;
  private String memo;
}