package com.ssafy.vitawearable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "api_average")
public class ApiAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiAverageId;
    private int age;
    private String sex;
    private Long apiAverageStep;
    private int apiAverageStepCnt;
    private Long apiAverageEnergy;
    private int apiAverageEnergyCnt;
    private Long apiAverageRhr;
    private int apiAverageRhrCnt;
    private Long apiAverageStress;
    private int apiAverageStressCnt;
    private Long apiAverageSleep;
    private int apiAverageSleepCnt;
//    private int apiAverageLight;
//    private int apiAverageRem;
//    private int apiAverageAwake;
//    private int apiAverageDeep;

}
