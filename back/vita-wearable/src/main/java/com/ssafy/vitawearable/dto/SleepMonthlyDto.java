package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepMonthlyDto {
    private Long monthlyWearableSleep;
    private int monthlyWearableLight;
    private int monthlyWearableRem;
    private int monthlyWearableCore;
    private int monthlyWearableDeep;
    private int year;
    private int month;
}
