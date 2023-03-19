package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepWeeklyDto {
    private Long weeklyWearableSleep;
    private int weeklyWearableLight;
    private int weeklyWearableRem;
    private int weeklyWearableCore;
    private int weeklyWearableDeep;
    private String year;
    private String month;
    private String week;
}
