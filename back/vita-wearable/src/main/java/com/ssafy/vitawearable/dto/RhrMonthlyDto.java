package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class RhrMonthlyDto {
    private int monthlyWearableRhr;
    private ZonedDateTime date;
}
