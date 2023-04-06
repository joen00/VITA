package com.ssafy.vitawearable.dto.Weight;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 일일 체중 데이터 Dto
public class WeightDailyDto {
    private float dailyWearableWeight;
    private float dailyWearableMuscle;
    private float dailyWearableFat;
    private ZonedDateTime date;
}
