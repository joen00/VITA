package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RhrWeeklyDto {
    private int weeklyWearableRhr;
    private String year;
    private String month;
    private String week;
}
