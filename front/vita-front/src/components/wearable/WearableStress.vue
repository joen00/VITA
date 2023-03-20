<template>
    <div>
      <h1> 스트레스 </h1>
      <h3>나의 스트레스 기록을 보여줘요.</h3>
      <button v-on:click="weekStress">1주</button>
      <button v-on:click="monthStress">1개월</button>
      <button v-on:click="yearStress">1년</button>
      {{data}}
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  var api_url = 'http://localhost:8080/wearable/user/stress'
  
  export default {
    name: 'WearableStress',
    props: {
      msg: String
    },
    data () {
      return {
        data: null
    }
   },
    methods: {
      weekStress() {
        axios.get(api_url + '/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      monthStress() {
        axios.get(api_url + '/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
      })
  
      },
      yearStress() {
        axios.get(api_url + '/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1NTU1NTU1NTU1IiwibmFtZSI6ImppbmhvIiwiaWF0IjoxNTE2MjM5MDIyfQ.DRS63ZgjpVEptIqCqxoMXSLfAXjiYkUH5qfRD7nXyUU'},
      }).then(res => {
        this.data = res.data;
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
  