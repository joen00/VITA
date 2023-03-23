package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
// 일일 수면 데이터 Dto. 수면단계는 onetomany 테이블로 변환가능
public class SleepDailyDto {
    private Long dailyWearableSleep;
    private int dailyWearableLight;
    private int dailyWearableRem;
    private int dailyWearableCore;
    private int dailyWearableDeep;
    private ZonedDateTime date;
}
