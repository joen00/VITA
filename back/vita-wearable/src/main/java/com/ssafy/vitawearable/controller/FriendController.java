package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.service.Wearable;
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
@Api("친구 데이터 컨트롤러 API")
@RequestMapping("/wearable/friend")
public class FriendController {
    private final Wearable wearable;

    // 친구 목록 리스트
//    @ApiOperation(
//            value = "심박수 월별 데이터 요청",
//            notes = "userId를 통해 심박수 월별 데이터를 json 형태로 반환한다",
//            response = RhrMonthlyDto.class,
//            responseContainer = "List"
//    )
//    @GetMapping("")
//    public ResponseEntity<List<FriendDto>> friendList(@RequestHeader("token") String token) {
//        String userId = wearable.getUserId(token);
//        return new ResponseEntity<>(wearable.friendList(userId), HttpStatus.valueOf(200));
//    }

    // 친구들의 평균 데이터 리스트
//    @ApiOperation(
//            value = "심박수 주별 데이터 요청",
//            notes = "userId를 통해 심박수 주별 데이터를 json 형태로 반환한다",
//            response = RhrWeeklyDto.class,
//            responseContainer = "List"
//    )
//    @GetMapping("/all")
//    public ResponseEntity<List<UserAverageDto>> friendAverage(@RequestHeader("token") String token) {
//        String userId = wearable.getUserId(token);
//        return new ResponseEntity<>(wearable.friendAverage(userId), HttpStatus.valueOf(200));
//    }

    // 해당 유저 평균 데이터
    @ApiOperation(
            value = "심박수 일별 데이터 요청",
            notes = "userId를 통해 심박수 일별 데이터를 json 형태로 반환한다",
            response = RhrDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/user")
    public ResponseEntity<UserAverageDto> userAverage(@RequestHeader("token") String token) {
        String userId = wearable.getUserId(token);
        return new ResponseEntity<>(wearable.userAverage(userId), HttpStatus.valueOf(200));
    }

}
