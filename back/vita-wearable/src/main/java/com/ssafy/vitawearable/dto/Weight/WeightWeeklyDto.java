package com.ssafy.vitawearable.dto.Weight;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
// 주간 무게 데이터 Dto
public class WeightWeeklyDto {
    private float weeklyWearableWeight;
    private float weeklyWearableMuscle;
    private float weeklyWearableFat;
    private ZonedDateTime date;
}
