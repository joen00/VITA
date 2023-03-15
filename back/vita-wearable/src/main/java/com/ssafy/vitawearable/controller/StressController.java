package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.StressDailyDto;
import com.ssafy.vitawearable.dto.StressMonthlyDto;
import com.ssafy.vitawearable.dto.StressWeeklyDto;
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
@RequestMapping("/wearable/user/stress")
public class StressController {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;

    // 스트레스 달별 데이터
    @GetMapping("/monthly")
    public ResponseEntity<List<StressMonthlyDto>> stressMonthly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MonthlyWearable> monthlyStress = monthlyWearableRepo.findByUsers_UserId(userId);
        List<StressMonthlyDto> stressMonthlyDtoList = monthlyStress.stream()
                .map(monthly -> mapper.map(monthly, StressMonthlyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(stressMonthlyDtoList, HttpStatus.valueOf(200));
    }

    // 스트레스 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<StressWeeklyDto>> stressWeekly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        List<StressWeeklyDto> stressWeeklyDtoList = weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StressWeeklyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(stressWeeklyDtoList,HttpStatus.valueOf(200));
    }

    // 스트레스 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<StressDailyDto>> stressDaily(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        List<StressDailyDto> stressDailyDtoList = dailyWearables.stream()
                .map(daily -> mapper.map(daily, StressDailyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(stressDailyDtoList,HttpStatus.valueOf(200));
    }
}
