<template>
  <div>
    <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
    <div id="energy-middle">
        <div id="energy-middle-left-btn">
          <div id="energy-middle-left-btn-up">
            <button id="btn-energy" v-on:click="weekEnergy">1주</button>
            <br>
            <button id="btn-energy" v-on:click="monthEnergy">1개월</button>
            <br>
            <button id="btn-energy" v-on:click="yearEnergy">1년</button>
          </div>
          <div id="energy-middle-left-btn-down"> 
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">활동에너지</div>
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">종합점수</div>
            <div style="font-size: 60px;
                        margin-top: -0.8rem;
                        font-weight: 800;
                        color: #5B5A63;">80</div>
          </div>
        </div>
        <div id="energy-middle-left">
          <!-- 그래프 그려지는 곳 -->
          <!-- <div id="energychart"></div> -->
          <EnergyChart :key="componentKey" :data="data" />
        </div>
        <div id="energy-middle-right">
          

          <div id="energy-middle-right-div">
            <div v-if="past.weekNowWearableEnergy < past.weekPastWearableEnergy"> 
              <p>저번주가 더 활동에너지가 높습니다</p>
              <p>이번주 활동에너지</p>
                <div class="progress" id="weekNowWearableEnergy">
                  <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                    <span class="sr-only">{{ past.weekNowWearableEnergy }}</span>
                  </div>
                </div>
                <br>
                <span>저번주 활동에너지</span>
                <div class="progress" id="weekPastWearableEnergy">
                  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                    <span class="sr-only">{{ past.weekPastWearableEnergy }}</span>
                  </div>
                </div>
              </div>
            <div v-else>  
              <p>이번주가 더 활동에너지가 높습니다</p>
              <p>이번주 활동에너지</p>
              <div class="progress" id="weekNowWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                  <span class="sr-only">{{ past.weekNowWearableEnergy }}</span>
                </div>
              </div>
              <br>
              <span>저번주 활동에너지</span>
              <div class="progress" id="weekPastWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%">
                  <span class="sr-only">{{ past.weekPastWearableEnergy }}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="energy-middle-right-div">
            <div v-if="past.monthNowWearableEnergy < past.monthPastWearableEnergy">  
              <p>전달이 더 활동에너지가 높습니다</p>
              <div class="progress" id="monthNowWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableEnergy }}</span>
                </div>
              </div>
              <br>
              <span>저번달 활동에너지</span>
              <div class="progress" id="monthPastWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableEnergy}}</span>
                </div>
              </div>
            </div>
            <div v-else>
              <p> 이번달이 더 활동에너지가 높습니다 </p> 
              <div class="progress" id="monthNowWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                  <span class="sr-only">{{ past.monthNowWearableEnergy }}</span>
                </div>
              </div>
              <br>
              <span>저번달 활동에너지</span>
              <div class="progress" id="monthPastWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:65%">
                  <span class="sr-only">{{past.monthPastWearableEnergy}}</span>
                </div>
              </div>
            </div>
          </div>


          <div id="energy-middle-right-div">
            <div v-if="past.yearNowWearableEnergy < past.yearPastWearableEnergy"> 
              <p> 작년에 더 활동에너지가 높습니다 </p>
              <p>이번해 활동에너지</p>
              <div class="progress" id="yearNowWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableEnergy }}</span>
                </div>
              </div>
              <br>
              <span>작년 활동에너지</span>
              <div class="progress" id="yearPastWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableEnergy }}</span>
                </div>
              </div>
            </div>
            <div v-else> 
              <p> 올해에 더 활동에너지가 높습니다 </p>
              <p>이번해 활동에너지</p>
              <div class="progress" id="yearNowWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:20%">
                  <span class="sr-only">{{ past.yearNowWearableEnergy }}</span>
                </div>
              </div>
              <br>
              <span>작년 활동에너지</span>
              <div class="progress" id="yearPastWearableEnergy">
                <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="100" style="width:80%">
                  <span class="sr-only">{{ past.yearPastWearableEnergy }}</span>
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
import EnergyChart from "@/components/wearable/EnergyChart.vue"

export default {
  name: "Wearableenergy",
  components: {
      ComponentHeader,
      EnergyChart
    },
  data: () => ({
        ComponentHeaderTitle: "활동에너지",
        ComponentHeaderContent: "나의 걸음수 기록을 보여줘요.",
        data: [],
        past: [],
        componentKey: 0,
  }),
// 데이터 가져오는 곳
created() {
    this.weekEnergy();
    this.pastAndNowEnergy();
  },
  methods: {
      async weekEnergy() {
        await axios.get(this.$store.state.url + 'energy/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.dailyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async monthEnergy() {
        await axios.get(this.$store.state.url + 'energy/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.weeklyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async yearEnergy() {
        await axios.get(this.$store.state.url + 'energy/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          return {"Energy": e.monthlyWearableEnergy, "date": e.date};
        })
      })
      this.componentKey += 1; 
      },
      async pastAndNowEnergy() {
        await axios.get(this.$store.state.url + 'energy/past' , {
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
#energy-middle{
    height: 400px;
    /* background-color: aqua; */
    margin: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
#energy-middle-left-btn{
    width: 12%;
    height: 100%;
    /* display: flex; */
    /* justify-content: center;
    align-items: center; */
    /* background-color: rgb(255, 255, 255); */
}
#energy-middle-left{
    width: 44%;
    height: 100%;
    align-items: center;
    background-color: rgb(255, 255, 255);
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
}

#energy-middle-right{
    margin-left: 2%;
    width: 42%;
    height: 100%;
    /* background-color: rgb(0, 0, 0); */
    display: flex; 
    flex-direction: column;
    justify-content: space-between;
    /* align-items: center; */
}

#btn-energy {
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
#btn-energy:hover {
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
#energy-middle-left-btn-up{
  height: 70%;
}
#energychart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#energy-middle-right-div{
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