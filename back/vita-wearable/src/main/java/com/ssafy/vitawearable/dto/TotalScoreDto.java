package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 일일 총합 점수 Dto
public class TotalScoreDto {
    private ZonedDateTime createdDate;
    private int totalScore;
    private int totalScoreEnergy;
    private int totalScoreRhr;
    private int totalScoreStress;
    private int totalScoreStep;
    private int totalScoreSleep;
}
