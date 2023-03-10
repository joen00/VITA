package com.ssafy.vitawearable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monthly_wearable")
public class MonthlyWearable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyWearableId;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    private int year;
    private int month;
    private Float monthlyWearableWeight;
    private Float monthlyWearableMuscle;
    private Float monthlyWearableFat;
    private int monthlyWearableWeightDay;
    private int monthlyWearableStep;
    private int monthlyWearableStepDay;
    private Long monthlyWearableEnergy;
    private int monthlyWearableEnergyDay;
    private int monthlyWearableRhr;
    private int monthlyWearableRhrDay;
    private int monthlyWearableStress;
    private int monthlyWearableStressDay;
    private Long monthlyWearableSleep;
    private int monthlyWearableLight;
    private int monthlyWearableRem;
    private int monthlyWearableCore;
    private int monthlyWearableDeep;
    private int monthlyWearableSleepDay;

}
