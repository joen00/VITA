package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
public class WeightDailyDto {
    private float dailyWearableWeight;
    private float dailyWearableMuscle;
    private float dailyWearableFat;
    private ZonedDateTime date;
}
