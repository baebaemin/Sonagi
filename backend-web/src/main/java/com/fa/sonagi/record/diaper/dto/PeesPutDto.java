package com.fa.sonagi.record.diaper.dto;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeesPutDto {

  private Long id;
  private LocalTime createdTime;
  private String memo;
}