package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepWeeklyDto {
    private Long weeklyWearableStep;
    private String year;
    private String month;
    private String week;
}
