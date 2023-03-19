package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiAverageDto {
    private int apiAverageStep;
    private int apiAverageEnergy;
    private int apiAverageRhr;
    private int apiAverageStress;
    private int apiAverageSleep;
    private int apiAverageLight;
    private int apiAverageRem;
    private int apiAverageCore;
    private int apiAverageDeep;

//    public UserAverageDto(UserAverage userAverage) {
//        userAverageStep = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageEnergy = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageRhr = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageStress = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageLight = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageRem = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageStep = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//        userAverageStep = userAverage.getUserAverageStep() / userAverage.getUserAverageStepDay();
//    }
}
