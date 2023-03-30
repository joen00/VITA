package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.*;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import com.ssafy.vitawearable.entity.UserHistory;
import com.ssafy.vitawearable.entity.WeeklyWearable;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import java.sql.Time;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
// 이전 데이터랑 비교관련 서비스
public class WearablePastImpl implements WearablePast {
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;
    private final UserHistoryRepo userHistoryRepo;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    // 걸음수 이전과 비교
    public StepPastAndNowDto stepPastAndNow(String userId) {

        StepPastAndNowDto stepPastAndNowDto = new StepPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        stepPastAndNowDto.setWeekNowWearableStep(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableStep());

        // 이전 주 값 넣어주기
        stepPastAndNowDto.setWeekPastWearableStep(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableStep());

        // 현재 달 값 넣어주기
        stepPastAndNowDto.setMonthNowWearableStep(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableStep());

        // 이전 달 값 넣어주기
        stepPastAndNowDto.setMonthPastWearableStep(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableStep());

        // 이번 년도 값 평균 구해서 넣어주기
        stepPastAndNowDto.setYearNowWearableStep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableStep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        stepPastAndNowDto.setYearPastWearableStep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableStep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return stepPastAndNowDto;
    }

    @Override
    // 활동에너지 이전과 비교
    public EnergyPastAndNowDto energyPastAndNow(String userId) {

        EnergyPastAndNowDto energyPastAndNowDto = new EnergyPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        energyPastAndNowDto.setWeekNowWearableEnergy(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableEnergy());

        // 이전 주 값 넣어주기
        energyPastAndNowDto.setWeekPastWearableEnergy(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableEnergy());

        // 현재 달 값 넣어주기
        energyPastAndNowDto.setMonthNowWearableEnergy(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableEnergy());

        // 이전 달 값 넣어주기
        energyPastAndNowDto.setMonthPastWearableEnergy(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableEnergy());

        // 이번 년도 값 평균 구해서 넣어주기
        energyPastAndNowDto.setYearNowWearableEnergy(
                (long)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableEnergy)
                        .mapToLong(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        energyPastAndNowDto.setYearPastWearableEnergy(
                (long)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableEnergy)
                        .mapToLong(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return energyPastAndNowDto;
    }

    @Override
    // 심박수 이전과 비교
    public RhrPastAndNowDto rhrPastAndNow(String userId) {

        RhrPastAndNowDto rhrPastAndNowDto = new RhrPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        rhrPastAndNowDto.setWeekNowWearableRhr(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableRhr());

        // 이전 주 값 넣어주기
        rhrPastAndNowDto.setWeekPastWearableRhr(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableRhr());

        // 현재 달 값 넣어주기
        rhrPastAndNowDto.setMonthNowWearableRhr(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableRhr());

        // 이전 달 값 넣어주기
        rhrPastAndNowDto.setMonthPastWearableRhr(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableRhr());

        // 이번 년도 값 평균 구해서 넣어주기
        rhrPastAndNowDto.setYearNowWearableRhr(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableRhr)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        rhrPastAndNowDto.setYearPastWearableRhr(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableRhr)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return rhrPastAndNowDto;
    }

    @Override
    // 수면 이전과 비교
    public SleepPastAndNowDto sleepPastAndNow(String userId) {

        SleepPastAndNowDto sleepPastAndNowDto = new SleepPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        sleepPastAndNowDto.setWeekNowWearableSleep(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableSleep());

        // 이전 주 값 넣어주기
        sleepPastAndNowDto.setWeekPastWearableSleep(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableSleep());

        // 현재 달 값 넣어주기
        sleepPastAndNowDto.setMonthNowWearableSleep(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableSleep());

        // 이전 달 값 넣어주기
        sleepPastAndNowDto.setMonthPastWearableSleep(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableSleep());

        // 이번 년도 값 평균 구해서 넣어주기
        sleepPastAndNowDto.setYearNowWearableSleep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableSleep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

//        List<LocalTime> yearNow = monthlyWearableList.stream()
//                .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
//                .map(MonthlyWearable::getMonthlyWearableSleep)
//                .collect(Collectors.toList());
//        LocalTime total = LocalTime.of(0,0,0);
//        for (LocalTime t:yearNow) {
//            total = total.plusHours(t.getHour())
//                            .plusMinutes(t.getMinute());
//        }
//        sleepPastAndNowDto.setYearNowWearableSleep(total);

        // 저번 년도 값 평균 구해서 넣어주기
        sleepPastAndNowDto.setYearPastWearableSleep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableSleep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

//        List<LocalTime> yearPast = monthlyWearableList.stream()
//                .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
//                .map(MonthlyWearable::getMonthlyWearableSleep)
//                .collect(Collectors.toList());
//        total = LocalTime.of(0,0,0);
//        for (LocalTime t:yearPast) {
//            total = total.plusHours(t.getHour())
//                    .plusMinutes(t.getMinute());
//        }
//        sleepPastAndNowDto.setYearPastWearableSleep(total);

        return sleepPastAndNowDto;
    }

    @Override
    // 스트레스 이전과 비교
    public StressPastAndNowDto stressPastAndNow(String userId) {

        StressPastAndNowDto stressPastAndNowDto = new StressPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);


        // 현재 주 값 넣어주기
        Optional<WeeklyWearable> nowWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(lastExportWeek)).findAny();
        if (nowWeekWearable.isPresent()) {
            stressPastAndNowDto.setWeekNowWearableStress(0);
        } else {
            stressPastAndNowDto.setWeekNowWearableStress(nowWeekWearable.get().getWeeklyWearableStress());
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            stressPastAndNowDto.setWeekPastWearableStress(0);
        } else {
            stressPastAndNowDto.setWeekPastWearableStress(pastWeekWearable.get().getWeeklyWearableStress());
        }

//        // 현재 주 값 넣어주기
//        Optional<WeeklyWearable> weeklyWearable = weeklyWearableList.stream()
//                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
//                                .equals(lastExportWeek)).findAny();
//        stressPastAndNowDto.setWeekNowWearableStress(
//                weeklyWearableList.stream()
//                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
//                                .equals(lastExportWeek))
//                        .findFirst()
//                        .get()
//                        .getWeeklyWearableStress());
//
//        // 이전 주 값 넣어주기
//        stressPastAndNowDto.setWeekPastWearableStress(
//                weeklyWearableList.stream()
//                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
//                                .equals(pastExportWeek))
//                        .findFirst()
//                        .get()
//                        .getWeeklyWearableStress());

        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonth() == LastExportTime.getMonth()).findAny();
        if (nowMonthWearable.isPresent()) {
            stressPastAndNowDto.setWeekNowWearableStress(0);
        } else {
            stressPastAndNowDto.setWeekNowWearableStress(nowMonthWearable.get().getMonthlyWearableStress());
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth()).findAny();
        if (pastMonthWearable.isPresent()) {
            stressPastAndNowDto.setWeekPastWearableStress(0);
        } else {
            stressPastAndNowDto.setWeekPastWearableStress(pastMonthWearable.get().getMonthlyWearableStress());
        }

//        // 현재 달 값 넣어주기
//        stressPastAndNowDto.setMonthNowWearableStress(
//                monthlyWearableList.stream()
//                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
//                                w.getDate().getMonth() == LastExportTime.getMonth())
//                        .findFirst().get().getMonthlyWearableStress());
//
//        // 이전 달 값 넣어주기
//        stressPastAndNowDto.setMonthPastWearableStress(
//                monthlyWearableList.stream()
//                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
//                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
//                        .findFirst().get().getMonthlyWearableStress());

        // 이번 년도 값 평균 구해서 넣어주기
        stressPastAndNowDto.setYearNowWearableStress(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableStress)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        stressPastAndNowDto.setYearPastWearableStress(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableStress)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return stressPastAndNowDto;
    }

    @Override
    // 체중 이전과 비교
    public WeightPastAndNowDto weightPastAndNow(String userId) {

        WeightPastAndNowDto weightPastAndNowDto = new WeightPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserHistory> userHistoryList = userHistoryRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userHistoryList.get(userHistoryList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        // 현재 주 값 넣어주기
        weightPastAndNowDto.setWeekNowWearableWeight(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(lastExportWeek))
                        .findFirst().get().getWeeklyWearableWeight());

        // 이전 주 값 넣어주기
        weightPastAndNowDto.setWeekPastWearableWeight(
                weeklyWearableList.stream()
                        .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                                .equals(pastExportWeek))
                        .findFirst().get().getWeeklyWearableWeight());

        // 현재 달 값 넣어주기
        weightPastAndNowDto.setMonthNowWearableWeight(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                                w.getDate().getMonth() == LastExportTime.getMonth())
                        .findFirst().get().getMonthlyWearableWeight());

        // 이전 달 값 넣어주기
        weightPastAndNowDto.setMonthPastWearableWeight(
                monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                                w.getDate().getMonth() == LastExportTime.minusMonths(1).getMonth())
                        .findFirst().get().getMonthlyWearableWeight());

        // 이번 년도 값 평균 구해서 넣어주기
        Double nowYearAvg = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                .map(MonthlyWearable::getMonthlyWearableWeight)
                .map(m-> m.doubleValue())
                .mapToDouble(num -> num)
                .summaryStatistics()
                .getAverage();
        weightPastAndNowDto.setYearNowWearableWeight(nowYearAvg.floatValue());

        // 저번 년도 값 평균 구해서 넣어주기

        Double pastYearAvg = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                .map(MonthlyWearable::getMonthlyWearableWeight)
                .map(m-> m.doubleValue())
                .mapToDouble(num -> num)
                .summaryStatistics()
                .getAverage();
        weightPastAndNowDto.setYearPastWearableWeight(pastYearAvg.floatValue());

        // 저번 년도 값 평균 구해서 넣어주기
//        weightPastAndNowDto.setYearPastWearableWeight(
//                (long)monthlyWearableList.stream()
//                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
//                        .map(MonthlyWearable::getMonthlyWearableWeight)
//                        .mapToLong(num -> num)
//                        .summaryStatistics()
//                        .getAverage());

        return weightPastAndNowDto;
    }

}
