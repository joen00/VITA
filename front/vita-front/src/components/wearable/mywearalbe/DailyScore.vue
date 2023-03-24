<template>
    <div>
        <h1>연별 데일리 종합점수</h1>
        {{ data }}
    </div>
  </template>
  

  <script>
  import axios from 'axios'
  
  export default {
    name: 'DailyScore',
    data () {
        return {
          data: [],
      }
    },
    created() {
      this.dailyScore();
    },
    methods: {
        async dailyScore() {
          await axios.get('http://localhost:8083/wearable/user/score' + '/2023', {
        headers: {'Content-Type': 'application/json',
                  'token': this.$store.state.test_token},
        }).then(res => {
          this.data = res.data
        }) 
        },
      },
  };
  </script>
  
  <style>
  
  </style>
  