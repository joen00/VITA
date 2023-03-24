<template>
    <div>
        <h1>export 시점 종합 점수</h1>
        {{ data }}
    </div>
  </template>
  

  <script>
  import axios from 'axios'
    
  export default {
    name: 'TotalScore',
    data () {
        return {
          data: [],
      }
    },
    created() {
      this.totalScore();
    },
    methods: {
        async totalScore() {
          await axios.get('http://localhost:8083/wearable/' + 'score', {
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
  