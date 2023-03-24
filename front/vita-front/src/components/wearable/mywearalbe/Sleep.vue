<template>
    <div>
      <h1> 수면 </h1>
      <h3>나의 수면 기록을 보여줘요.</h3>
      <button v-on:click="weekSleep">1주</button>
      <button v-on:click="monthSleep">1개월</button>
      <button v-on:click="yearSleep">1년</button>
      {{data}}
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'SleepRoot',
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekSleep() {
        axios.get(this.$store.state.url + 'sleep/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthSleep() {
        axios.get(this.$store.state.url + 'sleep/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearSleep() {
        axios.get(this.$store.state.url + 'sleep/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
    },
    created() {
      this.weekSleep();
    }
  }
  </script>
  
  <style>
  
  </style>
  