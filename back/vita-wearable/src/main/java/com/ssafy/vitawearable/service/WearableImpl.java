package com.ssafy.vitawearable.service;

import com.ssafy.vitawearable.dto.Energy.EnergyDailyDto;
import com.ssafy.vitawearable.dto.Energy.EnergyMonthlyDto;
import com.ssafy.vitawearable.dto.Energy.EnergyWeeklyDto;
import com.ssafy.vitawearable.dto.Rhr.RhrDailyDto;
import com.ssafy.vitawearable.dto.Rhr.RhrMonthlyDto;
import com.ssafy.vitawearable.dto.Rhr.RhrWeeklyDto;
import com.ssafy.vitawearable.dto.Sleep.SleepDailyDto;
import com.ssafy.vitawearable.dto.Sleep.SleepMonthlyDto;
import com.ssafy.vitawearable.dto.Sleep.SleepWeeklyDto;
import com.ssafy.vitawearable.dto.Step.StepDailyDto;
import com.ssafy.vitawearable.dto.Step.StepMonthlyDto;
import com.ssafy.vitawearable.dto.Step.StepWeeklyDto;
import com.ssafy.vitawearable.dto.Stress.StressDailyDto;
import com.ssafy.vitawearable.dto.Stress.StressMonthlyDto;
import com.ssafy.vitawearable.dto.Stress.StressWeeklyDto;
import com.ssafy.vitawearable.dto.Weight.WeightDailyDto;
import com.ssafy.vitawearable.dto.Weight.WeightMonthlyDto;
import com.ssafy.vitawearable.dto.Weight.WeightWeeklyDto;
import com.ssafy.vitawearable.entity.*;
import com.ssafy.vitawearable.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<MonthlyWearable> month = monthlyWearableRepo.findByUser_UserId(userId);
        Stream<MonthlyWearable> WeekStream = month.stream()
                .filter(data -> data.getMonthlyWearableStep() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return month.stream()
                .filter(data -> data.getMonthlyWearableStep() != 0)
                .skip(skipSize)
                .map(monthly -> mapper.map(monthly, StepMonthlyDto.class))
                .sorted(Comparator.comparing(StepMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 걸음수 주별
    @Override
    public List<StepWeeklyDto> stepWeekly(String userId) {
        List<WeeklyWearable> week = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = week.stream()
                .filter(data -> data.getWeeklyWearableStep() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return week.stream()
                .filter(data -> data.getWeeklyWearableStep() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, StepWeeklyDto.class))
                .sorted(Comparator.comparing(StepWeeklyDto::getDate))
                .collect(Collectors.toList());
    }

    // 걸음수 일별
    @Override
    public List<StepDailyDto> stepDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Stream<DailyWearable> DailyStream = dailyWearables.stream()
                .filter(data -> data.getDailyWearableStep() != 0);
        long skipSize = DailyStream.count() - 10;
        if (skipSize < 0) skipSize = 0;

        return dailyWearables.stream()
                .filter(data -> data.getDailyWearableStep() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, StepDailyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String stepTop(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Comparator<DailyWearable> comparatorByStep = Comparator.comparingInt(DailyWearable::getDailyWearableStep);
        Optional<DailyWearable> topStepDailyWearable = dailyWearables.stream()
                .max(comparatorByStep);
        if (topStepDailyWearable.isPresent()) {
            ZonedDateTime d = topStepDailyWearable.get().getDate();
            return d.getYear() + "년 " +
                    d.getMonthValue() + "월 " +
                    d.getDayOfMonth() + "일 ";
        } else {
            return "";
        }
    }



    // 활동량 달별
    @Override
    public List<EnergyMonthlyDto> energyMonthly(String userId) {
        List<MonthlyWearable> month = monthlyWearableRepo.findByUser_UserId(userId);
        Stream<MonthlyWearable> WeekStream = month.stream()
                .filter(data -> data.getMonthlyWearableEnergy() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return month.stream()
                .filter(data -> data.getMonthlyWearableEnergy() != 0)
                .skip(skipSize)
                .map(monthly -> mapper.map(monthly, EnergyMonthlyDto.class))
                .sorted(Comparator.comparing(EnergyMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 활동량 주별
    @Override
    public List<EnergyWeeklyDto> energyWeekly(String userId) {
        List<WeeklyWearable> week = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = week.stream()
                .filter(data -> data.getWeeklyWearableEnergy() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return week.stream()
                .filter(data -> data.getWeeklyWearableEnergy() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, EnergyWeeklyDto.class))
                .sorted(Comparator.comparing(EnergyWeeklyDto::getDate))
                .collect(Collectors.toList());
    }

    // 활동량 일별
    @Override
    public List<EnergyDailyDto> energyDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Stream<DailyWearable> DailyStream = dailyWearables.stream()
                .filter(data -> data.getDailyWearableEnergy() != 0);
        long skipSize = DailyStream.count() - 10;
        if (skipSize < 0) skipSize = 0;

        return dailyWearables.stream()
                .filter(data -> data.getDailyWearableEnergy() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, EnergyDailyDto.class))
                .collect(Collectors.toList());
    }

    // 심박수 달별
    @Override
    public List<RhrMonthlyDto> rhrMonthly(String userId) {
        List<MonthlyWearable> month = monthlyWearableRepo.findByUser_UserId(userId);
        Stream<MonthlyWearable> WeekStream = month.stream()
                .filter(data -> data.getMonthlyWearableRhr() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return month.stream()
                .filter(data -> data.getMonthlyWearableRhr() != 0)
                .skip(skipSize)
                .map(monthly -> mapper.map(monthly, RhrMonthlyDto.class))
                .sorted(Comparator.comparing(RhrMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 심박수 주별
    @Override
    public List<RhrWeeklyDto> rhrWeekly(String userId) {
        List<WeeklyWearable> week = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = week.stream()
                .filter(data -> data.getWeeklyWearableRhr() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return week.stream()
                .filter(data -> data.getWeeklyWearableRhr() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, RhrWeeklyDto.class))
                .sorted(Comparator.comparing(RhrWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 심박수 일별
    @Override
    public List<RhrDailyDto> rhrDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Stream<DailyWearable> DailyStream = dailyWearables.stream()
                .filter(data -> data.getDailyWearableRhr() != 0);
        long skipSize = DailyStream.count() - 10;
        if (skipSize < 0) skipSize = 0;

        return dailyWearables.stream()
                .filter(data -> data.getDailyWearableRhr() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, RhrDailyDto.class))
                .collect(Collectors.toList());

    }

    // 수면 달별
    @Override
    public List<SleepMonthlyDto> sleepMonthly(String userId) {
        List<MonthlyWearable> month = monthlyWearableRepo.findByUser_UserId(userId);
        Stream<MonthlyWearable> WeekStream = month.stream()
                .filter(data -> data.getMonthlyWearableSleep() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return month.stream()
                .filter(data -> data.getMonthlyWearableSleep() != 0)
                .skip(skipSize)
                .map(monthly -> mapper.map(monthly, SleepMonthlyDto.class))
                .sorted(Comparator.comparing(SleepMonthlyDto::getDate))
                .collect(Collectors.toList());

    }

    // 수면 주별
    @Override
    public List<SleepWeeklyDto> sleepWeekly(String userId) {
        List<WeeklyWearable> week = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = week.stream()
                .filter(data -> data.getWeeklyWearableSleep() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return week.stream()
                .filter(data -> data.getWeeklyWearableSleep() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, SleepWeeklyDto.class))
                .sorted(Comparator.comparing(SleepWeeklyDto::getDate))
                .collect(Collectors.toList());
    }

    // 수면 일별
    @Override
    public List<SleepDailyDto> sleepDaily(String userId) {
        List<DailySleep> dailyWearables = dailySleepRepo.findByUser_UserId(userId);
//        ZonedDateTime endTime = dailyWearables.get(dailyWearables.size()-1).getDailySleepStart().minusDays(7);
        ZonedDateTime endTime = dailyWearables.stream()
                .sorted(Comparator.comparing(DailySleep::getDailySleepStart)).collect(Collectors.toList())
                .get(dailyWearables.size()-1).getDailySleepStart().minusDays(7);
        return dailyWearables.stream()
                .filter(data -> data.getDailySleepTotal() != 0 &&
                        data.getDailySleepStart().isAfter(endTime))
                .map(daily -> mapper.map(daily, SleepDailyDto.class))
//                .limit(20)
                .sorted(Comparator.comparing(SleepDailyDto::getDailySleepStart))
                .collect(Collectors.toList());
    }

    // 스트레스 달별
    @Override
    public List<StressMonthlyDto> stressMonthly(String userId) {
        List<MonthlyWearable> monthlyStress = monthlyWearableRepo.findByUser_UserId(userId);
        int skipSize = monthlyStress.size()-12;
        if (skipSize < 0) skipSize = 0;
        return monthlyStress.stream()
                .filter(data -> data.getMonthlyWearableStress() != 0)
                .map(monthly -> mapper.map(monthly, StressMonthlyDto.class))
//                .limit(12)
                .skip(skipSize)
                .sorted(Comparator.comparing(StressMonthlyDto::getDate))
                .collect(Collectors.toList());

    }

    // 스트레스 주별
    @Override
    public List<StressWeeklyDto> stressWeekly(String userId) {
        List<WeeklyWearable> week = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = week.stream()
                .filter(data -> data.getWeeklyWearableStress() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;
        return week.stream()
                .filter(data -> data.getWeeklyWearableStress() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, StressWeeklyDto.class))
                .sorted(Comparator.comparing(StressWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 스트레스 일별
    @Override
    public List<StressDailyDto> stressDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Stream<DailyWearable> DailyStream = dailyWearables.stream()
                .filter(data -> data.getDailyWearableStress() != 0);
        long skipSize = DailyStream.count() - 10;
        if (skipSize < 0) skipSize = 0;

        return dailyWearables.stream()
                .filter(data -> data.getDailyWearableStress() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, StressDailyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String stressLess(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId).stream()
                .filter(d -> d.getDailyWearableStress() !=0)
                .collect(Collectors.toList());
        Comparator<DailyWearable> comparatorByStress = Comparator.comparingInt(DailyWearable::getDailyWearableStress);
        Optional<DailyWearable> lessStressDailyWearable = dailyWearables.stream()
                .min(comparatorByStress);
        if (lessStressDailyWearable.isPresent()) {
            ZonedDateTime d = lessStressDailyWearable.get().getDate();
            return d.getYear() + "년 " +
                    d.getMonthValue() + "월 " +
                    d.getDayOfMonth() + "일 ";
        } else {
            return "";
        }
    }


    // 무게 달별
    @Override
    public List<WeightMonthlyDto> weightMonthly(String userId) {
        List<MonthlyWearable> monthlyWeight = monthlyWearableRepo.findByUser_UserId(userId);
        Stream<MonthlyWearable> WeekStream = monthlyWeight.stream()
                .filter(data -> data.getMonthlyWearableWeight() != 0
                        && data.getMonthlyWearableFat() != 0
                        && data.getMonthlyWearableMuscle() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;

        return monthlyWeight.stream()
                .filter(data -> data.getMonthlyWearableWeight() != 0
                        && data.getMonthlyWearableFat() != 0
                        && data.getMonthlyWearableMuscle() != 0)
                .map(monthly -> mapper.map(monthly, WeightMonthlyDto.class))
//                .limit(12)
                .skip(skipSize)
                .sorted(Comparator.comparing(WeightMonthlyDto::getDate))
                .collect(Collectors.toList());
    }

    // 무게 주별
    @Override
    public List<WeightWeeklyDto> weightWeekly(String userId) {
        List<WeeklyWearable> weeklyWearable = weeklyWearableRepo.findByUser_UserId(userId);
        Stream<WeeklyWearable> WeekStream = weeklyWearable.stream()
                .filter(data -> data.getWeeklyWearableWeight() != 0
                        && data.getWeeklyWearableFat() != 0
                        && data.getWeeklyWearableMuscle() != 0);
        long skipSize = WeekStream.count() - 20;
        if (skipSize < 0) skipSize = 0;

        return weeklyWearable.stream()
                .filter(data -> data.getWeeklyWearableWeight() != 0
                        && data.getWeeklyWearableFat() != 0
                        && data.getWeeklyWearableMuscle() != 0)
                .map(weekly -> mapper.map(weekly, WeightWeeklyDto.class))
//                .limit(12)
                .skip(skipSize)
                .sorted(Comparator.comparing(WeightWeeklyDto::getDate))
                .collect(Collectors.toList());

    }

    // 무게 일별
    @Override
    public List<WeightDailyDto> weightDaily(String userId) {
        List<DailyWearable> dailyWearables = dailyWearableRepo.findByUser_UserId(userId);
        Stream<DailyWearable> DailyStream = dailyWearables.stream()
                .filter(data -> data.getDailyWearableWeight() != 0
                        && data.getDailyWearableFat() != 0
                        && data.getDailyWearableMuscle() != 0);
        long skipSize = DailyStream.count() - 10;
        if (skipSize < 0) skipSize = 0;

        return dailyWearables.stream()
                .filter(data -> data.getDailyWearableWeight() != 0
                        && data.getDailyWearableFat() != 0
                        && data.getDailyWearableMuscle() != 0)
                .skip(skipSize)
                .map(weekly -> mapper.map(weekly, WeightDailyDto.class))
                .collect(Collectors.toList());
    }
}
