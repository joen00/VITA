package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 심박수 데이터 Dto
public class RhrDailyDto {
    private int dailyWearableRhr;
//    private ZonedDateTime date;
    private ZonedDateTime date;
}
