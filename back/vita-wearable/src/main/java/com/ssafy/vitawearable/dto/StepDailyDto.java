package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class StepDailyDto {
    private Long dailyWearableStep;
    private ZonedDateTime date;
}
