package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeightMonthlyDto {
    private Float monthlyWearableWeight;
    private Float monthlyWearableMuscle;
    private Float monthlyWearableFat;
    private int year;
    private int month;
}
