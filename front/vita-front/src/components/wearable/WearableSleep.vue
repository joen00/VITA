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
  
  var api_url = 'http://localhost:8080/wearable/user/sleep'
  
  export default {
    name: 'WearableSleep',
    props: {
      msg: String
    },
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekSleep() {
        axios.get(api_url + '/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthSleep() {
        axios.get(api_url + '/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearSleep() {
        axios.get(api_url + '/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
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
  