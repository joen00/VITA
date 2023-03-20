<template>
  <div class="home">
    <h1>마이 헬스 데이터</h1>
    <p>나의 종합 건강 점수를 확인해보세요.</p>
    <wearableWalk />
    <WearableEnergy />
    <WearableRhr />
    <WearableSleep />
    <WearableStress />
    <WearableWeight />
  </div>
</template>

<script>
import WearableWalk from '@/components/wearable/WearableWalk.vue'
import WearableEnergy from '@/components/wearable/WearableEnergy.vue'
import WearableRhr from '@/components/wearable/WearableRhr.vue'
import WearableSleep from '@/components/wearable/WearableSleep.vue'
import WearableStress from '@/components/wearable/WearableStress.vue'
import WearableWeight from '@/components/wearable/WearableWeight.vue'

export default {
  name: 'HomeView',
  components: {
    WearableWalk,
    WearableEnergy,
    WearableRhr,
    WearableSleep,
    WearableStress,
    WearableWeight
  }
}
</script>
