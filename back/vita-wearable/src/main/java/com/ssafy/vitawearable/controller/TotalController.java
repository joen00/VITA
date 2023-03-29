package com.ssafy.vitawearable.controller;


import com.ssafy.vitawearable.dto.DailyTotalScoreDto;
import com.ssafy.vitawearable.dto.TotalScoreDto;
import com.ssafy.vitawearable.dto.TotalScoreYearDto;
import com.ssafy.vitawearable.service.Score;
import com.ssafy.vitawearable.service.Wearable;
import com.ssafy.vitawearable.util.HeaderUtil;
import com.ssafy.vitawearable.util.UserUtil;
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
@RequestMapping("/api/wearable")
public class TotalController {
    private final Wearable wearable;
    private final Score score;
    private final UserUtil userUtil;

    // 종합 점수 반환(프론트에선 최근것을 왼쪽에, 나머지 5개를 오른쪽에 배치)
    @ApiOperation(
            value = "종합 점수 반환 요청",
            notes = "userId를 해당 유저 종합 점수를 json 형태로 반환한다",
            response = TotalScoreDto.class,
            responseContainer = "List"
    )
    @GetMapping("/score")
    public ResponseEntity<List<TotalScoreDto>> totalScore(HttpServletRequest request) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(score.totalScore(userId), HttpStatus.valueOf(200));
    }


    // 연도별 데일리 종합 점수
    @ApiOperation(
            value = "연도별 데일리 종합 점수 데이터 요청",
            notes = "userId를 통해 연도별 데일리 종합 점수를 json 형태로 반환한다",
            response = DailyTotalScoreDto.class,
            responseContainer = "List"
    )
    @GetMapping("/user/score/{year}")
    public ResponseEntity<List<TotalScoreYearDto>> totalDailyScore(HttpServletRequest request, @PathVariable int year) {
        String accessToken = HeaderUtil.getAccessToken(request);
        String userId = userUtil.getUserId(accessToken);
        return new ResponseEntity<>(score.yearTotalScore(userId,year),HttpStatus.valueOf(200));
    }
}
