package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.*;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
// 평균 및 총합점수 관련 서비스
public class ScoreImpl implements Score{
    private final UserRepo userRepo;
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final TotalScoreRepo totalScoreRepo;
    private final ModelMapper mapper = new ModelMapper();


    // 모든 총합 점수 반환
    public List<TotalScoreDto> totalScore(String userId) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TotalScore> totalScoreList = totalScoreRepo.findByUser_UserId(userId);
        int skipCount = 0;
        if (totalScoreList.size() > 5) {
            skipCount = totalScoreList.size() - 5;
        }
        List<TotalScoreDto> totalScoreDtoList = totalScoreList.stream()
                .sorted(Comparator.comparing(TotalScore::getDate))
                .skip(skipCount)
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
    public List<TotalScoreYearDto> yearTotalScore(String userId, int year) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TotalScoreYearDto> totalScoreYearDtoList = new ArrayList<TotalScoreYearDto>();
        for (int i=1; i < 13; i++) { totalScoreYearDtoList.add(new TotalScoreYearDto(i)); }
        List<DailyTotalScoreDto> dailyWearablesDtoList = dailyWearableRepo.findByUser_UserId(userId).stream()
                .filter(daily -> daily.getDate().getYear() == year)
                .sorted(Comparator.comparing(DailyWearable::getDate))
                .map(daily -> mapper.map(daily, DailyTotalScoreDto.class))
                .collect(Collectors.toList());
        for (DailyTotalScoreDto data:dailyWearablesDtoList) {
            int month = data.getDate().getMonthValue();
            Map<String,Integer> xy = new HashMap<>();
//            totalScoreYearDtoList.get(month-1).getData().;
            xy.put("x",data.getDate().getDayOfMonth());
            xy.put("y",data.getDailyWearableScore());
            totalScoreYearDtoList.get(month-1).getData().add(xy);
        }
        // 비어있는 날짜 넣어주기
//        for (TotalScoreYearDto data:totalScoreYearDtoList) {
//            data.getData().
//        }

        return totalScoreYearDtoList;
    }


    // 해당 유저 평균값 구하기
    @Override
    public UserAverageDto userAverage(String userId) {
        List<UserAverage> userAverageList = userAverageRepo.findByUser_UserId(userId);
        UserAverageDto userAverageDto = new UserAverageDto();
        if (userAverageList.isEmpty()) {
            User user = userRepo.findUserByUserId(userId);
            userAverageDto.setUserImg(user.getUserImg());
            userAverageDto.setUserNickname(user.getUserNickname());
        } else {
            UserAverage userAverage = userAverageList.get(userAverageRepo.findByUser_UserId(userId).size()-1);
            userAverageDto = mapper.map(userAverage, UserAverageDto.class);
            userAverageDto.setUserNickname(userAverage.getUser().getUserNickname());
            userAverageDto.setUserImg(userAverage.getUser().getUserImg());
        }
//        UserAverage userAverage = userAverageRepo.findByUser_UserId(userId).get(userAverageRepo.findByUser_UserId(userId).size()-1);
//        UserAverageDto userAverageDto = mapper.map(userAverage, UserAverageDto.class);
//        userAverageDto.setUserNickname(userAverage.getUser().getUserNickname());
//        userAverageDto.setUserImg(userAverage.getUser().getUserImg());
        return userAverageDto;
    }

    @Override
    public List<UserAverageDto> friendAverage(List<String> friendIdList) {
        List<UserAverageDto> userAverageDtoList = new ArrayList<>();
        for (String userId:friendIdList) {
            List<UserAverage> userAverageList = userAverageRepo.findByUser_UserId(userId);
            UserAverageDto userAverageDto = new UserAverageDto();
            if (userAverageList.isEmpty()) {
                User user = userRepo.findUserByUserId(userId);
                userAverageDto.setUserImg(user.getUserImg());
                userAverageDto.setUserNickname(user.getUserNickname());
            } else {
                UserAverage userAverage = userAverageList.get(userAverageRepo.findByUser_UserId(userId).size()-1);
                userAverageDto = mapper.map(userAverage, UserAverageDto.class);
                userAverageDto.setUserNickname(userAverage.getUser().getUserNickname());
                userAverageDto.setUserImg(userAverage.getUser().getUserImg());
            }
            userAverageDtoList.add(userAverageDto);
        }
        return userAverageDtoList;

//        return friendIdList.stream()
//                .map(userId -> userAverageRepo.findByUser_UserId(userId).get(userAverageRepo.findByUser_UserId(userId).size()-1))
//                .map(m -> mapper.map(m,UserAverageDto.class))
//                .collect(Collectors.toList());
    }

    // api 종합점수
    @Override
    public ApiAverageDto apiTotalAverage() {
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverageDto apiAverageDto = new ApiAverageDto();
        apiAverageDto.setApiAverageStep((int)
                (apiAverageList.stream().mapToLong(ApiAverage::getApiAverageStep).sum() /
                apiAverageList.stream().mapToInt(ApiAverage::getApiAverageStepCnt).sum()));
        apiAverageDto.setApiAverageEnergy((int)
                (apiAverageList.stream().mapToLong(ApiAverage::getApiAverageEnergy).sum() /
                apiAverageList.stream().mapToInt(ApiAverage::getApiAverageEnergyCnt).sum()));
        apiAverageDto.setApiAverageRhr((int)
                (apiAverageList.stream().mapToLong(ApiAverage::getApiAverageRhr).sum() /
                apiAverageList.stream().mapToInt(ApiAverage::getApiAverageRhrCnt).sum()));
        apiAverageDto.setApiAverageStress((int)
                (apiAverageList.stream().mapToLong(ApiAverage::getApiAverageStress).sum() /
                apiAverageList.stream().mapToInt(ApiAverage::getApiAverageStressCnt).sum()));
        apiAverageDto.setApiAverageSleep((int)
                (apiAverageList.stream().mapToLong(ApiAverage::getApiAverageSleep).sum() /
                apiAverageList.stream().mapToInt(ApiAverage::getApiAverageSleepCnt).sum()));

//        apiAverageDto.setApiAverageEnergy((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageEnergy).average().getAsDouble());
//        apiAverageDto.setApiAverageRhr((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
//        apiAverageDto.setApiAverageStress((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageRhr).average().getAsDouble());
//        apiAverageDto.setApiAverageSleep((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageSleep).average().getAsDouble());

//        apiAverageDto.setApiAverageLight((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageLight).average().getAsDouble());
//        apiAverageDto.setApiAverageRem((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageRem).average().getAsDouble());
//        apiAverageDto.setApiAverageAwake((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageAwake).average().getAsDouble());
//        apiAverageDto.setApiAverageDeep((int)apiAverageList.stream().
//                mapToInt(ApiAverage::getApiAverageDeep).average().getAsDouble());
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
