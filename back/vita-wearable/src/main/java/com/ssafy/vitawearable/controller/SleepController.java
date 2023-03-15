package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.SleepDailyDto;
import com.ssafy.vitawearable.dto.SleepMonthlyDto;
import com.ssafy.vitawearable.dto.SleepWeeklyDto;
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
@RequestMapping("/wearable/user/sleep")
public class SleepController {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;

    // 수면 달별 데이터
    @GetMapping("/monthly")
    public ResponseEntity<List<SleepMonthlyDto>> sleepMonthly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MonthlyWearable> monthlySleep = monthlyWearableRepo.findByUsers_UserId(userId);
        List<SleepMonthlyDto> sleepMonthlyDtoList = monthlySleep.stream()
                .map(monthly -> mapper.map(monthly, SleepMonthlyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(sleepMonthlyDtoList, HttpStatus.valueOf(200));
    }

    // 수면 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<SleepWeeklyDto>> sleepWeekly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        List<SleepWeeklyDto> sleepWeeklyDtoList = weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, SleepWeeklyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(sleepWeeklyDtoList,HttpStatus.valueOf(200));
    }

    // 수면 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<SleepDailyDto>> sleepDaily(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        List<SleepDailyDto> sleepDailyDtoList = dailyWearables.stream()
                .map(daily -> mapper.map(daily, SleepDailyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(sleepDailyDtoList,HttpStatus.valueOf(200));
    }
}
