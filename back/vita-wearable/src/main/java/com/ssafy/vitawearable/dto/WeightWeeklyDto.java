package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightWeeklyDto {
    private float weeklyWearableWeight;
    private float weeklyWearableMuscle;
    private float weeklyWearableFat;
    private String year;
    private String month;
    private String week;
}
