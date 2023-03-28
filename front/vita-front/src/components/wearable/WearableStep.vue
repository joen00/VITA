<template>
  <div>
    <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
    <div id="step-middle">
        <div id="step-middle-left-btn">
          <div id="step-middle-left-btn-up">
            <button id="btn-step" v-on:click="weekStep">1주</button>
            <br>
            <button id="btn-step" v-on:click="monthStep">1개월</button>
            <br>
            <button id="btn-step" v-on:click="yearStep">1년</button>
          </div>
          <div id="step-middle-left-btn-down">
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">걸음수</div>
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">종합점수</div>
            <div style="font-size: 60px;
                        margin-top: -0.8rem;
                        font-weight: 800;
                        color: #5B5A63;">80</div>
          </div>
        </div>
        <div id="step-middle-left">
          <!-- 그래프 그려지는 곳 -->
          <!-- <div id="stepchart"></div> -->
          <StepChart :key="componentKey" :data="data" />
        </div>
        <div id="step-middle-right">
          

          <div id="step-middle-right-div">
            <div v-if="past.weekNowWearableStep < past.weekPastWearableStep"> 
              <p>저번주가 더 걸음수가 높습니다</p>
              <p>이번주 걸음수</p>
                <div class="progress" id="weekNowWearableStep">
                  <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                    <span class="sr-only">{{ past.weekNowWearableStep }}</span>
                  </div>
                </div>
                <br>
                <span>저번주 걸음수</span>
                <div class="progress" id="weekPastWearableStep">
                  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                    <span class="sr-only">{{ past.weekPastWearableStep }}</span>
                  </div>
                </div>
              </div>
            <div v-else>  
              <p>이번주가 더 걸음수가 높습니다</p>
              <p>이번주 걸음수</p>
              <div class="progress" id="weekNowWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                  <span class="sr-only">{{ past.weekNowWearableStep }}</span>
                </div>
              </div>
              <br>
              <span>저번주 걸음수</span>
              <div class="progress" id="weekPastWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                  <span class="sr-only">{{ past.weekPastWearableStep }}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="step-middle-right-div">
            <div v-if="past.monthNowWearableStep < past.monthPastWearableStep">  
              <p>전달이 더 걸음수가 높습니다</p>
              <div class="progress" id="monthNowWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableStep }}</span>
                </div>
              </div>
              <br>
              <span>저번달 걸음수</span>
              <div class="progress" id="monthPastWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableStep}}</span>
                </div>
              </div>
            </div>
            <div v-else>
              <p> 이번달이 더 걸음수가 높습니다 </p> 
              <div class="progress" id="monthNowWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableStep }}</span>
                </div>
              </div>
              <br>
              <span>저번달 걸음수</span>
              <div class="progress" id="monthPastWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableStep}}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="step-middle-right-div">
            <div v-if="past.yearNowWearableStep < past.yearPastWearableStep"> 
              <p> 작년에 더 걸음수가 높습니다 </p>
              <p>이번해 걸음수</p>
              <div class="progress" id="yearNowWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableStep }}</span>
                </div>
              </div>
              <br>
              <span>작년 걸음수</span>
              <div class="progress" id="yearPastWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableStep }}</span>
                </div>
              </div>
            </div>
            <div v-else> 
              <p> 올해에 더 걸음수가 높습니다 </p>
              <p>이번해 걸음수</p>
              <div class="progress" id="yearNowWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableStep }}</span>
                </div>
              </div>
              <br>
              <span>작년 걸음수</span>
              <div class="progress" id="yearPastWearableStep">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableStep }}</span>
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
import StepChart from "@/components/wearable/StepChart.vue";

export default {
  name: "WearableStep",
  components: {
      ComponentHeader,
      StepChart
    },
  data: () => ({
        ComponentHeaderTitle: "걸음수",
        ComponentHeaderContent: "나의 걸음수 기록을 보여줘요.",
        data: [],
        past: [],
        componentKey: 0,
  }),
// 데이터 가져오는 곳
created() {
    this.weekStep();
    this.pastAndNowStep();
  },
  methods: {
      async weekStep() {
        await axios.get(this.$store.state.url + 'step/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          // return {"Step": e.dailyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.dailyWearableStep, "date": e.date};
        })
        // this.data = res.data
      })
      this.componentKey += 1;  
      },
      async monthStep() {
        await axios.get(this.$store.state.url + 'step/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        // this.data = res.data
        this.data = res.data.map(function(e){
          // return {"Step": e.weeklyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.weeklyWearableStep, "date": e.date};
        })
      })
      this.componentKey += 1;  
      },
      async yearStep() {
        await axios.get(this.$store.state.url + 'step/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data
        this.data = res.data.map(function(e){
          return {"Step": e.monthlyWearableStep, "date": e.date};
        })
        this.componentKey += 1; 
      })
      },
      async pastAndNowStep() {
        await axios.get(this.$store.state.url + 'step/past' , {
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
#step-middle{
    height: 400px;
    /* background-color: aqua; */
    margin: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
#step-middle-left-btn{
    width: 12%;
    height: 100%;
    /* display: flex; */
    /* justify-content: center;
    align-items: center; */
    /* background-color: rgb(255, 255, 255); */
}
#step-middle-left{
    width: 44%;
    height: 100%;
    align-items: center;
    background-color: rgb(255, 255, 255);
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
}

#step-middle-right{
    margin-left: 2%;
    width: 42%;
    height: 100%;
    /* background-color: rgb(0, 0, 0); */
    display: flex; 
    flex-direction: column;
    justify-content: space-between;
    /* align-items: center; */
}

#btn-step {
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
#btn-step:hover {
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
#step-middle-left-btn-up{
  height: 70%;
}
#stepchart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#step-middle-right-div{
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