package com.fa.sonagi.baby.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.baby.dto.BabyCodePosDto;
import com.fa.sonagi.baby.dto.BabyCodeResDto;
import com.fa.sonagi.baby.dto.BabyInfoPostDto;
import com.fa.sonagi.baby.service.BabyService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "BabyInfo", description = "아기 정보 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BabyController {

	private final BabyService babyService;

	/**
	 * 아기 정보 생성
	 */
	@PostMapping("/babyInfos")
	public ResponseEntity<?> registBaby(@RequestBody BabyInfoPostDto babyInfoPostDto) {
		babyService.registBabyInfo(babyInfoPostDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 아기 코드 조회
	 */
	@GetMapping("/babyCode")
	public ResponseEntity<?> getBabyCode(@RequestParam Long babyId) {
		BabyCodeResDto babyCode = babyService.getBabyCode(babyId);
		return ResponseEntity.ok().body(babyCode);
	}

	/**
	 * 아기 코드로 아기 정보 등록
	 */
	@PostMapping("/babyCode")
	public ResponseEntity<?> registBabyByBabyCode(@RequestBody BabyCodePosDto babyCodePosDto) {
		babyService.registUserBabyByCode(babyCodePosDto);
		return ResponseEntity.ok().build();
	}
}
