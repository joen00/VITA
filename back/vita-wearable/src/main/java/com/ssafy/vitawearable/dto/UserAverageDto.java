package com.ssafy.vitawearable.dto;

import com.ssafy.vitawearable.entity.UserAverage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 해당 유저의 평균 데이터 Dto
public class UserAverageDto {
    private int userAverageStep;
    private int userAverageEnergy;
    private int userAverageRhr;
    private int userAverageStress;
    private int userAverageSleep;
    private int userAverageLight;
    private int userAverageRem;
    private int userAverageCore;
    private int userAverageDeep;

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
