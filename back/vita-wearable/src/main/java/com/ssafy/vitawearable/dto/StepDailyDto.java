package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 일일 걸음수 데이터 Dto
public class StepDailyDto {
    private Long dailyWearableStep;
    private ZonedDateTime date;
}
