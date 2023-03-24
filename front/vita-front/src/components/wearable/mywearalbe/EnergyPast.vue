<template>
    <div>
      {{past}}
      <span v-if="past.weekNowWearableEnergy < past.weekPastWearableEnergy"> 저번주에 더 활동에너지가 높습니다 </span>
    <span v-else> 이번주에 더 활동에너지가 높습니다 </span>
    <br>
    <p>이번주 활동에너지</p>
    <div class="progress" id="weekNowWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
        <span class="sr-only">{{ past.weekNowWearableEnergy }}</span>
      </div>
    </div>
    <br>
    <span>저번주 활동에너지</span>
    <div class="progress" id="weekPastWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
        <span class="sr-only">{{ past.weekPastWearableEnergy }}</span>
      </div>
    </div>
    <br>
    <span v-if="past.monthNowWearableEnergy < past.monthPastWearableEnergy"> 전달이 더 활동에너지가 높습니다 </span>
    <span v-else> 이번달이 더 활동에너지가 높습니다 </span>
    <br>
    <p>이번달 활동에너지</p>
    <div class="progress" id="monthNowWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
        <span class="sr-only">{{ past.monthNowWearableEnergy }}</span>
      </div>
    </div>
    <br>
    <span>저번달 활동에너지</span>
    <div class="progress" id="monthPastWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
        <span class="sr-only">{{past.monthPastWearableEnergy}}</span>
      </div>
    </div>
    <br>
    <span v-if="past.yearNowWearableEnergy < past.yearPastWearableEnergy"> 작년에 더 활동에너지가 높습니다 </span>
    <span v-else> 올해에 더 활동에너지가 높습니다 </span>
    <br>
    <p>이번해 활동에너지</p>
    <div class="progress" id="yearNowWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
        <span class="sr-only">{{ past.yearNowWearableEnergy }}</span>
      </div>
    </div>
    <br>
    <span>작년 활동에너지</span>
    <div class="progress" id="yearPastWearableEnergy">
      <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
        <span class="sr-only">{{ past.yearPastWearableEnergy }}</span>
      </div>
    </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'EnergyPast',
    props: {
      past: Object,
    },
  }
  </script>
  
  <style>
  </style>