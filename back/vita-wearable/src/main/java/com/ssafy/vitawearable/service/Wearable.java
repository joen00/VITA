package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.TotalScore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface Wearable {
    // 활동량 달별
    List<EnergyMonthlyDto> energyMonthly(String userId);

    // 활동량 주별
    List<EnergyWeeklyDto> energyWeekly(String userId);

    // 활동량 일별
    List<EnergyDailyDto> energyDaily(String userId);

    // 활동량 이전과 비교
//    List<String> energyPast(String userId);

    // 심박수 달별
    List<RhrMonthlyDto> rhrMonthly(String userId);

    // 심박수 주별
    List<RhrWeeklyDto> rhrWeekly(String userId);

    // 심박수 일별
    List<RhrDailyDto> rhrDaily(String userId);

    // 수면 달별
    List<SleepMonthlyDto> sleepMonthly(String userId);

    // 수면 주별
    List<SleepWeeklyDto> sleepWeekly(String userId);

    // 수면 일별
    List<SleepDailyDto> sleepDaily(String userId);

    // 스트레스 달별
    List<StressMonthlyDto> stressMonthly(String userId);

    // 스트레스 주별
    List<StressWeeklyDto> stressWeekly(String userId);

    // 스트레스 일별
    List<StressDailyDto> stressDaily(String userId);

    // 무게 달별
    List<WeightMonthlyDto> weightMonthly(String userId);

    // 무게 주별
    List<WeightWeeklyDto> weightWeekly(String userId);

    // 무게 일별
    List<WeightDailyDto> weightDaily(String userId);

    // 총합 점수 평균 반환


    // 연도별 데일리 종합 점수 반환


    // jwt 토큰에서 userId 추출
    String getUserId(String token);

    UserAverageDto userAverage(String userId);

    ApiAverageDto apiTotalAverage();

    ApiAverageDto apiCustomAverage(int userAge, String userSex);

    List<TotalScoreDto> totalScore(String userId);

    List<DailyTotalScore> yearTotalScore(String userId, int year);

//    List<FriendDto> friendList(String userId);
//
//    List<UserAverageDto> friendAverage(String userId);


}
