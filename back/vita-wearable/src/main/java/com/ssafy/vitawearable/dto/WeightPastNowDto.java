package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightPastNowDto {
    private float weekNowWearableWeight;
    private float weekLastWearableWeight;
    private float monthNowWearableWeight;
    private float monthLastWearableWeight;
    private float yearNowWearableWeight;
    private float yearLastWearableWeight;
}
