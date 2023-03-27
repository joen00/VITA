package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;

import java.util.List;

// 평균 및 총합점수 관련 서비스
public interface Score {
    // 총합 점수 평균 반환
    List<TotalScoreDto> totalScore(String userId);

    // 연도별 데일리 종합 점수 반환
    List<TotalScoreYearDto> yearTotalScore(String userId, int year);

    // 유저 평균값 반환
    UserAverageDto userAverage(String userId);

    // 친구들 평균값들 반환
    List<UserAverageDto> friendAverage(List<String> friendIdList);

    // DB에 기록된 api 평균 데이터를 가지고 전체 api 평균 구하기
    ApiAverageDto apiTotalAverage();

    // 요청받은 나이, 성별에 맞는 api 평균 데이터 반환
    ApiAverageDto apiCustomAverage(int userAge, String userSex);

}
