package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class StressDailyDto {
    private int dailyWearableStress;
    private ZonedDateTime date;
}
