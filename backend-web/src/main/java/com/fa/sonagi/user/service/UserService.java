package com.fa.sonagi.user.service;

import org.springframework.http.ResponseEntity;

import com.fa.sonagi.user.dto.NameDto;

public interface UserService {
	boolean checkEmail(String email) throws Exception;

	ResponseEntity<?> getUserInfo(String email);

	void updateName(NameDto nameDto);

	NameDto findName(Long id);
	// 재발급 로직 필요함.
	// ResponseEntity<?> reissue(UserTestReqDto.Reissue reissue);

}
