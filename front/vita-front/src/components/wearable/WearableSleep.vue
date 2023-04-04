<template>
    <div>
      <ComponentHeader
        :ComponentHeaderTitle="ComponentHeaderTitle"
        :ComponentHeaderContent="ComponentHeaderContent"
      />
      <div id="sleep-middle">
        <div id="sleep-middle-left-btn">
          <div id="sleep-middle-left-btn-up">
            <input type="radio" name="sleep-period" id="sleep-week" value="week" v-on:click="weekSleep" checked>
            <label for="sleep-week">1주</label>

            <input type="radio" name="sleep-period" id="sleep-month" value="month" v-on:click="monthSleep">
            <label for="sleep-month">1개월</label>

            <input type="radio" name="sleep-period" id="sleep-year" value="year" v-on:click="yearSleep">
            <label for="sleep-year">1년</label>
            <div>
            <img @click="info()" style="width: 18%" src="@/../public/wearable/question.png" />
          </div>
          </div>
          <div id="sleep-middle-left-btn-down">
            <div style="font-size: 18px; font-sleep: 800; color: #5b5a63">
              수면
            </div>
            <div style="font-size: 18px; font-sleep: 800; color: #5b5a63">
              종합점수
            </div>
            <div
              style="
                font-size: 60px;
                margin-top: -0.8rem;
                font-sleep: 800;
                color: #5b5a63;
              "
            >
              80
            </div>
          </div>
        </div>
        <div id="sleep-middle-left">
          <!-- 그래프 그려지는 곳 -->
          <!-- <div id="sleepchart"></div> -->
            <div v-if="isdaily">
                <SleepDailyChart :key="componentKey" :data="data" />
            </div>
            <div v-else>
                <SleepElseChart :key="componentKey" :awakedata="awakedata" :remdata="remdata" :deepdata="deepdata" :lightdata="lightdata" :date="date" />
            </div>
            <div v-if="infovalue" id="sleep-middle-left-child">여기</div>
        </div>
        <div id="sleep-middle-right">
          <div id="sleep-middle-right-div">
            <div v-if="past.weekNowWearableSleep < past.weekPastWearableSleep">
              <p id="sleep-middle-right-div-h">이번 주 평균 수면시간이 지난주보다 감소했습니다</p>
            </div>
            <div v-else-if="past.weekNowWearableSleep == past.weekPastWearableSleep">
              <p id="sleep-middle-right-div-h">이번 주 평균 수면시간은 지난주와 동일합니다</p>
            </div>
            <div v-else>
              <p id="sleep-middle-right-div-h">이번 주 평균 수면시간이 지난주보다 증가했습니다</p>
            </div>
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">이번 주</span>
              <div class="progress col-9 px-0" id="weekNowWearableSleep">
                <div
                  id="sleep-week-now-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.weekNowWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.weekNowPersent }"
                >
                  <span id="sleep-now-progress-p" class="sr-only">{{
                    past.weekNowWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
  
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">지난주</span>
              <div class="progress col-9 px-0" id="weekPastWearableSleep">
                <div
                  id="sleep-past-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.weekPastWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.weekPastPersent }"
                >
                  <span id="sleep-progress-p" class="sr-only">{{
                    past.weekPastWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
          </div>
  
          <div id="sleep-middle-right-div">
            <div v-if="past.monthNowWearableSleep < past.monthPastWearableSleep">
              <p id="sleep-middle-right-div-h">이번 달 평균 수면시간이 지난달보다 감소했습니다</p>
            </div>
            <div v-else-if="past.monthNowWearableSleep == past.monthPastWearableSleep">
              <p id="sleep-middle-right-div-h">이번 달 평균 수면시간은 지난달과 동일합니다</p>
            </div>
            <div v-else>
              <p id="sleep-middle-right-div-h">이번 달 평균 수면시간이 지난달보다 증가했습니다</p>
            </div>
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">이번 달</span>
              <div class="progress col-9 px-0" id="monthNowWearableSleep">
                <div
                  id="sleep-month-now-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.monthNowWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.monthNowPersent }"
                >
                  <span id="sleep-now-progress-p" class="sr-only">{{
                    past.monthNowWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
  
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">지난달</span>
              <div class="progress col-9 px-0" id="monthPastWearableSleep">
                <div
                  id="sleep-past-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.monthPastWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.monthPastPersent }"
                >
                  <span id="sleep-progress-p" class="sr-only">{{
                    past.monthPastWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
          </div>
  
          <div id="sleep-middle-right-div">
            <div v-if="past.yearNowWearableSleep < past.yearPastWearableSleep">
              <p id="sleep-middle-right-div-h">올해 평균 수면시간이 작년보다 감소했습니다</p>
            </div>
            <div v-else-if="past.yearNowWearableSleep == past.yearPastWearableSleep">
              <p id="sleep-middle-right-div-h">올해 평균 수면시간은 작년과 동일합니다</p>
            </div>
            <div v-else>
              <p id="sleep-middle-right-div-h">올해 평균 수면시간이 작년보다 증가했습니다</p>
            </div>
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">올해</span>
              <div class="progress col-9 px-0" id="yearNowWearableSleep">
                <div
                  id="sleep-year-now-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.yearNowWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.yearNowPersent }"
                >
                  <span id="sleep-now-progress-p" class="sr-only">{{
                    past.yearNowWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
            <div class="row">
              <span id="sleep-middle-right-div-p" class="col-2">작년</span>
              <div class="progress col-9 px-0" id="yearPastWearableSleep">
                <div
                  id="sleep-past-progess"
                  class="progress-bar"
                  role="progressbar"
                  :aria-valuenow="past.yearPastWearableSleep"
                  aria-valuemin="0"
                  aria-valuemax="100"
                  :style="{ width: past.yearPastPersent }"
                >
                  <span id="sleep-progress-p" class="sr-only">{{
                    past.yearPastWearableSleep
                  }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
  
<script>
  import axios from "axios";
  import ComponentHeader from "@/components/common/ComponentHeader.vue";
  import SleepElseChart from "@/components/wearable/SleepElseChart.vue";
  import SleepDailyChart from "./SleepDailyChart.vue";
  import { mapGetters } from "vuex";
  
  export default {
    name: "Wearablesleep",
    components: {
      ComponentHeader,
      SleepElseChart,
      SleepDailyChart,

    },
  
    data: () => ({
      ComponentHeaderTitle: "수면",
      ComponentHeaderContent: "나의 수면 기록을 보여줘요.",
      data: [],
      awakedata: [],
      lightdata: [],
      remdata: [],
      deepdata: [],
      date: [],
      past: [],
      isdaily: true,
      infovalue:false,
      componentKey: 0,
    }),
  
    // 데이터 가져오는 곳
    created() {
      this.weekSleep();
      this.pastAndNowSleep();
    },
  
    computed: {
      ...mapGetters(["token", "user"]),
    },
    
  methods: {
    info() { 
          if (this.infovalue == true) {
            this.infovalue = false;
          } else { 
            this.infovalue = true;
          }
          console.log(this.infovalue)
    },
      async weekSleep() {
        await axios
          .get(this.$store.state.url + "sleep/daily", {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${this.token}`,
            },
          })
          .then((res) => {
            this.data = res.data
            this.componentKey += 1;
            this.isdaily = true;
          });
      },
      async monthSleep() {
        await axios
          .get(this.$store.state.url + "sleep/weekly", {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${this.token}`,
            },
          })
          .then((res) => {
            this.awakedata = res.data.map(function (e) {
              return e.weeklyWearableAwake;
              }
            );
            this.lightdata = res.data.map(function (e) {
              return e.weeklyWearableLight;
              }
            );
            this.remdata = res.data.map(function (e) {
              return e.weeklyWearableRem;
              }
            );
            this.deepdata = res.data.map(function (e) {
              return e.weeklyWearableDeep;
              }
            );
            this.date = res.data.map(function (e) {
              return e.date;
            });
            this.componentKey += 1;
            this.isdaily = false;
          })
      },
      async yearSleep() {
        await axios
          .get(this.$store.state.url + "sleep/monthly", {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${this.token}`,
            },
          })
          .then((res) => {
            this.awakedata = res.data.map(function (e) {
              return e.monthlyWearableAwake;
              }
            );
            this.lightdata = res.data.map(function (e) {
              return e.monthlyWearableLight;
              }
            );
            this.remdata = res.data.map(function (e) {
              return e.monthlyWearableRem;
              }
            );
            this.deepdata = res.data.map(function (e) {
              return e.monthlyWearableDeep;
              }
            );
            this.date = res.data.map(function (e) {
              return e.date;
            });
            this.componentKey += 1;
            this.isdaily = false;
          });
      },
      async pastAndNowSleep() {
        await axios
          .get(this.$store.state.url + "sleep/past", {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${this.token}`,
            },
          })
          .then((res) => {
            this.past = res.data;
            if (this.past.weekPastWearableSleep >= this.past.weekNowWearableSleep) {
              this.past["weekNowPersent"] =
                (this.past.weekNowWearableSleep / this.past.weekPastWearableSleep) *
                100 +
              "%";
              this.past["weekPastPersent"] = 100 + "%";
            } else {
              this.past["weekNowPersent"] = 100 + "%";
              this.past["weekPastPersent"] =
                (this.past.weekPastWearableSleep / this.past.weekNowWearableSleep) *
                  100 +
                "%";
            }
            if (this.past.monthPastWearableSleep >= this.past.monthNowWearableSleep) {
              this.past["monthNowPersent"] =
                (this.past.monthNowWearableSleep / this.past.monthPastWearableSleep) *
                  100 +
                "%";
              this.past["monthPastPersent"] = 100 + "%";
            } else {
              this.past["monthNowPersent"] = 100 + "%";
              this.past["monthPastPersent"] =
                (this.past.monthPastWearableSleep / this.past.monthNowWearableSleep) *
                  100 +
                "%";
            }
            if (this.past.yearPastWearableSleep >= this.past.yearNowWearableSleep) {
              this.past["yearNowPersent"] =
                (this.past.yearNowWearableSleep / this.past.yearPastWearableSleep) *
                  100 +
                "%";
              this.past["yearPastPersent"] = 100 + "%";
            } else {
              this.past["yearNowPersent"] = 100 + "%";
              this.past["yearPastPersent"] =
                (this.past.yearPastWearableSleep / this.past.yearNowWearableSleep) *
                  100 +
                "%";
            }
          });
      },
    },
  };
  </script>
  
<style>
#sleep-week-now-progess {
  background: #3027e5;
  border-radius: 10px;
}

#sleep-month-now-progess {
  background: #27e58a;
  border-radius: 10px;
}

#sleep-now-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
}

#sleep-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
  color: black;
}

#sleep-year-now-progess {
  background: #f33249;
  border-radius: 10px;
}

#sleep-past-progess {
  background: #d0d6e5;
  border-radius: 10px;
}

#sleep-middle-right-div-p {
  font-size: 1rem;
  font-weight: 800;
  margin-left: -1rem;
}
#sleep-middle-right-div-h {
  font-size: 1rem;
  font-weight: 800;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

#sleep-middle {
  height: 400px;
  /* background-color: aqua; */
  margin: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#sleep-middle-left-btn {
  width: 12%;
  height: 100%;
  /* display: flex; */
  /* justify-content: center;
  align-items: center; */
  /* background-color: rgb(255, 255, 255); */
}
#sleep-middle-left {
  width: 44%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  position: relative;
}
#sleep-middle-left-child{
  width: 100%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  /* 부모 */
  position: absolute;
    left: 0;
    top: 0;
    z-index: 9;
    opacity: 0.45;
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-direction: column;
    font-size: 1.2rem;
    padding: 4rem;
    background-color: #bbe5f7;
    color:black;
}


#sleep-middle-right {
  margin-left: 2%;
  width: 42%;
  height: 100%;
  /* background-color: rgb(0, 0, 0); */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /* align-items: center; */
}

input[type="radio"] {
  display: none;
}

input[type="radio"] + label {
  display: inline-block;
  width: 70%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-bottom: 1rem;
  text-align: center;
  line-height: 35px;
}

input[type="radio"]:checked + label {
  width: 70%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  cursor: pointer;
}

input[type="radio"]:hover + label {
  cursor: pointer;
}

#sleep-middle-left-btn-up {
  height: 70%;
}
#sleepchart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#sleep-middle-right-div {
  width: 100%;
  height: 28%;
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;

  /* display: flex; 
flex-direction: column;
justify-content: space-between; */
}
.row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 0;
}
</style>