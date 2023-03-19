package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.ApiAverageDto;
import com.ssafy.vitawearable.dto.EnergyMonthlyDto;
import com.ssafy.vitawearable.dto.UserAverageDto;
import com.ssafy.vitawearable.service.Wearable;
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
@Api("평균 데이터 컨트롤러 API")
@RequestMapping("/wearable/average")
public class AverageController {
    private final Wearable wearable;

    // 사용자 평균 데이터 리스트
    @ApiOperation(
            value = "사용자 평균 데이터 리스트",
            notes = "사용자 평균 데이터 리스트"
    )
    @GetMapping("/user")
    public ResponseEntity<UserAverageDto> userAverage(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.userAverage(userId), HttpStatus.valueOf(200));
    }

    // API 전체 평균 데이터 리스트 전체
    @ApiOperation(
            value = "API 전체 평균 데이터 리스트 전체",
            notes = "API 전체 평균 데이터 리스트 전체"
    )

    @GetMapping("")
    public ResponseEntity<ApiAverageDto> apiTotalAverage() {
        return new ResponseEntity<>(wearable.apiTotalAverage(), HttpStatus.valueOf(200));
    }

    // API 선택 평균 데이터 리스트 전체
    @ApiOperation(
            value = "API 선택 평균 데이터 리스트 전체",
            notes = "API 선택 평균 데이터 리스트 전체"
    )

    @GetMapping("/{userAge}/{userSex}")
    public ResponseEntity<ApiAverageDto> apiCustomAverage(@PathVariable int userAge, @PathVariable String userSex) {
        return new ResponseEntity<>(wearable.apiCustomAverage(userAge,userSex), HttpStatus.valueOf(200));
    }
}
