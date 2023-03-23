package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;

// 이전 데이터랑 비교관련 서비스
public interface WearablePast {
    // 걸음수 이전과 비교
    StepPastAndNowDto stepPastAndNow(String userId);

    // 활동량 이전과 비교
    EnergyPastAndNowDto energyPastAndNow(String userId);

    // 심박수 이전과 비교
    RhrPastAndNowDto rhrPastAndNow(String userId);

    // 수면 이전과 비교
    SleepPastAndNowDto sleepPastAndNow(String userId);

    // 스트레스 이전과 비교
    StressPastAndNowDto stressPastAndNow(String userId);

    // 체중 이전과 비교
    WeightPastAndNowDto weightPastAndNow(String userId);
}
