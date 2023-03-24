<template>
    <div>
      {{past}}
      <span v-if="past.weekNowWearableStress < past.weekPastWearableStress"> 저번주에 더 스트레스가 높습니다 </span>
    <span v-else> 이번주에 더 스트레스가 높습니다 </span>
    <br>
    <p>이번주 스트레스</p>
    <div class="progress" id="weekNowWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableStress }}</span>
      </div>
    </div>
    <br>
    <span>저번주 스트레스</span>
    <div class="progress" id="weekPastWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableStress }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableStress < past.monthPastWearableStress"> 전달이 더 스트레스가 높습니다 </span>
    <span v-else> 이번달이 더 스트레스가 높습니다 </span>
    <br>
    <p>이번달 스트레스</p>
    <div class="progress" id="monthNowWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{ past.monthNowWearableStress }}</span>
      </div>
    </div>
    <br>
    <span>저번달 스트레스</span>
    <div class="progress" id="monthPastWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{past.monthPastWearableStress}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableStress < past.yearPastWearableStress"> 작년에 더 스트레스가 높습니다 </span>
    <span v-else> 올해에 더 스트레스가 높습니다 </span>
    <br>
    <p>이번해 스트레스</p>
    <div class="progress" id="yearNowWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
        <span class="sr-only">{{ past.yearNowWearableStress }}</span>
      </div>
    </div>
    <br>
    <span>작년 스트레스</span>
    <div class="progress" id="yearPastWearableStress">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
        <span class="sr-only">{{ past.yearPastWearableStress }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'StressPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>