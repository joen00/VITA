package com.ssafy.vitawearable.dto.Energy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 최근 export일 기준으로 해당 주와 저번 주, 해당 달과 저번 달, 해당 연도와 저번 연도 평균 Dto
public class EnergyPastAndNowDto {
    private Long weekNowWearableEnergy;
    private Long weekPastWearableEnergy;
    private Long monthNowWearableEnergy;
    private Long monthPastWearableEnergy;
    private Long yearNowWearableEnergy;
    private Long yearPastWearableEnergy;
}
