package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 걸음수 데이너 Dto
public class StepMonthlyDto {
    private Long monthlyWearableStep;
    private ZonedDateTime date;
}
