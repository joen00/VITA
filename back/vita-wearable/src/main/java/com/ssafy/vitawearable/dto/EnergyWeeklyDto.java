package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 활동에너지 데이터 Dto
public class EnergyWeeklyDto {
    private Long weeklyWearableEnergy;
    private ZonedDateTime date;
}
