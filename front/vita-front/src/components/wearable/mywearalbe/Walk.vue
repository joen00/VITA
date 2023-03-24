<template>
  <div>
    <h1> 걸음수 </h1>
    <button v-on:click="weekWalk">1주</button>
    <button v-on:click="monthWalk">1개월</button>
    <button v-on:click="yearWalk">1년</button>
    <WalkChart :key="componentKey" :data="data" />
    <WalkPast :past="past" />
    <!-- {{ past }} -->
    
  </div>
</template>

<script>
import axios from 'axios'
import WalkChart from "./WalkChart.vue"
import WalkPast from "./WalkPast.vue"
  
export default {
  name: 'WalkRoot',
  data () {
      return {
        data: [],
        past: null,
        componentKey: 0,
    }
  },
  created() {
    this.weekWalk();
    this.pastAndNowWalk();
  },
  components: {
    WalkChart,
    WalkPast
  },
  methods: {
      async weekWalk() {
        await axios.get(this.$store.state.url + 'step/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          // return {"Step": e.dailyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.dailyWearableStep, "date": e.date};
        })
        // this.data = res.data
      })
      this.componentKey += 1;  
      },
      async monthWalk() {
        await axios.get(this.$store.state.url + 'step/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        // this.data = res.data
        this.data = res.data.map(function(e){
          // return {"Step": e.weeklyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.weeklyWearableStep, "date": e.date};
        })
      })
      this.componentKey += 1;  
      },
      async yearWalk() {
        await axios.get(this.$store.state.url + 'step/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data
        this.data = res.data.map(function(e){
          return {"Step": e.monthlyWearableStep, "date": e.date};
        })
        this.componentKey += 1; 
      })
      },
      async pastAndNowWalk() {
        await axios.get(this.$store.state.url + 'step/past' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.past = res.data
      })
      },
    },
};
</script>

<style>

</style>
