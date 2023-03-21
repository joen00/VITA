package com.ssafy.vitawearable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monthly_wearable")
public class MonthlyWearable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyWearableId;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private ZonedDateTime date;
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
