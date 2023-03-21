package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class StepMonthlyDto {
    private Long monthlyWearableStep;
    private ZonedDateTime date;
}
