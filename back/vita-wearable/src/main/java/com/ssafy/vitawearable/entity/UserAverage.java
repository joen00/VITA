package com.ssafy.vitawearable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_average")
public class UserAverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAverageId;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private int userAverageStep;
    private int userAverageEnergy;
    private int userAverageRhr;
    private int userAverageStress;
    private int userAverageSleep;
    private int userAverageLight;
    private int userAverageRem;
    private int userAverageAwake;
    private int userAverageDeep;
}

