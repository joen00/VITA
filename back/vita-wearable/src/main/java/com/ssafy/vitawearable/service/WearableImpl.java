package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.*;
import com.ssafy.vitawearable.repo.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WearableImpl implements Wearable {
    private final ApiAverageRepo apiAverageRepo;
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final UserAverageRepo userAverageRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;
    private final TotalScoreRepo totalScoreRepo;
//    private final FriendRepo friendRepo;
//    private final UsersRepo usersRepo;
    private final ModelMapper mapper = new ModelMapper();

    // 활동량 달별
    @Override
    public List<EnergyMonthlyDto> energyMonthly(String userId) {
        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUsers_UserId(userId);
        return monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, EnergyMonthlyDto.class)).collect(Collectors.toList());
    }

    // 활동량 주별
    @Override
    public List<EnergyWeeklyDto> energyWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, EnergyWeeklyDto.class)).collect(Collectors.toList());
    }

    // 활동량 일별
    @Override
    public List<EnergyDailyDto> energyDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, EnergyDailyDto.class)).collect(Collectors.toList());
    }

    // 심박수 달별
    @Override
    public List<RhrMonthlyDto> rhrMonthly(String userId) {
        List<MonthlyWearable> monthlyRhr = monthlyWearableRepo.findByUsers_UserId(userId);
        return monthlyRhr.stream()
                .map(monthly -> mapper.map(monthly, RhrMonthlyDto.class)).collect(Collectors.toList());
    }

    // 심박수 주별
    @Override
    public List<RhrWeeklyDto> rhrWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, RhrWeeklyDto.class)).collect(Collectors.toList());

    }

    // 심박수 일별
    @Override
    public List<RhrDailyDto> rhrDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, RhrDailyDto.class)).collect(Collectors.toList());

    }

    // 수면 달별
    @Override
    public List<SleepMonthlyDto> sleepMonthly(String userId) {
        List<MonthlyWearable> monthlySleep = monthlyWearableRepo.findByUsers_UserId(userId);
        return monthlySleep.stream()
                .map(monthly -> mapper.map(monthly, SleepMonthlyDto.class)).collect(Collectors.toList());

    }

    // 수면 주별
    @Override
    public List<SleepWeeklyDto> sleepWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, SleepWeeklyDto.class)).collect(Collectors.toList());

    }

    // 수면 일별
    @Override
    public List<SleepDailyDto> sleepDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, SleepDailyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 달별
    @Override
    public List<StressMonthlyDto> stressMonthly(String userId) {
        List<MonthlyWearable> monthlyStress = monthlyWearableRepo.findByUsers_UserId(userId);
        return monthlyStress.stream()
                .map(monthly -> mapper.map(monthly, StressMonthlyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 주별
    @Override
    public List<StressWeeklyDto> stressWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StressWeeklyDto.class)).collect(Collectors.toList());

    }

    // 스트레스 일별
    @Override
    public List<StressDailyDto> stressDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, StressDailyDto.class)).collect(Collectors.toList());

    }

    // 무게 달별
    @Override
    public List<WeightMonthlyDto> weightMonthly(String userId) {
        List<MonthlyWearable> monthlyWeight = monthlyWearableRepo.findByUsers_UserId(userId);
        return monthlyWeight.stream()
                .map(monthly -> mapper.map(monthly, WeightMonthlyDto.class)).collect(Collectors.toList());

    }

    // 무게 주별
    @Override
    public List<WeightWeeklyDto> weightWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUsers_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, WeightWeeklyDto.class)).collect(Collectors.toList());

    }

    // 무게 일별
    @Override
    public List<WeightDailyDto> weightDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, WeightDailyDto.class)).collect(Collectors.toList());

    }

//    public WeightPastNowDto weightPastNow(String userId) {
//        WeightPastNowDto weightPastNowDto = new WeightPastNowDto();
//        weightPastNowDto = weeklyWearableRepo.findByUsers_UserId(userId).get(0);
//
//    }

    // 모든 총합 점수 반환
    public List<TotalScoreDto> totalScore(String userId) {
        List<TotalScore> totalScoreList = totalScoreRepo.findByUsers_UserId(userId);
        return totalScoreList.stream()
                .map(totalScore -> mapper.map(totalScore, TotalScoreDto.class)).collect(Collectors.toList());
    }

    // 연도별 데일리 종합 점수 반환
    public List<DailyTotalScore> yearTotalScore(String userId, int year) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUsers_UserId(userId);
        return dailyWearables.stream()
                .filter(daily -> daily.getDate().getYear() == year)
                .map(daily -> mapper.map(daily, DailyTotalScore.class))
                .collect(Collectors.toList());
    }


    // 해당 유저 평균값 구하기
    @Override
    public UserAverageDto userAverage(String userId) {
        UserAverage userAverage = userAverageRepo.findByUsers_UserId(userId).get(0);
        return mapper.map(userAverage, UserAverageDto.class);
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



//    @Override
//    public List<FriendDto> friendList(String userId) {
//        usersRepo.findById(userId).
//    }
//
//
//    @Override
//    public List<UserAverageDto> friendAverage(String userId) {
//        user
//    }


    // token에서 userId 추출. claims에서 subject안에 있는게 맞나?
    @Override
    public String getUserId(String token) {
//        Claims claims = Jwts.parserBuilder()
////                .setSigningKey(DatatypeConverter.parseBase64Binary("bundo10@naver.com"))
//                .setSigningKey("vita500")
//                .build()
//                .parseClaimsJws(token)
//                .getBody();

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary("youcantrevealthesecretkey1234012300040hjbvjhbjhvhjbjkbhjvjbjkn"))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();

//        return "2703629614";
    }
}
