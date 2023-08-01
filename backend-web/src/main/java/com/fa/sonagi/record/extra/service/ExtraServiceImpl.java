package com.fa.sonagi.record.extra.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.extra.dto.ExtraPostDto;
import com.fa.sonagi.record.extra.dto.ExtraPutDto;
import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.extra.entity.Extra;
import com.fa.sonagi.record.extra.repository.ExtraRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExtraServiceImpl implements ExtraService {

  private final ExtraRepository extraRepository;

  /**
   * 기타 기록 아이디로 조회
   */
  @Override
  public ExtraResDto findExtraById(Long id) {
    Extra extra = extraRepository.findById(id).orElseThrow();

    ExtraResDto extraResDto = ExtraResDto.builder()
        .createdTime(extra.getCreatedTime())
        .memo(extra.getMemo())
        .build();

    return extraResDto;
  }

  /**
   * 기타 기록 등록
   */
  @Override
  @Transactional
  public void registExtra(ExtraPostDto extraPostDto) {
    Extra extra = Extra.builder()
        .userId(extraPostDto.getUserId())
        .babyId(extraPostDto.getBabyId())
        .createdDate(extraPostDto.getCreatedDate())
        .createdTime(extraPostDto.getCreatedTime())
        .memo(extraPostDto.getMemo())
        .build();

    extraRepository.save(extra);
  }

  /**
   * 기타 기록 수정
   */
  @Override
  @Transactional
  public void updateExtra(ExtraPutDto extraPutDto) {
    Extra extra = extraRepository.findById(extraPutDto.getId()).orElseThrow();
    extra.updateExtra(extraPutDto.getCreatedTime(), extraPutDto.getMemo());
  }

  /**
   * 기타 기록 삭제
   */
  @Override
  @Transactional
  public void deleteExtra(Long id) {
    Extra extra = extraRepository.findById(id).orElseThrow();
    extraRepository.delete(extra);
  }
}
