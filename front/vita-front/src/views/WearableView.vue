<template>
  <div class="home">
    <h1>마이 헬스 데이터</h1>
    <p>나의 종합 건강 점수를 확인해보세요.</p>
    <TotalScore />
    <DailyScore />
    <WalkRoot />
    <EnergyRoot />
    <RhrRoot />
    <SleepRoot />
    <StressRoot />
    <WeightRoot />
  </div>
</template>

<script>
import TotalScore from '@/components/wearable/mywearalbe/TotalScore.vue'
import DailyScore from '@/components/wearable/mywearalbe/DailyScore.vue'
import WalkRoot from '@/components/wearable/mywearalbe/Walk.vue'
import EnergyRoot from '@/components/wearable/mywearalbe/Energy.vue'
import RhrRoot from '@/components/wearable/mywearalbe/Rhr.vue'
import SleepRoot from '@/components/wearable/mywearalbe/Sleep.vue'
import StressRoot from '@/components/wearable/mywearalbe/Stress.vue'
import WeightRoot from '@/components/wearable/mywearalbe/Weight.vue'

export default {
  name: 'HomeView',
  components: {
    TotalScore,
    DailyScore,
    WalkRoot,
    EnergyRoot,
    RhrRoot,
    SleepRoot,
    StressRoot,
    WeightRoot
  }
}
</script>
