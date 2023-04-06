package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// api 평균 dto
public class ApiAverageDto {
    private int apiAverageStep;
    private int apiAverageEnergy;
    private int apiAverageRhr;
    private int apiAverageStress;
    private int apiAverageSleep;
}
