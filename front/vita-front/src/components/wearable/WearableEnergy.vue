<template>
  <div>
    <ComponentHeader
      :ComponentHeaderTitle="ComponentHeaderTitle"
      :ComponentHeaderContent="ComponentHeaderContent"
    />
    <div id="energy-middle">
      <div id="energy-middle-left-btn">
        <div id="energy-middle-left-btn-up">
          <input type="radio" name="energy-period" id="energy-week" value="week" v-on:click="weekEnergy" checked>
          <label for="energy-week">1주</label>

          <input type="radio" name="energy-period" id="energy-month" value="month" v-on:click="monthEnergy">
          <label for="energy-month">1개월</label>

          <input type="radio" name="energy-period" id="energy-year" value="year" v-on:click="yearEnergy">
          <label for="energy-year">1년</label>
          <div>
            <img @click="info()" style="width: 18%" src="@/../public/wearable/question.png" />
          </div>
        </div>
        <div id="energy-middle-left-btn-down">
          <div style="font-size: 18px; font-weight: 800; color: #5b5a63">
            활동에너지
          </div>
          <div style="font-size: 18px; font-weight: 800; color: #5b5a63">
            종합점수
          </div>
          <div
            style="
              font-size: 60px;
              margin-top: -0.8rem;
              font-weight: 800;
              color: #5b5a63;
            "
          >
            {{ score }}
          </div>
        </div>
      </div>
      <div id="energy-middle-left">
        <!-- 그래프 그려지는 곳 -->
        <!-- <div id="energychart"></div> -->
        <div v-if="data.length == 0">
          <img :src="require(`/public/wearable/no_data_found.png`)" id="no_data_found" width="200px">
          <p> 표시할 활동에너지 데이터가 없습니다 <br>
              tip : 충분한 걸음수 혹은 추가적인 운동 입력으로 데이터를 입력해주세요 </p>
        </div>
        <div v-else>
          <EnergyChart :key="componentKey" :data="data" />
        </div>
        
        <div v-if="infovalue" id="energy-middle-left-child">여기</div>
      </div>
      <div id="energy-middle-right">
        <div id="energy-middle-right-div">
          <div v-if="past.weekNowWearableEnergy < past.weekPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              이번 주 평균 활동에너지가 지난주보다 감소했습니다
            </p>
          </div>
          <div v-else-if="past.weekNowWearableEnergy == past.weekPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              이번 주 평균 활동에너지는 지난주와 동일합니다
            </p>
          </div>
          <div v-else>
            <p id="energy-middle-right-div-h">
              이번 주 평균 활동에너지가 지난주보다 증가했습니다
            </p>
          </div>
          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">이번 주</span>
            <div class="progress col-9 px-0" id="weekNowWearableEnergy">
              <div
                id="energy-week-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekNowWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekNowPersent }"
              >
                <span id="energy-now-progress-p" class="sr-only">{{
                  past.weekNowWearableEnergy
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">지난주</span>
            <div class="progress col-9 px-0" id="weekPastWearableEnergy">
              <div
                id="energy-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekPastWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekPastPersent }"
              >
                <span id="energy-progress-p" class="sr-only">{{
                  past.weekPastWearableEnergy
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="energy-middle-right-div">
          <div v-if="past.monthNowWearableEnergy < past.monthPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              이번 달 평균 활동에너지가 지난달보다 감소했습니다
            </p>
          </div>
          <div v-else-if="past.monthNowWearableEnergy == past.monthPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              이번 달 평균 활동에너지는 지난달과 동일합니다
            </p>
          </div>
          <div v-else>
            <p id="energy-middle-right-div-h">
              이번 달 평균 활동에너지가 지난달보다 증가했습니다
            </p>
          </div>
          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">이번 달</span>
            <div class="progress col-9 px-0" id="monthNowWearableEnergy">
              <div
                id="energy-month-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthNowWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthNowPersent }"
              >
                <span id="energy-now-progress-p" class="sr-only">{{
                  past.monthNowWearableEnergy
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">지난달</span>
            <div class="progress col-9 px-0" id="monthPastWearableEnergy">
              <div
                id="energy-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthPastWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthPastPersent }"
              >
                <span id="energy-progress-p" class="sr-only">{{
                  past.monthPastWearableEnergy
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="energy-middle-right-div">
          <div v-if="past.yearNowWearableEnergy < past.yearPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              올해 평균 활동에너지가 작년보다 감소했습니다
            </p>
          </div>
          <div v-else-if="past.yearNowWearableEnergy == past.yearPastWearableEnergy">
            <p id="energy-middle-right-div-h">
              올해 평균 활동에너지는 작년과 동일합니다
            </p>
          </div>
          <div v-else>
            <p id="energy-middle-right-div-h">
              올해 평균 활동에너지가 작년보다 증가했습니다
            </p>
          </div>
          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">올해</span>
            <div class="progress col-9 px-0" id="yearNowWearableEnergy">
              <div
                id="energy-year-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearNowWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearNowPersent }"
              >
                <span id="energy-now-progress-p" class="sr-only">{{
                  past.yearNowWearableEnergy
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="energy-middle-right-div-p" class="col-2">작년</span>
            <div class="progress col-9 px-0" id="yearPastWearableEnergy">
              <div
                id="energy-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearPastWearableEnergy"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearPastPersent }"
              >
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
import axios from "axios";
import ComponentHeader from "@/components/common/ComponentHeader.vue";
import EnergyChart from "@/components/wearable/EnergyChart.vue";
import { mapGetters } from "vuex";

export default {
  name: "Wearableenergy",
  components: {
    ComponentHeader,
    EnergyChart,
  },

  data: () => ({
    ComponentHeaderTitle: "활동에너지",
    ComponentHeaderContent: "나의 걸음수 기록을 보여줘요.",
    data: [],
    past: [],
    // 설명란
infovalue:false,
    componentKey: 0,
  }),

  props: {
    score: Number,
  },

  // 데이터 가져오는 곳
  created() {
    this.weekEnergy();
    this.pastAndNowEnergy();
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
    async weekEnergy() {
      await axios
        .get(this.$store.state.url + "energy/daily", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            return { Energy: e.dailyWearableEnergy, date: e.date };
          });
        });
      this.componentKey += 1;
    },
    async monthEnergy() {
      await axios
        .get(this.$store.state.url + "energy/weekly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            return { Energy: e.weeklyWearableEnergy, date: e.date };
          });
        });
      this.componentKey += 1;
    },
    async yearEnergy() {
      await axios
        .get(this.$store.state.url + "energy/monthly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            return { Energy: e.monthlyWearableEnergy, date: e.date };
          });
        });
      this.componentKey += 1;
    },
    async pastAndNowEnergy() {
      await axios
        .get(this.$store.state.url + "energy/past", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.past = res.data;
          if (
            this.past.weekPastWearableEnergy >= this.past.weekNowWearableEnergy
          ) {
            this.past["weekNowPersent"] =
              (this.past.weekNowWearableEnergy /
                this.past.weekPastWearableEnergy) *
                100 +
              "%";
            this.past["weekPastPersent"] = 100 + "%";
          } else {
            this.past["weekNowPersent"] = 100 + "%";
            this.past["weekPastPersent"] =
              (this.past.weekPastWearableEnergy /
                this.past.weekNowWearableEnergy) *
                100 +
              "%";
          }
          if (
            this.past.monthPastWearableEnergy >=
            this.past.monthNowWearableEnergy
          ) {
            this.past["monthNowPersent"] =
              (this.past.monthNowWearableEnergy /
                this.past.monthPastWearableEnergy) *
                100 +
              "%";
            this.past["monthPastPersent"] = 100 + "%";
          } else {
            this.past["monthNowPersent"] = 100 + "%";
            this.past["monthPastPersent"] =
              (this.past.monthPastWearableEnergy /
                this.past.monthNowWearableEnergy) *
                100 +
              "%";
          }
          if (
            this.past.yearPastWearableEnergy >= this.past.yearNowWearableEnergy
          ) {
            this.past["yearNowPersent"] =
              (this.past.yearNowWearableEnergy /
                this.past.yearPastWearableEnergy) *
                100 +
              "%";
            this.past["yearPastPersent"] = 100 + "%";
          } else {
            this.past["yearNowPersent"] = 100 + "%";
            this.past["yearPastPersent"] =
              (this.past.yearPastWearableEnergy /
                this.past.yearNowWearableEnergy) *
                100 +
              "%";
          }
        });
    },
  },
};
</script>

<style scoped>
#energy-week-now-progess {
  background: #3027e5;
  border-radius: 10px;
}

#energy-month-now-progess {
  background: #27e58a;
  border-radius: 10px;
}

#energy-now-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
}

#energy-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
  color: black;
}

#energy-year-now-progess {
  background: #f33249;
  border-radius: 10px;
}

#energy-past-progess {
  background: #d0d6e5;
  border-radius: 10px;
}

#energy-middle-right-div-p {
  font-size: 1rem;
  font-weight: 800;
  margin-left: -1rem;
}
#energy-middle-right-div-h {
  font-size: 1rem;
  font-weight: 800;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

#energy-middle {
  height: 400px;
  /* background-color: aqua; */
  margin: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#energy-middle-left-btn {
  width: 12%;
  height: 100%;
  /* display: flex; */
  /* justify-content: center;
  align-items: center; */
  /* background-color: rgb(255, 255, 255); */
}
#energy-middle-left {
  width: 44%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  position: relative;
}

#energy-middle-left-child{
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

#energy-middle-right {
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

#energy-middle-left-btn-up {
  height: 70%;
}
#energychart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#energy-middle-right-div {
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