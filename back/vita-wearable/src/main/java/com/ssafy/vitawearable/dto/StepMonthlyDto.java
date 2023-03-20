package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepMonthlyDto {
    private Long monthlyWearableStep;
    private int year;
    private int month;
}
