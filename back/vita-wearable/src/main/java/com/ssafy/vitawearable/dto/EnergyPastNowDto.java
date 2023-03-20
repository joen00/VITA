package com.ssafy.vitawearable.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnergyPastNowDto {
    private float weekNowWearableEnergy;
    private float weekLastWearableEnergy;
    private float monthNowWearableEnergy;
    private float monthLastWearableEnergy;
    private float yearNowWearableEnergy;
    private float yearLastWearableEnergy;
}
