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
@Table(name = "daily_wearable")
public class DailyWearable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyWearableId;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private ZonedDateTime date;
    private Float dailyWearableWeight;
    private Float dailyWearableMuscle;
    private Float dailyWearableFat;
    private int dailyWearableStep;
    private Long dailyWearableEnergy;
    private int dailyWearableRhr;
    private int dailyWearableStress;
    private Long dailyWearableSleep;
    private int dailyWearableLight;
    private int dailyWearableRem;
    private int dailyWearableCore;
    private int dailyWearableDeep;
    private int dailyWearableScore;

}

