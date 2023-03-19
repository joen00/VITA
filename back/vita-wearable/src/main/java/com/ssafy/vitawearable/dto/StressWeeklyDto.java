package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StressWeeklyDto {
    private int weeklyWearableStress;
    private String year;
    private String month;
    private String week;
}
