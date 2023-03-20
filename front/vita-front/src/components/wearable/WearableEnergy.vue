<template>
    <div>
      <h1> 활동에너지 </h1>
      <h3>나의 활동에너지 기록을 보여줘요.</h3>
      <button v-on:click="weekEnergy">1주</button>
      <button v-on:click="monthEnergy">1개월</button>
      <button v-on:click="yearEnergy">1년</button>
      {{data}}
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  var api_url = 'http://localhost:8080/wearable/user/energy'
  
  export default {
    name: 'WearableEnergy',
    props: {
      msg: String
    },
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekEnergy() {
        axios.get(api_url + '/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthEnergy() {
        axios.get(api_url + '/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearEnergy() {
        axios.get(api_url + '/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
    },
    created() {
      this.weekEnergy();
    }
  }
  </script>
  
  <style>
  
  </style>
  