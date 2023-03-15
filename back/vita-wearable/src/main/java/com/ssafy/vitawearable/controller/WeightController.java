package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.WeightDailyDto;
import com.ssafy.vitawearable.dto.WeightMonthlyDto;
import com.ssafy.vitawearable.dto.WeightWeeklyDto;
import com.ssafy.vitawearable.entity.DailyWearable;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.entity.WeeklyWearable;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wearable/user/weight")
public class WeightController {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;

    // 체중 월간 데이터
    @GetMapping("/monthly")
    public ResponseEntity<List<WeightMonthlyDto>> weightMonthly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MonthlyWearable> monthlyWeight = monthlyWearableRepo.findByUsers_UserId(userId);
        List<WeightMonthlyDto> weightMonthlyDtoList = monthlyWeight.stream()
                .map(monthly -> mapper.map(monthly, WeightMonthlyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(weightMonthlyDtoList,HttpStatus.valueOf(200));
    }

    // 체중 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<WeightWeeklyDto>> weightWeekly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        List<WeightWeeklyDto> weightWeeklyDtoList = weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, WeightWeeklyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(weightWeeklyDtoList,HttpStatus.valueOf(200));
    }

    // 체중 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<WeightDailyDto>> weightDaily(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        List<WeightDailyDto> weightDailyDtoList = dailyWearables.stream()
                .map(daily -> mapper.map(daily, WeightDailyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(weightDailyDtoList,HttpStatus.valueOf(200));
    }

//    // 1년전 평균과 이번해 평균, 1달전 평균과 이번달 평균, 1주전 평균과 이번주 평균 데이터 반환
//    @GetMapping("/past")
//    public ResponseEntity<List<TotalScore>> weightPath(@RequestHeader String userId) {
//        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
//        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
//    }
}
