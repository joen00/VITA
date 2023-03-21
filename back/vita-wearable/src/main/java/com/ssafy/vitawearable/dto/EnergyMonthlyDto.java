package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class EnergyMonthlyDto {
    private Long monthlyWearableEnergy;
    private ZonedDateTime date;
}
