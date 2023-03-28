<template>
    <div>
      <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
      <div id="stress-middle">
          <div id="stress-middle-left-btn">
            <div id="stress-middle-left-btn-up">
              <button id="btn-stress" v-on:click="weekStress">1주</button>
              <br>
              <button id="btn-stress" v-on:click="monthStress">1개월</button>
              <br>
              <button id="btn-stress" v-on:click="yearStress">1년</button>
            </div>
            <div id="stress-middle-left-btn-down"> 
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;">스트레스</div>
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;">종합점수</div>
              <div style="font-size: 60px;
                          margin-top: -0.8rem;
                          font-weight: 800;
                          color: #5B5A63;">80</div>
            </div>
          </div>
          <div id="stress-middle-left">
            <!-- 그래프 그려지는 곳 -->
            <!-- <div id="stresschart"></div> -->
            <stressChart :key="componentKey" :data="data" />
          </div>
          <div id="stress-middle-right">
          

          <div id="stress-middle-right-div">
            <div v-if="past.weekNowWearableStress < past.weekPastWearableStress"> 
              <p>저번주가 더 스트레스가 높습니다</p>
              <p>이번주 스트레스</p>
                <div class="progress" id="weekNowWearableStress">
                  <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                    <span class="sr-only">{{ past.weekNowWearableStress }}</span>
                  </div>
                </div>
                <br>
                <span>저번주 스트레스</span>
                <div class="progress" id="weekPastWearableStress">
                  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                    <span class="sr-only">{{ past.weekPastWearableStress }}</span>
                  </div>
                </div>
              </div>
            <div v-else>  
              <p>이번주가 더 스트레스가 높습니다</p>
              <p>이번주 스트레스</p>
              <div class="progress" id="weekNowWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                  <span class="sr-only">{{ past.weekNowWearableStress }}</span>
                </div>
              </div>
              <br>
              <span>저번주 스트레스</span>
              <div class="progress" id="weekPastWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                  <span class="sr-only">{{ past.weekPastWearableStress }}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="stress-middle-right-div">
            <div v-if="past.monthNowWearableStress < past.monthPastWearableStress">  
              <p>전달이 더 스트레스가 높습니다</p>
              <div class="progress" id="monthNowWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableStress }}</span>
                </div>
              </div>
              <br>
              <span>저번달 스트레스</span>
              <div class="progress" id="monthPastWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableStress}}</span>
                </div>
              </div>
            </div>
            <div v-else>
              <p> 이번달이 더 스트레스가 높습니다 </p> 
              <div class="progress" id="monthNowWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableStress }}</span>
                </div>
              </div>
              <br>
              <span>저번달 스트레스</span>
              <div class="progress" id="monthPastWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableStress}}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="stress-middle-right-div">
            <div v-if="past.yearNowWearableStress < past.yearPastWearableStress"> 
              <p> 작년에 더 스트레스가 높습니다 </p>
              <p>이번해 스트레스</p>
              <div class="progress" id="yearNowWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableStress }}</span>
                </div>
              </div>
              <br>
              <span>작년 스트레스</span>
              <div class="progress" id="yearPastWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableStress }}</span>
                </div>
              </div>
            </div>
            <div v-else> 
              <p> 올해에 더 스트레스가 높습니다 </p>
              <p>이번해 스트레스</p>
              <div class="progress" id="yearNowWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableStress }}</span>
                </div>
              </div>
              <br>
              <span>작년 스트레스</span>
              <div class="progress" id="yearPastWearableStress">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableStress }}</span>
                </div>
              </div>

            </div>
            
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import ComponentHeader from "@/components/common/ComponentHeader.vue";
  import StressChart from "@/components/wearable/StressChart.vue";
  
  export default {
    name: "Wearablestress",
    components: {
        ComponentHeader,
        StressChart
      },
    data: () => ({
          ComponentHeaderTitle: "스트레스",
          ComponentHeaderContent: "나의 스트레스 기록을 보여줘요.",
          data: [],
          past: [],
          componentKey: 0,
    }),
  // 데이터 가져오는 곳
    created() {
        this.weekStress();
    },
    methods: {
      async weekStress() {
        await axios.get(this.$store.state.url + 'stress/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Stress": e.dailyWearableStress, "date": e.date};
        })
        // this.data = res.data
      })
      this.componentKey += 1;  
      },
      async monthStress() {
        await axios.get(this.$store.state.url + 'stress/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        // this.data = res.data
        this.data = res.data.map(function(e){
          return {"Stress": e.weeklyWearableStress, "date": e.date};
        })
      })
      this.componentKey += 1;  
      },
      async yearStress() {
        await axios.get(this.$store.state.url + 'stress/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data
        this.data = res.data.map(function(e){
          return {"Stress": e.monthlyWearableStress, "date": e.date};
        })
        this.componentKey += 1; 
      })
      },
      async pastAndNowStress() {
        await axios.get(this.$store.state.url + 'stress/past' , {
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
  #stress-middle{
      height: 400px;
      /* background-color: aqua; */
      margin: 3rem;
      display: flex;
      justify-content: center;
      align-items: center;
  }
  #stress-middle-left-btn{
      width: 12%;
      height: 100%;
      /* display: flex; */
      /* justify-content: center;
      align-items: center; */
      /* background-color: rgb(255, 255, 255); */
  }
  #stress-middle-left{
      width: 44%;
      height: 100%;
      align-items: center;
      background-color: rgb(255, 255, 255);
      background: #FFFFFF;
      box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
      border-radius: 20px;
  }
  
  #stress-middle-right{
      margin-left: 2%;
      width: 42%;
      height: 100%;
      /* background-color: rgb(0, 0, 0); */
      display: flex; 
      flex-direction: column;
      justify-content: space-between;
      /* align-items: center; */
  }
  
  #btn-stress {
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
  #btn-stress:hover {
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
  #stress-middle-left-btn-up{
    height: 70%;
  }
  #stresschart {
    width: 150%;
    height: 90%;
    margin-top: 1rem;
    /* margin: auto; */
  }
  #stress-middle-right-div{
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