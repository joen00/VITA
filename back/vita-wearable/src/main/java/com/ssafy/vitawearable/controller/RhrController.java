package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.RhrDailyDto;
import com.ssafy.vitawearable.dto.RhrMonthlyDto;
import com.ssafy.vitawearable.dto.RhrWeeklyDto;
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
@RequestMapping("/wearable/user/rhr")
public class RhrController {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;

    // 심박수 달별 데이터
    @GetMapping("/monthly")
    public ResponseEntity<List<RhrMonthlyDto>> rhrMonthly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<MonthlyWearable> monthlyRhr = monthlyWearableRepo.findByUsers_UserId(userId);
        List<RhrMonthlyDto> rhrMonthlyDtoList = monthlyRhr.stream()
                .map(monthly -> mapper.map(monthly, RhrMonthlyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(rhrMonthlyDtoList, HttpStatus.valueOf(200));
    }

    // 심박수 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<RhrWeeklyDto>> rhrWeekly(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        List<RhrWeeklyDto> rhrWeeklyDtoList = weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, RhrWeeklyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(rhrWeeklyDtoList,HttpStatus.valueOf(200));
    }

    // 심박수 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<RhrDailyDto>> rhrDaily(@RequestHeader String userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        List<RhrDailyDto> rhrDailyDtoList = dailyWearables.stream()
                .map(daily -> mapper.map(daily, RhrDailyDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(rhrDailyDtoList,HttpStatus.valueOf(200));
    }
}
