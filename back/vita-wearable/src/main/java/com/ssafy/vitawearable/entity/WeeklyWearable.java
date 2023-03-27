package com.ssafy.vitawearable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "weekly_wearable")
public class WeeklyWearable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weeklyWearableId;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private ZonedDateTime date;
    private Float weeklyWearableWeight;
    private Float weeklyWearableMuscle;
    private Float weeklyWearableFat;
    private int weeklyWearableStep;
    private Long weeklyWearableEnergy;
    private int weeklyWearableRhr;
    private int weeklyWearableStress;
    private LocalTime weeklyWearableSleep;
    private LocalTime weeklyWearableLight;
    private LocalTime weeklyWearableRem;
    private LocalTime weeklyWearableAwake;
    private LocalTime weeklyWearableDeep;

}
