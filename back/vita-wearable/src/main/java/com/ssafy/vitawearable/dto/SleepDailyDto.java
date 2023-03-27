package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 수면 데이터 Dto. 수면단계는 onetomany 테이블로 변환가능
public class SleepDailyDto {
    private String dailySleepStage;
    private ZonedDateTime dailySleepStart;
    private ZonedDateTime dailySleepEnd;
    private LocalTime dailySleepTotal;
}
