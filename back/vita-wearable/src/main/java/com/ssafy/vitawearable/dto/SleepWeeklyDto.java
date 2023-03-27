package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 수면 데이터 Dto
public class SleepWeeklyDto {
    private LocalTime weeklyWearableSleep;
    private LocalTime weeklyWearableLight;
    private LocalTime weeklyWearableRem;
    private LocalTime weeklyWearableAwake;
    private LocalTime weeklyWearableDeep;
    private ZonedDateTime date;
}
