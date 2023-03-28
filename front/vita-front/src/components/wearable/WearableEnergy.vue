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
            <p>저번주가 더 활동에너지이 높습니다</p>
          </div>
          <div v-else>  
            <p>이번주가 더 활동에너지이 높습니다</p>
          </div>
          <div class="row">
            <span class="col-2">이번주</span>
            <div class="progress col-9 px-0" id="weekNowWearableEnergy">
              <div class="progress-bar" role="progressbar" :aria-valuenow="past.weekNowWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.weekNowPersent }">
              <span class="sr-only">{{ past.weekNowWearableEnergy }}</span>
              </div>
            </div>
          </div>
          
            <div class="row">
              <span class="col-2">저번주</span>
              <div class="progress col-9 px-0" id="weekPastWearableEnergy">
                <div class="progress-bar" role="progressbar" :aria-valuenow="past.weekPastWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.weekPastPersent }">
                <span class="sr-only">{{ past.weekPastWearableEnergy }}</span>
                </div>
              </div>
            </div>
          </div>


        <div id="energy-middle-right-div">
          <div v-if="past.monthNowWearableEnergy < past.monthPastWearableEnergy">  
            <p>전달이 더 활동에너지이 높습니다</p>
          </div>
          <div v-else>
            <p>이번달이 더 활동에너지이 높습니다 </p> 
          </div>
          <div class="row">
            <span class="col-2">이번달</span>
            <div class="progress col-9 px-0" id="monthNowWearableEnergy">
              <div class="progress-bar" role="progressbar" :aria-valuenow="past.monthNowWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.monthNowPersent }">
              <span class="sr-only">{{ past.monthNowWearableEnergy }}</span>
              </div>
            </div>
          </div>
          
            <div class="row">
              <span class="col-2">저번달</span>
              <div class="progress col-9 px-0" id="monthPastWearableEnergy">
                <div class="progress-bar" role="progressbar" :aria-valuenow="past.monthPastWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.monthPastPersent }">
                <span class="sr-only">{{ past.monthPastWearableEnergy }}</span>
                </div>
              </div>
            </div>
          </div>

        <div id="energy-middle-right-div">
          <div v-if="past.yearNowWearableEnergy < past.yearPastWearableEnergy"> 
            <p> 작년에 더 활동에너지이 높습니다 </p>
          </div>
          <div v-else> 
            <p> 올해에 더 활동에너지이 높습니다 </p>
          </div>
          <div class="row">
            <span class="col-2">작년</span>
            <div class="progress col-9 px-0" id="yearNowWearableEnergy">
              <div class="progress-bar" role="progressbar" :aria-valuenow="past.yearNowWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.yearNowPersent }">
              <span class="sr-only">{{ past.yearNowWearableEnergy }}</span>
              </div>
            </div>
          </div>
          
            <div class="row">
              <span class="col-2">올해</span>
              <div class="progress col-9 px-0" id="yearPastWearableEnergy">
                <div class="progress-bar" role="progressbar" :aria-valuenow="past.yearPastWearableEnergy" aria-valuemin="0" aria-valuemax="100" :style="{ width: past.yearPastPersent }">
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
        if (this.past.weekPastWearableEnergy >= this.past.weekNowWearableEnergy) {
          this.past['weekNowPersent'] = this.past.weekNowWearableEnergy / this.past.weekPastWearableEnergy * 100;
          this.past['weekPastPersent'] = 100 + "%";
        }
        else {
          this.past['weekNowPersent'] = 100 + "%"; 
          this.past['weekPastPersent'] = (this.past.weekPastWearableEnergy / this.past.weekNowWearableEnergy * 100) + "%";
        }
        if (this.past.monthPastWearableEnergy >= this.past.monthNowWearableEnergy) {
          this.past['monthNowPersent'] = (this.past.monthNowWearableEnergy / this.past.monthPastWearableEnergy * 100) + "%";
          this.past['monthPastPersent'] = 100 + "%";
        }
        else {
          this.past['monthNowPersent'] = 100 + "%"; 
          this.past['monthPastPersent'] = (this.past.monthPastWearableEnergy / this.past.monthNowWearableEnergy * 100) + "%";
        }
        if (this.past.yearPastWearableEnergy >= this.past.yearNowWearableEnergy) {
          this.past['yearNowPersent'] = (this.past.yearNowWearableEnergy / this.past.yearPastWearableEnergy * 100) + "%";
          this.past['yearPastPersent'] = 100 + "%";
        }
        else {
          this.past['yearNowPersent'] = 100 + "%"; 
          this.past['yearPastPersent'] = (this.past.yearPastWearableEnergy / this.past.yearNowWearableEnergy * 100) + "%" ;
        }
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