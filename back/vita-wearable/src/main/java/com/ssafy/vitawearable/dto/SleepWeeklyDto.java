package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SleepWeeklyDto {
    private Long weeklyWearableSleep;
    private int weeklyWearableLight;
    private int weeklyWearableRem;
    private int weeklyWearableCore;
    private int weeklyWearableDeep;
    private ZonedDateTime date;
}
