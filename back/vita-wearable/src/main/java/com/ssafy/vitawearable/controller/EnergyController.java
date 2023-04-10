package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.Energy.EnergyDailyDto;
import com.ssafy.vitawearable.dto.Energy.EnergyMonthlyDto;
import com.ssafy.vitawearable.dto.Energy.EnergyPastAndNowDto;
import com.ssafy.vitawearable.dto.Energy.EnergyWeeklyDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("웨어러블 활동량 컨트롤러 API")
@RequestMapping("/api/wearable/user/energy")
public class EnergyController {
    private final Wearable wearable;
    private final WearablePast wearablePast;
    private final UserUtil userUtil;
    // 활동량 달별 데이터
    @ApiOperation(
            value = "활동량 월별 데이터 요청",
            notes = "userId를 통해 활동량 월별 데이터를 json 형태로 반환한다",
            response = EnergyMonthlyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/monthly")
    public ResponseEntity<List<EnergyMonthlyDto>> loadEnergyMonthly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.energyMonthly(userId), HttpStatus.valueOf(200));
    }

    // 활동량 주간 데이터
    @ApiOperation(
            value = "활동량 주별 데이터 요청",
            notes = "userId를 통해 활동량 주별 데이터를 json 형태로 반환한다",
            response = EnergyWeeklyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/weekly")
    public ResponseEntity<List<EnergyWeeklyDto>> loadEnergyWeekly(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.energyWeekly(userId), HttpStatus.valueOf(200));
    }

    // 활동량 일간 데이터
    @ApiOperation(
            value = "활동량 일별 데이터 요청",
            notes = "userId를 통해 활동량 일별 데이터를 json 형태로 반환한다",
            response = EnergyDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/daily")
    public ResponseEntity<List<EnergyDailyDto>> loadEnergyDaily(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearable.energyDaily(userId), HttpStatus.valueOf(200));
    }

    // 활동에너지 과거 비교 데이터
    @ApiOperation(
            value = "활동에너지 과거,현재 데이터 요청",
            notes = "userId를 통해 활동에너지 과거,현재 데이터를 json 형태로 반환한다",
            response = EnergyPastAndNowDto.class
    )
    @GetMapping("/past")
    public ResponseEntity<EnergyPastAndNowDto> stepPast(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearablePast.energyPastAndNow(userId), HttpStatus.valueOf(200));
    }

}
