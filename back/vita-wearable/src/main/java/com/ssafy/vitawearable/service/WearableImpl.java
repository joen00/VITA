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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WearableImpl implements Wearable {
    private final DailyWearableRepo dailyWearableRepo;
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;
    private final DailySleepRepo dailySleepRepo;
    private final ModelMapper mapper = new ModelMapper();

    // 걸음수 달별
    @Override
    public List<StepMonthlyDto> stepMonthly(String userId) {
        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, StepMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(StepMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 걸음수 주별
    @Override
    public List<StepWeeklyDto> stepWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StepWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(StepWeeklyDto::getDate))
                .collect(Collectors.toList());
    }

    // 걸음수 일별
    @Override
    public List<StepDailyDto> stepDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, StepDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(StepDailyDto::getDate))
                .collect(Collectors.toList());
    }



    // 활동량 달별
    @Override
    public List<EnergyMonthlyDto> energyMonthly(String userId) {
        List<MonthlyWearable> monthlyEnergy = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyEnergy.stream()
                .map(monthly -> mapper.map(monthly, EnergyMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(EnergyMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 활동량 주별
    @Override
    public List<EnergyWeeklyDto> energyWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, EnergyWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(EnergyWeeklyDto::getDate))
                .collect(Collectors.toList());
    }

    // 활동량 일별
    @Override
    public List<EnergyDailyDto> energyDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, EnergyDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(EnergyDailyDto::getDate))
                .collect(Collectors.toList());
    }

    // 심박수 달별
    @Override
    public List<RhrMonthlyDto> rhrMonthly(String userId) {
        List<MonthlyWearable> monthlyRhr = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyRhr.stream()
                .map(monthly -> mapper.map(monthly, RhrMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(RhrMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 심박수 주별
    @Override
    public List<RhrWeeklyDto> rhrWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, RhrWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(RhrWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 심박수 일별
    @Override
    public List<RhrDailyDto> rhrDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, RhrDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(RhrDailyDto::getDate))
                .collect(Collectors.toList());

    }

    // 수면 달별
    @Override
    public List<SleepMonthlyDto> sleepMonthly(String userId) {
        List<MonthlyWearable> monthlySleep = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlySleep.stream()
                .map(monthly -> mapper.map(monthly, SleepMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(SleepMonthlyDto::getDate))
                .collect(Collectors.toList());

    }

    // 수면 주별
    @Override
    public List<SleepWeeklyDto> sleepWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, SleepWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(SleepWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 수면 일별
    @Override
    public List<SleepDailyDto> sleepDaily(String userId) {
        List<DailySleep> dailyWearables = dailySleepRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, SleepDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(SleepDailyDto::getDailySleepStart))
                .collect(Collectors.toList());
    }

    // 스트레스 달별
    @Override
    public List<StressMonthlyDto> stressMonthly(String userId) {
        List<MonthlyWearable> monthlyStress = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyStress.stream()
                .map(monthly -> mapper.map(monthly, StressMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(StressMonthlyDto::getDate))
                .collect(Collectors.toList());

    }

    // 스트레스 주별
    @Override
    public List<StressWeeklyDto> stressWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, StressWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(StressWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 스트레스 일별
    @Override
    public List<StressDailyDto> stressDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, StressDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(StressDailyDto::getDate))
                .collect(Collectors.toList());

    }

    // 무게 달별
    @Override
    public List<WeightMonthlyDto> weightMonthly(String userId) {
        List<MonthlyWearable> monthlyWeight = monthlyWearableRepo.findByUser_UserId(userId);
        return monthlyWeight.stream()
                .map(monthly -> mapper.map(monthly, WeightMonthlyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(WeightMonthlyDto::getDate))
                .collect(Collectors.toList());

    }

    // 무게 주별
    @Override
    public List<WeightWeeklyDto> weightWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        return weeklyWearable.stream()
                .map(weekly -> mapper.map(weekly, WeightWeeklyDto.class))
                .limit(12)
                .sorted(Comparator.comparing(WeightWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 무게 일별
    @Override
    public List<WeightDailyDto> weightDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        return dailyWearables.stream()
                .map(daily -> mapper.map(daily, WeightDailyDto.class))
                .limit(20)
                .sorted(Comparator.comparing(WeightDailyDto::getDate))
                .collect(Collectors.toList());

    }

    // token에서 userId 추출. claims에서 subject안에 있는게 맞나?
    @Override
    public String getUserId(String token) {
        // user 서버의 secretKey
        String secretKey = "8sknjlO3NPTBqo319DHLNqsQAfRJEdKsETOds";
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
