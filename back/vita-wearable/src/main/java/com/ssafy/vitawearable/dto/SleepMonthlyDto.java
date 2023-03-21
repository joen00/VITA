package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class SleepMonthlyDto {
    private Long monthlyWearableSleep;
    private int monthlyWearableLight;
    private int monthlyWearableRem;
    private int monthlyWearableCore;
    private int monthlyWearableDeep;
    private ZonedDateTime date;
}
