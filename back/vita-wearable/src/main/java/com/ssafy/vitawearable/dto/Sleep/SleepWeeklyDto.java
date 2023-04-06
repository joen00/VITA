package com.ssafy.vitawearable.dto.Sleep;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 수면 데이터 Dto
public class SleepWeeklyDto {
    private int weeklyWearableSleep;
    private int weeklyWearableLight;
    private int weeklyWearableRem;
    private int weeklyWearableAwake;
    private int weeklyWearableDeep;
    private ZonedDateTime date;
}
