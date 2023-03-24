<template>
    <div>
      <h1> 심박수 </h1>
      <h3>나의 심박수 기록을 보여줘요.</h3>
      <button v-on:click="weekRhr">1주</button>
      <button v-on:click="monthRhr">1개월</button>
      <button v-on:click="yearRhr">1년</button>
      {{data}}
      <RhrChart :key="componentKey" :data="data" :date="date" />
      <RhrPast :past="past" />
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import RhrChart from './RhrChart.vue'
  import RhrPast from './RhrPast.vue'

  export default {
    name: 'RhrRoot',
    data () {
      return {
        data: null,
        date: null,
        past: null,
        componentKey: 0,
    }
   },
  components: {
    RhrChart,
    RhrPast,
  },
    methods: {
      async weekRhr() {
        await axios.get(this.$store.state.url + 'rhr/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.dailyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
  
      },
      async monthRhr() {
        await axios.get(this.$store.state.url + 'rhr/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.weeklyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
  
      },
      async yearRhr() {
        await axios.get(this.$store.state.url + 'rhr/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.monthlyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
      },
      async pastAndNowRhr() {
        await axios.get(this.$store.state.url + 'rhr/past' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.past = res.data
      })
      },
    },
    created() {
      this.weekRhr();
      this.pastAndNowRhr();
    }
  }
  </script>
  
  <style>
  
  </style>
  