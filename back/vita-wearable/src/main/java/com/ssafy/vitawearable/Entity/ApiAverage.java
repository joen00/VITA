package com.ssafy.vitawearable.Entity;

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
    private int apiAverageStep;
    private int apiAverageEnergy;
    private int apiAverageRhr;
    private int apiAverageStress;
    private int apiAverageSleep;
    private int apiAverageLight;
    private int apiAverageRem;
    private int apiAverageCore;
    private int apiAverageDeep;

}
