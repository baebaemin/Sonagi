package com.fa.sonagi.record.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.activity.dto.ActivityPostDto;
import com.fa.sonagi.record.activity.dto.ActivityPutDto;
import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.activity.entity.Play;
import com.fa.sonagi.record.activity.repository.PlayRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlayServiceImpl implements PlayServeice {

  private final PlayRepository playRepository;

  /**
   * 놀이 기록 아이디로 조회
   */
  @Override
  public ActivityResDto findPlayById(Long id) {
    Play play = playRepository.findById(id).orElseThrow();

    ActivityResDto activityResDto = ActivityResDto.builder()
        .createdTime(play.getCreatedTime())
        .memo(play.getMemo())
        .build();

    return activityResDto;
  }

  /**
   * 놀이 기록 등록
   */
  @Override
  @Transactional
  public void registPlay(ActivityPostDto activityPostDto) {
    Play play = Play.builder()
        .userId(activityPostDto.getUserId())
        .babyId(activityPostDto.getBabyId())
        .createdDate(activityPostDto.getCreatedDate())
        .createdTime(activityPostDto.getCreatedTime())
        .endTime(activityPostDto.getEndTime())
        .memo(activityPostDto.getMemo())
        .build();

    playRepository.save(play);
  }

  /**
   * 놀이 기록 수정
   */
  @Override
  @Transactional
  public void updatePlay(ActivityPutDto activityPutDto) {
    Play play = playRepository.findById(activityPutDto.getId()).orElseThrow();
    play.updatePlay(activityPutDto.getCreatedTime(), activityPutDto.getEndTime(),
        activityPutDto.getMemo());
  }

  /**
   * 놀이 기록 삭제
   */
  @Override
  @Transactional
  public void deletePlay(Long id) {
    Play play = playRepository.findById(id).orElseThrow();
    playRepository.delete(play);
  }
}
