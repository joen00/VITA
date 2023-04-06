package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.Energy.EnergyPastAndNowDto;
import com.ssafy.vitawearable.dto.Rhr.RhrPastAndNowDto;
import com.ssafy.vitawearable.dto.Sleep.SleepPastAndNowDto;
import com.ssafy.vitawearable.dto.Step.StepPastAndNowDto;
import com.ssafy.vitawearable.dto.Stress.StressPastAndNowDto;
import com.ssafy.vitawearable.dto.Weight.WeightPastAndNowDto;
import com.ssafy.vitawearable.entity.MonthlyWearable;
import com.ssafy.vitawearable.entity.UserUpload;
import com.ssafy.vitawearable.entity.WeeklyWearable;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
// 이전 데이터랑 비교관련 서비스
public class WearablePastImpl implements WearablePast {
    private final MonthlyWearableRepo monthlyWearableRepo;
    private final WeeklyWearableRepo weeklyWearableRepo;
    private final UserUploadRepo userUploadRepo;

    @Override
    // 걸음수 이전과 비교
    public StepPastAndNowDto stepPastAndNow(String userId) {

        StepPastAndNowDto stepPastAndNowDto = new StepPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
        String lastExportWeek = LastExportTime.format(DateTimeFormatter.ofPattern("yyyy w"));
        // 생성 주보다 이전 주 'yyyy w' 포멧
        String pastExportWeek = LastExportTime.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy w"));

        List<WeeklyWearable> weeklyWearableList = weeklyWearableRepo.findByUser_UserId(userId);
        List<MonthlyWearable> monthlyWearableList = monthlyWearableRepo.findByUser_UserId(userId);

        Optional<WeeklyWearable> nowWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(lastExportWeek)).findAny();
        if (nowWeekWearable.isPresent()) {
            stepPastAndNowDto.setWeekNowWearableStep(nowWeekWearable.get().getWeeklyWearableStep());
        } else {
            stepPastAndNowDto.setWeekNowWearableStep(0);
        }

        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            stepPastAndNowDto.setWeekPastWearableStep(pastWeekWearable.get().getWeeklyWearableStep());
        } else {
            stepPastAndNowDto.setWeekPastWearableStep(0);
        }

        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue())
                .findAny();
        if (nowMonthWearable.isPresent()) {
            stepPastAndNowDto.setMonthNowWearableStep(nowMonthWearable.get().getMonthlyWearableStep());
        } else {
            stepPastAndNowDto.setMonthNowWearableStep(0);
        }

        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            stepPastAndNowDto.setMonthPastWearableStep(pastMonthWearable.get().getMonthlyWearableStep());
        } else {
            stepPastAndNowDto.setMonthPastWearableStep(0);
        }

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
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
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
            energyPastAndNowDto.setWeekNowWearableEnergy(nowWeekWearable.get().getWeeklyWearableEnergy());
        } else {
            energyPastAndNowDto.setWeekNowWearableEnergy(0L);
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            energyPastAndNowDto.setWeekPastWearableEnergy(pastWeekWearable.get().getWeeklyWearableEnergy());
        } else {
            energyPastAndNowDto.setWeekPastWearableEnergy(0L);
        }



        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue()).findAny();
        if (nowMonthWearable.isPresent()) {
            energyPastAndNowDto.setMonthNowWearableEnergy(nowMonthWearable.get().getMonthlyWearableEnergy());
        } else {
            energyPastAndNowDto.setMonthNowWearableEnergy(0L);
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            energyPastAndNowDto.setMonthPastWearableEnergy(pastMonthWearable.get().getMonthlyWearableEnergy());
        } else {
            energyPastAndNowDto.setMonthPastWearableEnergy(0L);
        }

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
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
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
            rhrPastAndNowDto.setWeekNowWearableRhr(nowWeekWearable.get().getWeeklyWearableRhr());
        } else {
            rhrPastAndNowDto.setWeekNowWearableRhr(0);
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            rhrPastAndNowDto.setWeekPastWearableRhr(pastWeekWearable.get().getWeeklyWearableRhr());
        } else {
            rhrPastAndNowDto.setWeekPastWearableRhr(0);
        }



        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue()).findAny();
        if (nowMonthWearable.isPresent()) {
            rhrPastAndNowDto.setMonthNowWearableRhr(nowMonthWearable.get().getMonthlyWearableRhr());
        } else {
            rhrPastAndNowDto.setMonthNowWearableRhr(0);
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            rhrPastAndNowDto.setMonthPastWearableRhr(pastMonthWearable.get().getMonthlyWearableRhr());
        } else {
            rhrPastAndNowDto.setMonthPastWearableRhr(0);
        }

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
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
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
            sleepPastAndNowDto.setWeekNowWearableSleep(nowWeekWearable.get().getWeeklyWearableSleep());
        } else {
            sleepPastAndNowDto.setWeekNowWearableSleep(0);
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            sleepPastAndNowDto.setWeekPastWearableSleep(pastWeekWearable.get().getWeeklyWearableSleep());
        } else {
            sleepPastAndNowDto.setWeekPastWearableSleep(0);
        }



        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue()).findAny();
        if (nowMonthWearable.isPresent()) {
            sleepPastAndNowDto.setMonthNowWearableSleep(nowMonthWearable.get().getMonthlyWearableSleep());
        } else {
            sleepPastAndNowDto.setMonthNowWearableSleep(0);
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            sleepPastAndNowDto.setMonthPastWearableSleep(pastMonthWearable.get().getMonthlyWearableSleep());
        } else {
            sleepPastAndNowDto.setMonthPastWearableSleep(0);
        }

        // 이번 년도 값 평균 구해서 넣어주기
        sleepPastAndNowDto.setYearNowWearableSleep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                        .map(MonthlyWearable::getMonthlyWearableSleep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        // 저번 년도 값 평균 구해서 넣어주기
        sleepPastAndNowDto.setYearPastWearableSleep(
                (int)monthlyWearableList.stream()
                        .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                        .map(MonthlyWearable::getMonthlyWearableSleep)
                        .mapToInt(num -> num)
                        .summaryStatistics()
                        .getAverage());

        return sleepPastAndNowDto;
    }

    @Override
    // 스트레스 이전과 비교
    public StressPastAndNowDto stressPastAndNow(String userId) {

        StressPastAndNowDto stressPastAndNowDto = new StressPastAndNowDto();
        // 해당 유저의 userHistory 가져오기
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
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
            stressPastAndNowDto.setWeekNowWearableStress(nowWeekWearable.get().getWeeklyWearableStress());
        } else {
            stressPastAndNowDto.setWeekNowWearableStress(0);
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            stressPastAndNowDto.setWeekPastWearableStress(pastWeekWearable.get().getWeeklyWearableStress());
        } else {
            stressPastAndNowDto.setWeekPastWearableStress(0);
        }



        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue()).findAny();
        if (nowMonthWearable.isPresent()) {
            stressPastAndNowDto.setMonthNowWearableStress(nowMonthWearable.get().getMonthlyWearableStress());
        } else {
            stressPastAndNowDto.setMonthNowWearableStress(0);
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            stressPastAndNowDto.setMonthPastWearableStress(pastMonthWearable.get().getMonthlyWearableStress());
        } else {
            stressPastAndNowDto.setMonthPastWearableStress(0);
        }

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
        List<UserUpload> userUploadList = userUploadRepo.findByUser_UserId(userId);
        // userHistory중 마지막을 가져오고, 거기서 생성일(export날짜)를 가지고 연,주 데이터를 'yyyy w'포멧으로 가져오기
        ZonedDateTime LastExportTime = userUploadList.get(userUploadList.size()-1).getCreatedDate();
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
            weightPastAndNowDto.setWeekNowWearableWeight(nowWeekWearable.get().getWeeklyWearableWeight());
        } else {
            weightPastAndNowDto.setWeekNowWearableWeight(0.0f);
        }

        // 이전 주 값 넣어주기
        Optional<WeeklyWearable> pastWeekWearable = weeklyWearableList.stream()
                .filter(w -> w.getDate().format(DateTimeFormatter.ofPattern("yyyy w"))
                        .equals(pastExportWeek)).findAny();
        if (pastWeekWearable.isPresent()) {
            weightPastAndNowDto.setWeekPastWearableWeight(pastWeekWearable.get().getWeeklyWearableWeight());
        } else {
            weightPastAndNowDto.setWeekPastWearableWeight(0.0f);
        }



        // 현재 달 값 넣어주기
        Optional<MonthlyWearable> nowMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.getMonthValue()).findAny();
        if (nowMonthWearable.isPresent()) {
            weightPastAndNowDto.setMonthNowWearableWeight(nowMonthWearable.get().getMonthlyWearableWeight());
        } else {
            weightPastAndNowDto.setMonthNowWearableWeight(0.0f);
        }

        // 이전 달 값 넣어주기
        Optional<MonthlyWearable> pastMonthWearable = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusMonths(1).getYear() &&
                        w.getDate().getMonthValue() == LastExportTime.minusMonths(1).getMonthValue()).findAny();
        if (pastMonthWearable.isPresent()) {
            weightPastAndNowDto.setMonthPastWearableWeight(pastMonthWearable.get().getMonthlyWearableWeight());
        } else {
            weightPastAndNowDto.setMonthPastWearableWeight(0.0f);
        }

        // 이번 년도 값 평균 구해서 넣어주기
        Double nowYearAvg = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.getYear())
                .map(MonthlyWearable::getMonthlyWearableWeight)
                .map(m-> m.doubleValue())
                .mapToDouble(num -> num)
                .summaryStatistics()
                .getAverage();

        weightPastAndNowDto.setYearNowWearableWeight((float)(Math.round(nowYearAvg*100)/100.0));

        // 저번 년도 값 평균 구해서 넣어주기

        Double pastYearAvg = monthlyWearableList.stream()
                .filter(w -> w.getDate().getYear() == LastExportTime.minusYears(1).getYear())
                .map(MonthlyWearable::getMonthlyWearableWeight)
                .map(m-> m.doubleValue())
                .mapToDouble(num -> num)
                .summaryStatistics()
                .getAverage();
        weightPastAndNowDto.setYearPastWearableWeight((float)(Math.round(pastYearAvg*100)/100.0));

        return weightPastAndNowDto;
    }

}
