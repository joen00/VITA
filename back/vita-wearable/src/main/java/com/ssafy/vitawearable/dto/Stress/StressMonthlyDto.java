package com.ssafy.vitawearable.dto.Stress;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 수면 데이터 Dto
public class StressMonthlyDto {
    private int monthlyWearableStress;
    private ZonedDateTime date;
}
