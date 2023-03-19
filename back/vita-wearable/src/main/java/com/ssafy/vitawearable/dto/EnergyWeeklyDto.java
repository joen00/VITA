package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnergyWeeklyDto {
    private Long weeklyWearableEnergy;
    private String year;
    private String month;
    private String week;
}
