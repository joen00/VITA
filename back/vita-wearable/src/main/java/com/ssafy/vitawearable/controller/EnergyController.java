package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.EnergyDailyDto;
import com.ssafy.vitawearable.dto.EnergyMonthlyDto;
import com.ssafy.vitawearable.dto.EnergyWeeklyDto;
import com.ssafy.vitawearable.entity.DailyWearable;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import com.ssafy.vitawearable.entity.WeeklyWearable;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wearable/user/energy")
public class EnergyController {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;

    // 활동량 달별 데이터
    @GetMapping("/monthly")
    public ResponseEntity<List<EnergyMonthlyDto>> energyMonthly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUsers_UserId(userId);
        List<EnergyMonthlyDto> energyMonthlyDtoList = monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, EnergyMonthlyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(energyMonthlyDtoList, HttpStatus.valueOf(200));
    }

    // 활동량 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<EnergyWeeklyDto>> energyWeekly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        List<EnergyWeeklyDto> energyWeeklyDtoList = weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, EnergyWeeklyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(energyWeeklyDtoList,HttpStatus.valueOf(200));
    }

    // 활동량 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<EnergyDailyDto>> energyDaily(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        List<EnergyDailyDto> energyDailyDtoList = dailyWearables.stream()
                .map(daily -> mapper.map(daily, EnergyDailyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(energyDailyDtoList,HttpStatus.valueOf(200));
    }

}
