package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class EnergyDailyDto {
    private Long dailyWearableEnergy;
    private ZonedDateTime date;
}
