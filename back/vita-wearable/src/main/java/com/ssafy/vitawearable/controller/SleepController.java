package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.service.WearablePast;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("웨어러블 수면 컨트롤러 API")
@RequestMapping("/api/wearable/user/sleep")
public class SleepController {
    private final Wearable wearable;
    private final WearablePast wearablePast;

    // 수면 달별 데이터
    @ApiOperation(
            value = "수면 월별 데이터 요청",
            notes = "userId를 통해 수면 월별 데이터를 json 형태로 반환한다",
            response = SleepMonthlyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/monthly")
    public ResponseEntity<List<SleepMonthlyDto>> sleepMonthly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.sleepMonthly(userId), HttpStatus.valueOf(200));
    }

    // 수면 주간 데이터
    @ApiOperation(
            value = "수면 주별 데이터 요청",
            notes = "userId를 통해 수면 주별 데이터를 json 형태로 반환한다",
            response = SleepWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<SleepWeeklyDto>> sleepWeekly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.sleepWeekly(userId), HttpStatus.valueOf(200));
    }

    // 수면 일간 데이터
    @ApiOperation(
            value = "수면 일별 데이터 요청",
            notes = "userId를 통해 수면 일별 데이터를 json 형태로 반환한다",
            response = SleepDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<SleepDailyDto>> sleepDaily(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.sleepDaily(userId), HttpStatus.valueOf(200));
    }

    // 수면 과거 비교 데이터
    @ApiOperation(
            value = "수면 과거,현재 데이터 요청",
            notes = "userId를 통해 수면 과거,현재 데이터를 json 형태로 반환한다",
            response = SleepPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<SleepPastAndNowDto> stepPast(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearablePast.sleepPastAndNow(userId), HttpStatus.valueOf(200));
    }
}
