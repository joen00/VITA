package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class StressMonthlyDto {
    private int monthlyWearableStress;
    private ZonedDateTime date;
}
