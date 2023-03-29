package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.ApiAverageDto;
import com.ssafy.vitawearable.dto.EnergyDailyDto;
import com.ssafy.vitawearable.dto.EnergyMonthlyDto;
import com.ssafy.vitawearable.dto.UserAverageDto;
import com.ssafy.vitawearable.service.Score;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.util.HeaderUtil;
import com.ssafy.vitawearable.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api("평균 데이터 컨트롤러 API")
@RequestMapping("/api/wearable/average")
public class AverageController {
    private final Wearable wearable;
    private final Score score;
    private final UserUtil userUtil;

    // 사용자 평균 데이터 리스트
    @ApiOperation(
            value = "사용자 평균 데이터 리스트",
            notes = "사용자 평균 데이터 리스트",
            response = UserAverageDto.class
    )
    @GetMapping("/user")
    public ResponseEntity<UserAverageDto> userAverage(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(score.userAverage(userId), HttpStatus.valueOf(200));
    }

    // API 전체 평균 데이터 리스트 전체
    @ApiOperation(
            value = "API 전체 평균 데이터 리스트 전체",
            notes = "API 전체 평균 데이터 리스트 전체",
            response = ApiAverageDto.class
    )

    @GetMapping("")
    public ResponseEntity<ApiAverageDto> apiTotalAverage() {
        return new ResponseEntity<>(score.apiTotalAverage(), HttpStatus.valueOf(200));
    }

    // API 선택 평균 데이터 리스트 전체
    @ApiOperation(
            value = "API 선택 평균 데이터 리스트 전체",
            notes = "API 선택 평균 데이터 리스트 전체",
            response = EnergyDailyDto.class
    )

    @GetMapping("/{userAge}/{userSex}")
    public ResponseEntity<ApiAverageDto> apiCustomAverage(@PathVariable int userAge, @PathVariable String userSex) {
        return new ResponseEntity<>(score.apiCustomAverage(userAge,userSex), HttpStatus.valueOf(200));
    }
}
