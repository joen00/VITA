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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("웨어러블 체중 컨트롤러 API")
@RequestMapping("/wearable/user/weight")
public class WeightController {
    private final Wearable wearable;
    private final WearablePast wearablePast;

    // 체중 월간 데이터
    @ApiOperation(
            value = "체중 월별 데이터 요청",
            notes = "userId를 통해 체중 월별 데이터를 json 형태로 반환한다",
            response = WeightMonthlyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/monthly")
    public ResponseEntity<List<WeightMonthlyDto>> weightMonthly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.weightMonthly(userId), HttpStatus.valueOf(200));
    }

    // 체중 주간 데이터
    @ApiOperation(
            value = "체중 주별 데이터 요청",
            notes = "userId를 통해 체중 주별 데이터를 json 형태로 반환한다",
            response = WeightWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<WeightWeeklyDto>> weightWeekly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.weightWeekly(userId), HttpStatus.valueOf(200));
    }

    // 체중 일간 데이터
    @ApiOperation(
            value = "체중 일별 데이터 요청",
            notes = "userId를 통해 체중 일별 데이터를 json 형태로 반환한다",
            response = WeightDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<WeightDailyDto>> weightDaily(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.weightDaily(userId), HttpStatus.valueOf(200));
    }

    // 체중 과거 비교 데이터
    @ApiOperation(
            value = "체중 과거,현재 데이터 요청",
            notes = "userId를 통해 체중 과거,현재 데이터를 json 형태로 반환한다",
            response = WeightPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<WeightPastAndNowDto> stepPast(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearablePast.weightPastAndNow(userId), HttpStatus.valueOf(200));
    }
}
