package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;

import java.util.List;

public interface Wearable {
    // 걸음수 달별
    List<StepMonthlyDto> stepMonthly(String userId);

    // 걸음수 주별
    List<StepWeeklyDto> stepWeekly(String userId);

    // 걸음수 일별
    List<StepDailyDto> stepDaily(String userId);

    // 활동량 달별
    List<EnergyMonthlyDto> energyMonthly(String userId);

    // 활동량 주별
    List<EnergyWeeklyDto> energyWeekly(String userId);

    // 활동량 일별
    List<EnergyDailyDto> energyDaily(String userId);

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

    // 활동량 이전과 비교
//    WeightPastNowDto weightPastNow(String userId);


    // jwt 토큰에서 userId 추출
    String getUserId(String token);


}
