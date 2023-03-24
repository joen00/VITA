<template>
    <div>
      {{past}}
      <span v-if="past.weekNowWearableSleep < past.weekPastWearableSleep"> 저번주에 더 수면가 높습니다 </span>
    <span v-else> 이번주에 더 수면가 높습니다 </span>
    <br>
    <p>이번주 수면</p>
    <div class="progress" id="weekNowWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableSleep }}</span>
      </div>
    </div>
    <br>
    <span>저번주 수면</span>
    <div class="progress" id="weekPastWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableSleep }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableSleep < past.monthPastWearableSleep"> 전달이 더 수면가 높습니다 </span>
    <span v-else> 이번달이 더 수면가 높습니다 </span>
    <br>
    <p>이번달 수면</p>
    <div class="progress" id="monthNowWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{ past.monthNowWearableSleep }}</span>
      </div>
    </div>
    <br>
    <span>저번달 수면</span>
    <div class="progress" id="monthPastWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{past.monthPastWearableSleep}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableSleep < past.yearPastWearableSleep"> 작년에 더 수면가 높습니다 </span>
    <span v-else> 올해에 더 수면가 높습니다 </span>
    <br>
    <p>이번해 수면</p>
    <div class="progress" id="yearNowWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
        <span class="sr-only">{{ past.yearNowWearableSleep }}</span>
      </div>
    </div>
    <br>
    <span>작년 수면</span>
    <div class="progress" id="yearPastWearableSleep">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
        <span class="sr-only">{{ past.yearPastWearableSleep }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'SleepPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>