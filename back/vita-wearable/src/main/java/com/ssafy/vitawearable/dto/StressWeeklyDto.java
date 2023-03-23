package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 수면 데이터 Dto
public class StressWeeklyDto {
    private int weeklyWearableStress;
    private ZonedDateTime date;
}
