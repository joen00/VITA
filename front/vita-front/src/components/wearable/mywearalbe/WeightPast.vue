<template>
    <div>
      {{past}}
      <span v-if="past.weekNowWearableWeight < past.weekPastWearableWeight"> 저번주에 더 체중가 높습니다 </span>
    <span v-else> 이번주에 더 체중가 높습니다 </span>
    <br>
    <p>이번주 체중</p>
    <div class="progress" id="weekNowWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableWeight }}</span>
      </div>
    </div>
    <br>
    <span>저번주 체중</span>
    <div class="progress" id="weekPastWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableWeight }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableWeight < past.monthPastWearableWeight"> 전달이 더 체중가 높습니다 </span>
    <span v-else> 이번달이 더 체중가 높습니다 </span>
    <br>
    <p>이번달 체중</p>
    <div class="progress" id="monthNowWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{ past.monthNowWearableWeight }}</span>
      </div>
    </div>
    <br>
    <span>저번달 체중</span>
    <div class="progress" id="monthPastWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{past.monthPastWearableWeight}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableWeight < past.yearPastWearableWeight"> 작년에 더 체중가 높습니다 </span>
    <span v-else> 올해에 더 체중가 높습니다 </span>
    <br>
    <p>이번해 체중</p>
    <div class="progress" id="yearNowWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
        <span class="sr-only">{{ past.yearNowWearableWeight }}</span>
      </div>
    </div>
    <br>
    <span>작년 체중</span>
    <div class="progress" id="yearPastWearableWeight">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
        <span class="sr-only">{{ past.yearPastWearableWeight }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'WeightPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>