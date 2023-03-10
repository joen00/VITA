package com.ssafy.vitawearable.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "weekly_wearable")
public class WeeklyWearable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weeklyWearableId;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    private int year;
    private int month;
    private int week;
    private Float weeklyWearableWeight;
    private Float weeklyWearableMuscle;
    private Float weeklyWearableFat;
    private int weeklyWearableWeightDay;
    private int weeklyWearableStep;
    private int weeklyWearableStepDay;
    private Long weeklyWearableEnergy;
    private int weeklyWearableEnergyDay;
    private int weeklyWearableRhr;
    private int weeklyWearableRhrDay;
    private int weeklyWearableStress;
    private int weeklyWearableStressDay;
    private Long weeklyWearableSleep;
    private int weeklyWearableLight;
    private int weeklyWearableRem;
    private int weeklyWearableCore;
    private int weeklyWearableDeep;
    private int weeklyWearableSleepDay;

}
