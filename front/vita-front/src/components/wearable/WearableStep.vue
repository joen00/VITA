<template>
  <div>
    <ComponentHeader
      :ComponentHeaderTitle="ComponentHeaderTitle"
      :ComponentHeaderContent="ComponentHeaderContent"
    />
    <div id="step-middle">
      <div id="step-middle-left-btn">
        <div id="step-middle-left-btn-up">
          <input type="radio" name="step-period" id="step-week" value="week" v-on:click="weekStep" checked>
          <label for="step-week">1주</label>

          <input type="radio" name="step-period" id="step-month" value="month" v-on:click="monthStep">
          <label for="step-month">1개월</label>

          <input type="radio" name="step-period" id="step-year" value="year" v-on:click="yearStep">
          <label for="step-year">1년</label>
        </div>
        <div id="step-middle-left-btn-down">
          <div style="font-size: 18px; font-weight: 800; color: #5b5a63">
            걸음수
          </div>
          <div style="font-size: 18px; font-weight: 800; color: #5b5a63">
            종합점수
          </div>
          <div style="
              font-size: 60px;
              margin-top: -0.8rem;
              font-weight: 800;
              color: #5b5a63;">
            80
          </div>
        </div>
      </div>
      <div id="step-middle-left">
        <!-- 그래프 그려지는 곳 -->
        <!-- <div id="stepchart"></div> -->
        <StepChart :key="componentKey" :data="data" :timeUnit="timeUnit"/>
      </div>
      <!-- 오른쪽 과거와의 비교 -->
      <div id="step-middle-right">
        <!-- 저번주 -->
        <div id="step-middle-right-div">
          <div v-if="past.weekNowWearableStep < past.weekPastWearableStep">
            <p id="step-middle-right-div-h">이번 주 평균 걸음수가 지난주보다 줄었습니다</p>
          </div>
          <div v-else-if="past.weekNowWearableStep == past.weekPastWearableStep">
            <p id="step-middle-right-div-h">이번 주 평균 걸음수는 지난주와 동일합니다</p>
          </div>
          <div v-else>
            <p id="step-middle-right-div-h">이번 주 평균 걸음수가 지난주보다 늘었습니다</p>
          </div>
          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">이번 주</span>
            <div class="progress col-9 px-0" id="weekNowWearableStep">
              <div
                id="walk-week-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekNowWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekNowPersent }"
              >
                <span id="walk-now-progress-p" class="sr-only">{{
                  past.weekNowWearableStep
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">지난주</span>
            <div class="progress col-9 px-0" id="weekPastWearableStep">
              <div
                id="walk-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekPastWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekPastPersent }"
              >
                <span id="walk-progress-p" class="sr-only">{{
                  past.weekPastWearableStep
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="step-middle-right-div">
          <!-- 전달 -->
          <div v-if="past.monthNowWearableStep < past.monthPastWearableStep">
            <p id="step-middle-right-div-h">이번 달 평균 걸음수가 지난달보다 줄었습니다</p>
          </div>
          <div v-else-if="past.monthNowWearableStep == past.monthPastWearableStep">
            <p id="step-middle-right-div-h">이번 달 평균 걸음수는 지난달과 동일합니다</p>
          </div>
          <div v-else>
            <p id="step-middle-right-div-h">이번 달 평균 걸음수가 지난달보다 늘었습니다</p>
          </div>
          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">이번 달</span>
            <div class="progress col-9 px-0" id="monthNowWearableStep">
              <div
                id="walk-month-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthNowWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthNowPersent }"
              >
                <span id="walk-now-progress-p" class="sr-only">{{
                  past.monthNowWearableStep
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">지난달</span>
            <div class="progress col-9 px-0" id="monthPastWearableStep">
              <div
                id="walk-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthPastWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthPastPersent }"
              >
                <span id="walk-progress-p" class="sr-only">{{
                  past.monthPastWearableStep
                }}</span>
              </div>
            </div>
          </div>
        </div>
        <!-- 작년 -->
        <div id="step-middle-right-div">
          <div v-if="past.yearNowWearableStep < past.yearPastWearableStep">
            <p id="step-middle-right-div-h">올해 평균 걸음수가 작년보다 줄었습니다</p>
          </div>
          <div v-else-if="past.yearNowWearableStep == past.yearPastWearableStep">
            <p id="step-middle-right-div-h">올해 평균 걸음수는 작년과 동일합니다</p>
          </div>
          <div v-else>
            <p id="step-middle-right-div-h">올해 평균 걸음수가 작년보다 늘었습니다</p>
          </div>

          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">올해</span>
            <div class="progress col-9 px-0" id="yearNowWearableStep">
              <div
                id="walk-year-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearNowWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearNowPersent }"
              >
                <span id="walk-now-progress-p" class="sr-only">{{
                  past.yearNowWearableStep
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="step-middle-right-div-p" class="col-2">작년</span>
            <div class="progress col-9 px-0" id="yearPastWearableStep">
              <div
                id="walk-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearPastWearableStep"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearPastPersent }"
              >
                <span id="walk-progress-p" class="sr-only">{{
                  past.yearPastWearableStep
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
import StepChart from "@/components/wearable/StepChart.vue";
import { mapGetters } from "vuex";

export default {
  name: "WearableStep",

  components: {
    ComponentHeader,
    StepChart,
  },

  data: () => ({
    ComponentHeaderTitle: "걸음수",
    ComponentHeaderContent: "나의 걸음수 기록을 보여줘요.",
    data: [],
    past: [],
    timeUnit: "",
    componentKey: 0,
  }),

  // 데이터 가져오는 곳
  created() {
    this.weekStep();
    this.pastAndNowStep();
  },

  computed: {
    ...mapGetters(["token", "user"]),
  },

  methods: {
    async weekStep() {
      await axios
        .get(this.$store.state.url + "step/daily", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            // return {"Step": e.dailyWearableStep, "date": e.date.slice(0,10)};
            return { Step: e.dailyWearableStep, date: e.date };
          });
          // this.data = res.data
        });
      this.timeUnit = "day";
      this.componentKey += 1;
    },
    async monthStep() {
      await axios
        .get(this.$store.state.url + "step/weekly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          // this.data = res.data
          this.data = res.data.map(function (e) {
            // return {"Step": e.weeklyWearableStep, "date": e.date.slice(0,10)};
            return { Step: e.weeklyWearableStep, date: e.date };
          });
        });
      this.timeUnit = "week";
      this.componentKey += 1;
    },
    async yearStep() {
      await axios
        .get(this.$store.state.url + "step/monthly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data;
          this.data = res.data.map(function (e) {
            return { Step: e.monthlyWearableStep, date: e.date };
          });
          this.timeUnit = "month";
          this.componentKey += 1;
        });
    },
    async pastAndNowStep() {
      await axios
        .get(this.$store.state.url + "step/past", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.past = res.data;
          if (this.past.weekPastWearableStep >= this.past.weekNowWearableStep) {
            this.past["weekNowPersent"] =
              (this.past.weekNowWearableStep / this.past.weekPastWearableStep) *
              100 +
              "%";
            this.past["weekPastPersent"] = 100 + "%";
          } else {
            this.past["weekNowPersent"] = 100 + "%";
            this.past["weekPastPersent"] =
              (this.past.weekPastWearableStep / this.past.weekNowWearableStep) *
                100 +
              "%";
          }
          if (
            this.past.monthPastWearableStep >= this.past.monthNowWearableStep
          ) {
            this.past["monthNowPersent"] =
              (this.past.monthNowWearableStep /
                this.past.monthPastWearableStep) *
                100 +
              "%";
            this.past["monthPastPersent"] = 100 + "%";
          } else {
            this.past["monthNowPersent"] = 100 + "%";
            this.past["monthPastPersent"] =
              (this.past.monthPastWearableStep /
                this.past.monthNowWearableStep) *
                100 +
              "%";
          }
          if (this.past.yearPastWearableStep >= this.past.yearNowWearableStep) {
            this.past["yearNowPersent"] =
              (this.past.yearNowWearableStep / this.past.yearPastWearableStep) *
                100 +
              "%";
            this.past["yearPastPersent"] = 100 + "%";
          } else {
            this.past["yearNowPersent"] = 100 + "%";
            this.past["yearPastPersent"] =
              (this.past.yearPastWearableStep / this.past.yearNowWearableStep) *
                100 +
              "%";
          }
        });
    },
  },
};
</script>

<style scoped>
#walk-week-now-progess {
  background: #3027e5;
  border-radius: 10px;
}

#walk-month-now-progess {
  background: #27e58a;
  border-radius: 10px;
}

#walk-now-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
}

#walk-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
  color: black;
}

#walk-year-now-progess {
  background: #f33249;
  border-radius: 10px;
}

#walk-past-progess {
  background: #d0d6e5;
  border-radius: 10px;
}

#step-middle-right-div-p {
  font-size: 1rem;
  font-weight: 800;
  margin-left: -1rem;
}
#step-middle-right-div-h {
  font-size: 1rem;
  font-weight: 800;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

#step-middle {
  height: 400px;
  /* background-color: aqua; */
  margin: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#step-middle-left-btn {
  width: 12%;
  height: 100%;
  /* display: flex; */
  /* justify-content: center;
    align-items: center; */
  /* background-color: rgb(255, 255, 255); */
}
#step-middle-left {
  width: 44%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
}

#step-middle-right {
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

#step-middle-left-btn-up {
  height: 70%;
}
#stepchart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#step-middle-right-div {
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