package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.repo.TotalScoreRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wearable")
public class TotalController {
    private final TotalScoreRepo totalScoreRepo;

    // 종합 점수 평균 반환
    @GetMapping("/score")
    public ResponseEntity<List<TotalScore>> totalScore(@RequestHeader String userId) {
        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore, HttpStatus.valueOf(200));
    }

    // 연도별 데일리 종합 점수
    //
    @GetMapping("/user/score/{year}")
    public ResponseEntity<List<TotalScore>> totalDailyScore(@RequestHeader String userId, @PathVariable int year) {
        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(totalScore,HttpStatus.valueOf(200));
    }
}
