package com.fa.sonagi.faq.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.faq.dto.FAQPostDto;
import com.fa.sonagi.faq.dto.FAQPutDto;
import com.fa.sonagi.faq.dto.FAQResDto;
import com.fa.sonagi.faq.entity.FAQ;
import com.fa.sonagi.faq.repository.FAQRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {
	private final FAQRepository faqRepository;

	/**
	 * FAQ 기록 아이디로 조회
	 */
	@Override
	public FAQResDto findFAQById(Long id) {
		FAQ faq = faqRepository.findById(id).orElseThrow();

		FAQResDto faqResDto = FAQResDto.builder()
			.id(faq.getId())
			.title(faq.getTitle())
			.content(faq.getContent())
			.build();
		return faqResDto;
	}

	/**
	 * FAQ 기록 아이디로 등록
	 */
	@Override
	@Transactional
	public void registFAQ(FAQPostDto faqPostDto) {
		FAQ faq = FAQ.builder()
			.userId(faqPostDto.getUserId())
			.title(faqPostDto.getTitle())
			.content(faqPostDto.getContent())
			.build();

		faqRepository.save(faq);
	}

	/**
	 * FAQ 기록 아이디로 조회
	 */
	@Override
	@Transactional
	public void updateFAQ(FAQPutDto faqPutDto) {
		FAQ faq = faqRepository.findById(faqPutDto.getId()).orElseThrow();
		faq.updateFAQ(faqPutDto.getTitle(), faqPutDto.getContent());
	}

	/**
	 * FAQ 기록 아이디로 조회
	 */
	@Override
	@Transactional
	public void deleteFAQ(Long FAQId) {
		FAQ faq = faqRepository.findById(FAQId).orElseThrow();
		faqRepository.delete(faq);
	}
}