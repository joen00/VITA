package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnergyMonthlyDto {
    private Long monthlyWearableEnergy;
    private int year;
    private int month;
}
