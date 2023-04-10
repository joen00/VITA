package com.ssafy.vitawearable.dto.Rhr;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 심박수 데이터 Dto
public class RhrWeeklyDto {
    private int weeklyWearableRhr;
    private ZonedDateTime date;
}
