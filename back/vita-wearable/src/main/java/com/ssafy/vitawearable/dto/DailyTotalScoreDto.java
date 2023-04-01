package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 총합 점수 Dto
public class DailyTotalScoreDto {
    private int dailyWearableScore;
//    private ZonedDateTime date;
    private ZonedDateTime date;
}