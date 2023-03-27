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
    private Time userAverageSleep;
    private Time userAverageLight;
    private Time userAverageRem;
    private Time userAverageAwake;
    private Time userAverageDeep;
}

