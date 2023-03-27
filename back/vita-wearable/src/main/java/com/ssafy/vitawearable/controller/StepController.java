package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.StepDailyDto;
import com.ssafy.vitawearable.dto.StepMonthlyDto;
import com.ssafy.vitawearable.dto.StepPastAndNowDto;
import com.ssafy.vitawearable.dto.StepWeeklyDto;
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
@Api("웨어러블 걸음수 컨트롤러 API")
@RequestMapping("/api/wearable/user/step")
public class StepController {
    private final Wearable wearable;
    private final WearablePast wearablePast;

    // 걸음수 달별 데이터
    @ApiOperation(
            value = "걸음수 월별 데이터 요청",
            notes = "userId를 통해 걸음수 월별 데이터를 json 형태로 반환한다",
            response = StepMonthlyDto.class,
            responseContainer = "List"
    )
//    @GetMapping("/monthly")
//    public ResponseEntity<List<StepMonthlyDto>> stepMonthly(@RequestHeader("token") String token) {
//        String userId = wearable.getUserId(token);
//        return new ResponseEntity<>(wearable.stepMonthly(userId), HttpStatus.valueOf(200));
//    }
    @GetMapping("/monthly")
    public ResponseEntity<List<StepMonthlyDto>> stepMonthly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.stepMonthly(userId), HttpStatus.valueOf(200));
    }

    // 걸음수 주간 데이터
    @ApiOperation(
            value = "걸음수 주별 데이터 요청",
            notes = "userId를 통해 걸음수 주별 데이터를 json 형태로 반환한다",
            response = StepWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<StepWeeklyDto>> stepWeekly(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.stepWeekly(userId), HttpStatus.valueOf(200));
    }

    // 걸음수 일간 데이터
    @ApiOperation(
            value = "걸음수 일별 데이터 요청",
            notes = "userId를 통해 걸음수 일별 데이터를 json 형태로 반환한다",
            response = StepDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<StepDailyDto>> stepDaily(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.stepDaily(userId), HttpStatus.valueOf(200));
    }

    // 걸음수 과거 비교 데이터
    @ApiOperation(
            value = "걸음수 과거,현재 데이터 요청",
            notes = "userId를 통해 걸음수 과거,현재 데이터를 json 형태로 반환한다",
            response = StepPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<StepPastAndNowDto> stepPast(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearablePast.stepPastAndNow(userId), HttpStatus.valueOf(200));
    }
}
