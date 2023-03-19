package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class SleepDailyDto {
    private Long dailyWearableSleep;
    private int dailyWearableLight;
    private int dailyWearableRem;
    private int dailyWearableCore;
    private int dailyWearableDeep;
    private ZonedDateTime date;
}
