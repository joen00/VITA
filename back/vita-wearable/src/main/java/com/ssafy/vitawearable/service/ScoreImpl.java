package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.*;
import com.ssafy.vitawearable.repo.*;
import io.swagger.models.auth.In;
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
    private final WearableFriend wearableFriend;
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
                .sorted(Comparator.comparing(TotalScore::getCreatedDate))
                .skip(skipCount)
                .map(totalScore -> mapper.map(totalScore, TotalScoreDto.class))
                .collect(Collectors.toList());
        // totalScore 구하기
        for (TotalScoreDto t:totalScoreDtoList) {
            int totalScore = t.getTotalScoreEnergy() +
                    t.getTotalScoreSleep() + t.getTotalScoreRhr() +
                    t.getTotalScoreStep() + t.getTotalScoreStress();
            t.setTotalScore(totalScore/5);
        }
        return totalScoreDtoList;
    }

    // 연도별 데일리 종합 점수 반환
    public List<TotalScoreYearDto> yearTotalScore(String userId, int year) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TotalScoreYearDto> totalScoreYearDtoList = new ArrayList<TotalScoreYearDto>();
        for (int i=1; i < 13; i++) {

            totalScoreYearDtoList.add(new TotalScoreYearDto(i)); }
        List<DailyTotalScoreDto> dailyWearablesDtoList = dailyWearableRepo.findByUser_UserId(userId).stream()
                .filter(daily -> daily.getDate().getYear() == year)
                .sorted(Comparator.comparing(DailyWearable::getDate))
                .map(daily -> mapper.map(daily, DailyTotalScoreDto.class))
                .collect(Collectors.toList());
        for (DailyTotalScoreDto data:dailyWearablesDtoList) {
            // 월, 일 추출(인덱스용)
            int month = data.getDate().getMonthValue();
            int day = data.getDate().getDayOfMonth();
            // 값 넣어주기
            totalScoreYearDtoList.get(month-1).getData().get(day-1).setY(data.getDailyWearableScore());
        }

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
        return apiAverageDto;
    }

    // 연별 유저 평균
    @Override
    public ApiAverageDto apiCustomAverage(String userId) {
        ApiAverageDto apiAverageDto = new ApiAverageDto();
        User user = userRepo.findUserByUserId(userId);
        String userSex = user.getUserGender();
        int userAge = (user.getUserAge() / 10 + 1) * 10 ;
        List<ApiAverage> apiAverageList = apiAverageRepo.findAll();
        ApiAverage apiAverage = apiAverageList.stream()
                .filter(i -> i.getSex().equals(userSex) && i.getAge() == userAge).findAny().get();

        apiAverageDto.setApiAverageStep((int)(apiAverage.getApiAverageStep() / apiAverage.getApiAverageStepCnt()));
        apiAverageDto.setApiAverageEnergy((int)
                (apiAverage.getApiAverageEnergy() / apiAverage.getApiAverageEnergyCnt()));
        apiAverageDto.setApiAverageRhr((int)
                (apiAverage.getApiAverageRhr() / apiAverage.getApiAverageRhrCnt()));
        apiAverageDto.setApiAverageStress((int)
                (apiAverage.getApiAverageStress() / apiAverage.getApiAverageStressCnt()));
        apiAverageDto.setApiAverageSleep((int)
                (apiAverage.getApiAverageSleep() / apiAverage.getApiAverageSleepCnt()));

        return apiAverageDto;
    }

    @Override
    public List<Integer> rank(String userId) {
        List<TotalScore> totalScoreList = totalScoreRepo.findByUser_UserId(userId);
        int friendRank = 1;
        int totalRank = 1;
        List<Integer> result = new ArrayList<>();
        // 유저 점수가 없으면 [0,0]으로 반환
        if (totalScoreList.isEmpty()) {
            result.add(0);
            result.add(0);
            return result;
        }
        // 유저 total score 추출
        TotalScore recentUserTotalScore = totalScoreList.get(totalScoreList.size()-1);
        int recentUserScore = (recentUserTotalScore.getTotalScoreEnergy() +
                recentUserTotalScore.getTotalScoreSleep() +
                recentUserTotalScore.getTotalScoreRhr() +
                recentUserTotalScore.getTotalScoreStress() +
                recentUserTotalScore.getTotalScoreStep()) / 5;

        // 친구 userId 추출
        List<FriendDto> friendDtoList = wearableFriend.getFriendList(userId);
        List<String> friendUserIdList = friendDtoList.stream()
                .map(FriendDto::getUserId)
                .collect(Collectors.toList());

        // 친구 점수와 비교해서 등수 반환
        for (String friendId:friendUserIdList) {
            List<TotalScore> friendTotalScoreList = totalScoreRepo.findByUser_UserId(friendId);
            // 친구 점수가 없다면 다음 진행
            if (friendTotalScoreList.isEmpty()) continue;
            // 친구 total score 추출
            TotalScore recentFriendTotalScore = friendTotalScoreList.get(friendTotalScoreList.size()-1);
            int recentFriendScore = (recentFriendTotalScore.getTotalScoreEnergy() +
                    recentFriendTotalScore.getTotalScoreSleep() +
                    recentFriendTotalScore.getTotalScoreRhr() +
                    recentFriendTotalScore.getTotalScoreStress() +
                    recentFriendTotalScore.getTotalScoreStep()) / 5;
            if (recentFriendScore > recentUserScore) {
                friendRank += 1;
            }
        }

        // 전체 유저 점수와 비교해서 등수 반환
        List<User> allUser = userRepo.findAll();
        List<String> otherUserIdList = allUser.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
        otherUserIdList.remove(userId);
        for (String otherUserId:otherUserIdList) {
            List<TotalScore> otherTotalScoreList = totalScoreRepo.findByUser_UserId(otherUserId);
            // 다른 유저 점수가 없다면 다음 진행
            if (otherTotalScoreList.isEmpty()) continue;
            // 다른 유저 total score 추출
            TotalScore recentOtherTotalScore = otherTotalScoreList.get(otherTotalScoreList.size()-1);
            int recentOtherScore = (recentOtherTotalScore.getTotalScoreEnergy() +
                    recentOtherTotalScore.getTotalScoreSleep() +
                    recentOtherTotalScore.getTotalScoreRhr() +
                    recentOtherTotalScore.getTotalScoreStress() +
                    recentOtherTotalScore.getTotalScoreStep()) / 5;
            if (recentOtherScore > recentUserScore) {
                totalRank += 1;
            }
        }
        result.add(totalRank);
        result.add(friendRank);
        return result;
    }
}
