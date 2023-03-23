package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
// 월간 체중 데이터 Dto
public class WeightMonthlyDto {
    private Float monthlyWearableWeight;
    private Float monthlyWearableMuscle;
    private Float monthlyWearableFat;
    private ZonedDateTime date;
}
