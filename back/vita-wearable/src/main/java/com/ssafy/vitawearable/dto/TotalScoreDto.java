package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalScoreDto {
    private int year;
    private int month;
    private int day;
    private float totalScoreWeight;
    private int totalScoreEnergy;
    private int totalScoreRhr;
    private int totalScoreStress;
    private int totalScoreStep;
    private int totalScoreSleep;
}
