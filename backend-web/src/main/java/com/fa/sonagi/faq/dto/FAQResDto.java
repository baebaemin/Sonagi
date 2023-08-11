package com.fa.sonagi.faq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FAQResDto {
	private Long FAQId;
	private String title;
	private String content;
}
