package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 걸음수 주간 Dto
public class StepWeeklyDto {
    private Long weeklyWearableStep;
    private ZonedDateTime date;
}
