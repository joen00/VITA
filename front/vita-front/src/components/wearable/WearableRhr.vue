<template>
    <div>
      <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
      <div id="rhr-middle">
          <div id="rhr-middle-left-btn">
            <div id="rhr-middle-left-btn-up">
              <button id="btn-rhr" v-on:click="weekRhr">1주</button>
              <br>
              <button id="btn-rhr" v-on:click="monthRhr">1개월</button>
              <br>
              <button id="btn-rhr" v-on:click="yearRhr">1년</button>
            </div>
            <div id="rhr-middle-left-btn-down"> 
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;">심박변이</div>
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;">종합점수</div>
              <div style="font-size: 60px;
                          margin-top: -0.8rem;
                          font-weight: 800;
                          color: #5B5A63;">80</div>
            </div>
          </div>
          <div id="rhr-middle-left">
            <!-- 그래프 그려지는 곳 -->
            <!-- <div id="rhrchart"></div> -->
            <RhrChart :key="componentKey" :date="date" :data="data" />
          </div>
          <div id="rhr-middle-right">
            <div id="rhr-middle-right-div">
              <div>이번주 심박변이가 그 전주보다 줄었습니다.</div>
            </div>
            <div id="rhr-middle-right-div">a</div>
            <div id="rhr-middle-right-div">dfdfz</div>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import ComponentHeader from "@/components/common/ComponentHeader.vue";
  import RhrChart from "@/components/wearable/RhrChart.vue";
  
  export default {
    name: "Wearablerhr",
    components: {
        ComponentHeader,
        RhrChart
      },
    data: () => ({
          ComponentHeaderTitle: "심박변이",
          ComponentHeaderContent: "나의 심박변이 기록을 보여줘요.",
          data: [],
          past: null,
          componentKey: 0,
    }),
  // 데이터 가져오는 곳
  created() {
      this.weekRhr();
      this.pastAndNowRhr();
    },
    methods: {
      async weekRhr() {
        await axios.get(this.$store.state.url + 'rhr/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.dailyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
  
      },
      async monthRhr() {
        await axios.get(this.$store.state.url + 'rhr/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.weeklyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
  
      },
      async yearRhr() {
        await axios.get(this.$store.state.url + 'rhr/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return e.monthlyWearableRhr;
        })
        this.date = res.data.map(function(e){
          return e.date;
        })
        this.componentKey += 1;
      })
      },
      async pastAndNowRhr() {
        await axios.get(this.$store.state.url + 'rhr/past' , {
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
  #rhr-middle{
      height: 400px;
      /* background-color: aqua; */
      margin: 3rem;
      display: flex;
      justify-content: center;
      align-items: center;
  }
  #rhr-middle-left-btn{
      width: 12%;
      height: 100%;
      /* display: flex; */
      /* justify-content: center;
      align-items: center; */
      /* background-color: rgb(255, 255, 255); */
  }
  #rhr-middle-left{
      width: 44%;
      height: 100%;
      align-items: center;
      background-color: rgb(255, 255, 255);
      background: #FFFFFF;
      box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
      border-radius: 20px;
  }
  
  #rhr-middle-right{
      margin-left: 2%;
      width: 42%;
      height: 100%;
      /* background-color: rgb(0, 0, 0); */
      display: flex; 
      flex-direction: column;
      justify-content: space-between;
      /* align-items: center; */
  }
  
  #btn-rhr {
    width: 70%;
    height: 35px;
    border: none;
    color: rgb(255, 255, 255);
    font-weight: 600;
    background: #3695be;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 12px;
    margin-bottom: 1rem;
  }
  #btn-rhr:hover {
    width: 70%;
    height: 35px;
    border: none;
    color: #3695be;
    border: solid 2px #3695be;
    font-weight: 600;
    background: rgb(255, 255, 255);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 12px;
  }
  #rhr-middle-left-btn-up{
    height: 70%;
  }
  #rhrchart {
    width: 150%;
    height: 90%;
    margin-top: 1rem;
    /* margin: auto; */
  }
  #rhr-middle-right-div{
    width: 100%;
    height: 28%;
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
  
    /* display: flex; 
    flex-direction: column;
    justify-content: space-between; */
  }
  </style>