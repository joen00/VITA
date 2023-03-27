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
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private ZonedDateTime date;
    private Float dailyWearableWeight;
    private Float dailyWearableMuscle;
    private Float dailyWearableFat;
    private int dailyWearableStep;
    private int dailyWearableEnergy;
    private int dailyWearableRhr;
    private int dailyWearableStress;
    private int dailyWearableScore;

}

