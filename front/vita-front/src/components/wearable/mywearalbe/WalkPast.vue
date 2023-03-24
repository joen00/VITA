<template>
    <div>
      {{past}}
      <br>
    <span v-if="past.weekNowWearableStep < past.weekPastWearableStep"> 저번주에 더 많이 걸었습니다 </span>
    <span v-else> 이번주에 더 많이 걸었습니다 </span>
    <br>
    <p>이번주 걸음</p>
    <div class="progress" id="weekNowWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableStep }}</span>
      </div>
    </div>
    <br>
    <span>저번주 걸음</span>
    <div class="progress" id="weekPastWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableStep }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableStep < past.monthPastWearableStep"> 전달이 더 많이 걸었습니다 </span>
    <span v-else> 이번달이 더 많이 걸었습니다 </span>
    <br>
    <p>이번달 걸음</p>
    <div class="progress" id="monthNowWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{ past.monthNowWearableStep }}</span>
      </div>
    </div>
    <br>
    <span>저번달 걸음</span>
    <div class="progress" id="monthPastWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{past.monthPastWearableStep}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableStep < past.yearPastWearableStep"> 작년에 더 많이 걸었습니다 </span>
    <span v-else> 올해에 더 많이 걸었습니다 </span>
    <br>
    <p>이번해 걸음</p>
    <div class="progress" id="yearNowWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
        <span class="sr-only">{{ past.yearNowWearableStep }}</span>
      </div>
    </div>
    <br>
    <span>작년 걸음</span>
    <div class="progress" id="yearPastWearableStep">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:2%">
        <span class="sr-only">{{ past.yearPastWearableStep }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'WalkPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>