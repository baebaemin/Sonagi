package com.fa.sonagi.record.pumpingBreast.dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PumpingBreastResDto {
	private Long id;
	private Long amount;
	private String memo;
	private Time createdTime;
}
