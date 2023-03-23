package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 심박수 데이터 Dto
public class RhrMonthlyDto {
    private int monthlyWearableRhr;
    private ZonedDateTime date;
}
