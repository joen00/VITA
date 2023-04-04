package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.service.WearablePast;
import com.ssafy.vitawearable.util.HeaderUtil;
import com.ssafy.vitawearable.util.UserUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("웨어러블 스트레스 컨트롤러 API")
@RequestMapping("/api/wearable/user/stress")
public class StressController {
    private final Wearable wearable;
    private final WearablePast wearablePast;
    private final UserUtil userUtil;

    // 스트레스 달별 데이터
    @ApiOperation(
            value = "스트레스 월별 데이터 요청",
            notes = "userId를 통해 스트레스 월별 데이터를 json 형태로 반환한다",
            response = StressMonthlyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/monthly")
    public ResponseEntity<List<StressMonthlyDto>> stressMonthly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.stressMonthly(userId), HttpStatus.valueOf(200));
    }

    // 스트레스 주간 데이터
    @ApiOperation(
            value = "스트레스 주별 데이터 요청",
            notes = "userId를 통해 스트레스 주별 데이터를 json 형태로 반환한다",
            response = StressWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<StressWeeklyDto>> stressWeekly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.stressWeekly(userId), HttpStatus.valueOf(200));
    }

    // 스트레스 일간 데이터
    @ApiOperation(
            value = "스트레스 일별 데이터 요청",
            notes = "userId를 통해 스트레스 일별 데이터를 json 형태로 반환한다",
            response = StressDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<StressDailyDto>> stressDaily(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.stressDaily(userId), HttpStatus.valueOf(200));
    }

    // 스트레스 과거 비교 데이터
    @ApiOperation(
            value = "스트레스 과거,현재 데이터 요청",
            notes = "userId를 통해 스트레스 과거,현재 데이터를 json 형태로 반환한다",
            response = StressPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<StressPastAndNowDto> StressPast(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearablePast.stressPastAndNow(userId), HttpStatus.valueOf(200));
    }

    // 스트레스 less 날짜 반환
    @ApiOperation(
            value = "스트레스 less 날짜 반환",
            notes = "userId를 통해 스트레스 less 날짜 반환한다",
            response = String.class
    )
    @GetMapping("/less")
    public ResponseEntity<String> stressLess(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.stressLess(userId), HttpStatus.valueOf(200));
    }
}
