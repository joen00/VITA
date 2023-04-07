package com.ssafy.vitawearable.dto.Energy;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 에너지 데이터 Dto
public class EnergyDailyDto {
    private Long dailyWearableEnergy;
    private ZonedDateTime date;
}
