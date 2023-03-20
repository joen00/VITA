<template>
  <div class="home">
    <h1>마이 헬스 데이터</h1>
    <p>나의 종합 건강 점수를 확인해보세요.</p>
    <wearable-walk msg="msg 보냄"/>
  </div>
</template>

<script>
import WearableWalk from '@/components/wearable/WearableWalk.vue'

export default {
  name: 'HomeView',
  components: {
    WearableWalk
  }
}
</script>
