package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.ApiAverageDto;
import com.ssafy.vitawearable.dto.DailyTotalScore;
import com.ssafy.vitawearable.dto.TotalScoreDto;
import com.ssafy.vitawearable.dto.UserAverageDto;
import com.ssafy.vitawearable.entity.ApiAverage;
import com.ssafy.vitawearable.entity.DailyWearable;
import com.ssafy.vitawearable.entity.TotalScore;
import com.ssafy.vitawearable.entity.UserAverage;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
// 평균 및 총합점수 관련 서비스
public class ScoreImpl implements Score{
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final TotalScoreRepo totalScoreRepo;
    private final ModelMapper mapper = new ModelMapper();


    // 모든 총합 점수 반환
    public List<TotalScoreDto> totalScore(String userId) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TotalScore> totalScoreList = totalScoreRepo.findByUser_UserId(userId);
        List<TotalScoreDto> totalScoreDtoList = totalScoreList.stream()
                .map(totalScore -> mapper.map(totalScore, TotalScoreDto.class))
                .collect(Collectors.toList());
        // totalScore 구하기
        for (TotalScoreDto t:totalScoreDtoList) {
            int totalScore = t.getTotalScoreEnergy() + t.getTotalScoreWeight() +
                    t.getTotalScoreSleep() + t.getTotalScoreRhr() +
                    t.getTotalScoreStep() + t.getTotalScoreStress();
            t.setTotalScore(totalScore/6);
        }
        return totalScoreDtoList;
    }

    // 연도별 데일리 종합 점수 반환
    public List<DailyTotalScore> yearTotalScore(String userId, int year) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .filter(daily -> daily.getDate().getYear() == year)
                .map(daily -> mapper.map(daily, DailyTotalScore.class))
                .collect(Collectors.toList());
    }


    // 해당 유저 평균값 구하기
    @Override
    public UserAverageDto userAverage(String userId) {
        UserAverage userAverage = userAverageRepo.findByUser_UserId(userId).get(0);
        return mapper.map(userAverage, UserAverageDto.class);
    }

    @Override
    public List<UserAverageDto> friendAverage(List<String> friendIdList) {
        return friendIdList.stream()
                .map(userId -> userAverageRepo.findByUser_UserId(userId).get(0))
                .map(m -> mapper.map(m,UserAverageDto.class))
                .collect(Collectors.toList());

    }

    // api 종합점수
    @Override
    public ApiAverageDto apiTotalAverage() {
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverageDto apiAverageDto = new ApiAverageDto();
        apiAverageDto.setApiAverageStep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageStep).average().getAsDouble());
        apiAverageDto.setApiAverageEnergy((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageEnergy).average().getAsDouble());
        apiAverageDto.setApiAverageRhr((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
        apiAverageDto.setApiAverageStress((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
        apiAverageDto.setApiAverageSleep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageSleep).average().getAsDouble());
        apiAverageDto.setApiAverageLight((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageLight).average().getAsDouble());
        apiAverageDto.setApiAverageRem((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRem).average().getAsDouble());
        apiAverageDto.setApiAverageCore((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageCore).average().getAsDouble());
        apiAverageDto.setApiAverageDeep((int)apiAverageList.stream().mapToInt(ApiAverage::getApiAverageDeep).average().getAsDouble());
        return apiAverageDto;
    }

    // 연별 유저 평균
    @Override
    public ApiAverageDto apiCustomAverage(int userAge, String userSex) {
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverage apiAverage = apiAverageList.stream()
                .filter(i -> i.getSex().equals(userSex) && i.getAge() == userAge).collect(Collectors.toList()).get(0);
        return mapper.map(apiAverage, ApiAverageDto.class);
    }



}