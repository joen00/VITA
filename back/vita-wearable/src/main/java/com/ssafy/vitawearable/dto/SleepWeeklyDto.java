package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 수면 데이터 Dto
public class SleepWeeklyDto {
    private Long weeklyWearableSleep;
    private int weeklyWearableLight;
    private int weeklyWearableRem;
    private int weeklyWearableCore;
    private int weeklyWearableDeep;
    private ZonedDateTime date;
}
