<template>
    <div>
      <h1> 스트레스 </h1>
      <h3>나의 스트레스 기록을 보여줘요.</h3>
      <button v-on:click="weekStress">1주</button>
      <button v-on:click="monthStress">1개월</button>
      <button v-on:click="yearStress">1년</button>
      {{data}}
      
      <StressChart :key="componentKey" :data="data" />
      <StressPast :past="past" />
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import StressChart from './StressChart.vue'
  import StressPast from './StressPast.vue'
  
  export default {
    name: 'StressRoot',
    data () {
      return {
        data: null,
        past: null,
        componentKey: 0,
    }
   },
   components: {
    StressChart,
    StressPast
   },
    methods: {
      async weekStress() {
        await axios.get(this.$store.state.url + 'stress/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Stress": e.dailyWearableStress, "date": e.date};
        })
        // this.data = res.data
      })
      this.componentKey += 1;  
      },
      async monthStress() {
        await axios.get(this.$store.state.url + 'stress/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        // this.data = res.data
        this.data = res.data.map(function(e){
          return {"Stress": e.weeklyWearableStress, "date": e.date};
        })
      })
      this.componentKey += 1;  
      },
      async yearStress() {
        await axios.get(this.$store.state.url + 'stress/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data
        this.data = res.data.map(function(e){
          return {"Stress": e.monthlyWearableStress, "date": e.date};
        })
        this.componentKey += 1; 
      })
      },
      async pastAndNowStress() {
        await axios.get(this.$store.state.url + 'stress/past' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.past = res.data
      })
      },
    },
    created() {
      this.weekStress();
    }
  }
  </script>
  
  <style>
  
  </style>
  