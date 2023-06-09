package com.ssafy.vitawearable.controller;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.dto.Rhr.RhrDailyDto;
import com.ssafy.vitawearable.service.Score;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.service.WearableFriend;
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
@Api("친구 데이터 컨트롤러 API")
@RequestMapping("/api/wearable/friend")
public class FriendController {
    private final WearableFriend wearableFriend;
    private final Wearable wearable;
    private final Score score;
    private final UserUtil userUtil;

    // 친구 목록 리스트
    @ApiOperation(
            value = "친구 리스트",
            notes = "userId를 통해 친구 리스트를 json 형태로 반환한다",
            response = FriendDto.class,
            responseContainer = "List"
    )
    @GetMapping("")
    public ResponseEntity<List<FriendDto>> friendList(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(wearableFriend.getFriendList(userId), HttpStatus.valueOf(200));
    }


    // 친구들의 평균 데이터 리스트
    @ApiOperation(
            value = "친구들의 평균 데이터 리스트 요청",
            notes = "친구들 userId를 통해 평균 데이터 리스트를 json 형태로 반환한다",
            response = UserAverageDto.class,
            responseContainer = "List"
    )
    @PostMapping("/all")
    public ResponseEntity<List<UserAverageDto>> friendAverage(@RequestBody List<String> friendList) {
        return new ResponseEntity<>(score.friendAverage(friendList), HttpStatus.valueOf(200));
    }

    // 해당 유저 평균 데이터
    @ApiOperation(
            value = "해당 유저 평균 데이터 요청",
            notes = "userId를 통해 해당 유저 평균 데이터를 json 형태로 반환한다",
            response = RhrDailyDto.class,
            responseContainer = "List"
    )
    @GetMapping("/user")
    public ResponseEntity<UserAverageDto> userAverage(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(score.userAverage(userId), HttpStatus.valueOf(200));
    }
}
