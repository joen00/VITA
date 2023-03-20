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
  
  var api_url = 'http://localhost:8080/wearable/user/weight'
  
  export default {
    name: 'WearableWeight',
    props: {
      msg: String
    },
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekWeight() {
        axios.get(api_url + '/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthWeight() {
        axios.get(api_url + '/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearWeight() {
        axios.get(api_url + '/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
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
  