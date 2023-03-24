<template>
    <div>
      <h1> 활동에너지 </h1>
      <button v-on:click="weekEnergy">1주</button>
      <button v-on:click="monthEnergy">1개월</button>
      <button v-on:click="yearEnergy">1년</button>
      <EnergyChart :key="componentKey" :data="data" />
      <EnergyPast :past="past" />
      <br>
    
  </div>
  </template>
  
  <script>
  import axios from 'axios'
  import EnergyChart from "./EnergyChart.vue"
  import EnergyPast from "./EnergyPast.vue"

  export default {
    name: 'EnergyRoot',
    data () {
      return {
        data: [],
        past: null,
        componentKey: 0,
    }
   },
   created() {
    this.weekEnergy();
    this.pastAndNowEnergy();
  },
  components: {
    EnergyChart,
    EnergyPast,
  },
    methods: {
      async weekEnergy() {
        await axios.get(this.$store.state.url + 'energy/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.dailyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async monthEnergy() {
        await axios.get(this.$store.state.url + 'energy/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.weeklyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async yearEnergy() {
        await axios.get(this.$store.state.url + 'energy/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.monthlyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async pastAndNowEnergy() {
        await axios.get(this.$store.state.url + 'energy/past' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.past = res.data
      })
      },
    },
  }
  </script>
  
  <style>
  
  </style>
  