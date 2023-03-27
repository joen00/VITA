package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 수면 데이터 Dto
public class SleepMonthlyDto {
    private LocalTime monthlyWearableSleep;
    private LocalTime monthlyWearableLight;
    private LocalTime monthlyWearableRem;
    private LocalTime monthlyWearableAwake;
    private LocalTime monthlyWearableDeep;
    private ZonedDateTime date;
}
