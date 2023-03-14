package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.WeightDto;
import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.repo.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<WeightDto>> weightMonthly(@RequestHeader String userId) {
        List<TotalScore> totalScore = monthlyWearableRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
    }

    // 체중 주간 데이터
    @GetMapping("/weekly")
    public ResponseEntity<List<TotalScore>> weightWeekly(@RequestHeader String userId) {
        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
    }

    // 체중 일간 데이터
    @GetMapping("/daily")
    public ResponseEntity<List<TotalScore>> weightDaily(@RequestHeader String userId) {
        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
    }

    // 1년전 평균과 이번해 평균, 1달전 평균과 이번달 평균, 1주전 평균과 이번주 평균 데이터 반환
    @GetMapping("/past")
    public ResponseEntity<List<TotalScore>> weightPath(@RequestHeader String userId) {
        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
    }
}
