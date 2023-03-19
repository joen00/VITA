package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class RhrDailyDto {
    private int dailyWearableRhr;
    private ZonedDateTime date;
}
