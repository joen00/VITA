package com.ssafy.vitawearable.dto;

import com.ssafy.vitawearable.entity.User;
import com.ssafy.vitawearable.entity.UserAverage;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
// 해당 유저의 평균 데이터 Dto
public class UserAverageDto {
    private String userNickname;
    private String userImg;
    private int userAverageStep;
    private int userAverageEnergy;
    private int userAverageRhr;
    private int userAverageStress;
    private int userAverageSleep;
    private int userAverageLight;
    private int userAverageRem;
    private int userAverageAwake;
    private int userAverageDeep;

    public UserAverageDto() {
        this.userAverageStep = 0;
        this.userAverageEnergy = 0;
        this.userAverageRhr = 0;
        this.userAverageStress = 0;
        this.userAverageSleep = 0;
        this.userAverageLight = 0;
        this.userAverageRem = 0;
        this.userAverageAwake = 0;
        this.userAverageDeep = 0;
    }
}
