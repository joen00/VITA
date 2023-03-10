package com.ssafy.vitawearable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_average")
public class UserAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAverageId;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    private int age;
    private String sex;
    private int userAverageStep;
    private int userAverageStepDay;
    private int userAverageEnergy;
    private int userAverageEnergyDay;
    private int userAverageRhr;
    private int userAverageRhrDay;
    private int userAverageStress;
    private int userAverageStressDay;
    private Long userAverageSleep;
    private int userAverageSleepDay;
    private int userAverageLight;
    private int userAverageRem;
    private int userAverageCore;
    private int userAverageDeep;
    private int userAverageStageDay;

}
