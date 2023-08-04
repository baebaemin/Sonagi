package com.fa.sonagi.record.sleep.dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SleepResDto {
	private Long id;
	private Time createdTime;
	private Time endTime;
	private String memo;
}
