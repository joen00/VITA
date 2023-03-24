<template>
    <div>
      <h1> 체중 </h1>
      <h3>나의 체중 기록을 보여줘요.</h3>
      <button v-on:click="weekWeight">1주</button>
      <button v-on:click="monthWeight">1개월</button>
      <button v-on:click="yearWeight">1년</button>
      {{data}}
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'WeightRoot',
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekWeight() {
        axios.get(this.$store.state.url + 'weight/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthWeight() {
        axios.get(this.$store.state.url + 'weight/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearWeight() {
        axios.get(this.$store.state.url + 'weight/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data;
      })
  
      },
    },
    created() {
      this.weekWeight();
    }
  }
  </script>
  
  <style>
  
  </style>
  