package com.ssafy.vitawearable.dto.Energy;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 에너지 데이터 Dto
public class EnergyMonthlyDto {
    private Long monthlyWearableEnergy;
    private ZonedDateTime date;
}
