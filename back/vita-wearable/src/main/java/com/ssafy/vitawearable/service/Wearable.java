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

import java.util.List;

public interface Wearable {
    // 걸음수 달별
    List<StepMonthlyDto> stepMonthly(String userId);

    // 걸음수 주별
    List<StepWeeklyDto> stepWeekly(String userId);

    // 걸음수 일별
    List<StepDailyDto> stepDaily(String userId);

    String stepTop(String userId);

    // 활동량 달별
    List<EnergyMonthlyDto> energyMonthly(String userId);

    // 활동량 주별
    List<EnergyWeeklyDto> energyWeekly(String userId);

    // 활동량 일별
    List<EnergyDailyDto> energyDaily(String userId);

    // 심박수 달별
    List<RhrMonthlyDto> rhrMonthly(String userId);

    // 심박수 주별
    List<RhrWeeklyDto> rhrWeekly(String userId);

    // 심박수 일별
    List<RhrDailyDto> rhrDaily(String userId);

    // 수면 달별
    List<SleepMonthlyDto> sleepMonthly(String userId);

    // 수면 주별
    List<SleepWeeklyDto> sleepWeekly(String userId);

    // 수면 일별
    List<SleepDailyDto> sleepDaily(String userId);

    // 스트레스 달별
    List<StressMonthlyDto> stressMonthly(String userId);

    // 스트레스 주별
    List<StressWeeklyDto> stressWeekly(String userId);

    // 스트레스 일별
    List<StressDailyDto> stressDaily(String userId);

    String stressLess(String userId);

    // 무게 달별
    List<WeightMonthlyDto> weightMonthly(String userId);

    // 무게 주별
    List<WeightWeeklyDto> weightWeekly(String userId);

    // 무게 일별
    List<WeightDailyDto> weightDaily(String userId);

}
