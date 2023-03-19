package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.TotalScoreDto;
import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.repo.TotalScoreRepo;
import com.ssafy.vitawearable.service.Wearable;
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
    private final Wearable wearable;

    // 종합 점수 반환(최근것을 왼쪽에, 나머지 5개를 오른쪽에 배치)
    @GetMapping("/score")
    public ResponseEntity<List<TotalScoreDto>> totalScore(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
//        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(wearable.totalScore(userId), HttpStatus.valueOf(200));
    }

    // 연도별 데일리 종합 점수
    @GetMapping("/user/score/{year}")
    public ResponseEntity<List<?>> totalDailyScore(@RequestHeader("token") String token, @PathVariable int year) {
        String userId = wearable.getUserId(token);
//        List<TotalScore> totalScore = totalScoreRepo.findByUsers_UserId(userId);
        return new ResponseEntity<>(wearable.yearTotalScore(userId,year),HttpStatus.valueOf(200));
    }
}
