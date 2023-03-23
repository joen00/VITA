package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 스트레스 데이터 Dto
public class StressDailyDto {
    private int dailyWearableStress;
    private ZonedDateTime date;
}
