<template>
    <div>
      {{past}}
      <span v-if="past.weekNowWearableRhr < past.weekPastWearableRhr"> 저번주에 더 심박수가 높습니다 </span>
    <span v-else> 이번주에 더 심박수가 높습니다 </span>
    <br>
    <p>이번주 심박수</p>
    <div class="progress" id="weekNowWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableRhr }}</span>
      </div>
    </div>
    <br>
    <span>저번주 심박수</span>
    <div class="progress" id="weekPastWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableRhr }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableRhr < past.monthPastWearableRhr"> 전달이 더 심박수가 높습니다 </span>
    <span v-else> 이번달이 더 심박수가 높습니다 </span>
    <br>
    <p>이번달 심박수</p>
    <div class="progress" id="monthNowWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{ past.monthNowWearableRhr }}</span>
      </div>
    </div>
    <br>
    <span>저번달 심박수</span>
    <div class="progress" id="monthPastWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{past.monthPastWearableRhr}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableRhr < past.yearPastWearableRhr"> 작년에 더 심박수가 높습니다 </span>
    <span v-else> 올해에 더 심박수가 높습니다 </span>
    <br>
    <p>이번해 심박수</p>
    <div class="progress" id="yearNowWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
        <span class="sr-only">{{ past.yearNowWearableRhr }}</span>
      </div>
    </div>
    <br>
    <span>작년 심박수</span>
    <div class="progress" id="yearPastWearableRhr">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
        <span class="sr-only">{{ past.yearPastWearableRhr }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'RhrPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>