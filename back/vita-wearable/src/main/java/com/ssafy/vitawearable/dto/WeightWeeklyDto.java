package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class WeightWeeklyDto {
    private float weeklyWearableWeight;
    private float weeklyWearableMuscle;
    private float weeklyWearableFat;
    private ZonedDateTime date;
}
