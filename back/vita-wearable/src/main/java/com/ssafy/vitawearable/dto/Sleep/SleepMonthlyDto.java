package com.ssafy.vitawearable.dto.Sleep;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 수면 데이터 Dto
public class SleepMonthlyDto {
    private int monthlyWearableSleep;
    private int monthlyWearableLight;
    private int monthlyWearableRem;
    private int monthlyWearableAwake;
    private int monthlyWearableDeep;
    private ZonedDateTime date;
}
