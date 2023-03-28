<template>
  <div>
    <ComponentHeader
      :ComponentHeaderTitle="ComponentHeaderTitle"
      :ComponentHeaderContent="ComponentHeaderContent"
    />
    <div id="stress-middle">
      <div id="stress-middle-left-btn">
        <div id="stress-middle-left-btn-up">
          <button id="btn-stress" v-on:click="weekStress">1주</button>
          <br />
          <button id="btn-stress" v-on:click="monthStress">1개월</button>
          <br />
          <button id="btn-stress" v-on:click="yearStress">1년</button>
        </div>
        <div id="stress-middle-left-btn-down">
          <div style="font-size: 18px; font-weight: 800; color: #5b5a63">
            스트레스
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
            80
          </div>
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
            <p id="stress-middle-right-div-h">
              저번주가 더 스트레스이 높습니다
            </p>
          </div>
          <div v-else>
            <p id="stress-middle-right-div-h">
              이번주가 더 스트레스이 높습니다
            </p>
          </div>
          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">이번주</span>
            <div class="progress col-9 px-0" id="weekNowWearableStress">
              <div
                id="stress-week-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekNowWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekNowPersent }"
              >
                <span id="stress-now-progress-p" class="sr-only">{{
                  past.weekNowWearableStress
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">저번주</span>
            <div class="progress col-9 px-0" id="weekPastWearableStress">
              <div
                id="stress-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekPastWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekPastPersent }"
              >
                <span id="stress-progress-p" class="sr-only">{{
                  past.weekPastWearableStress
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="stress-middle-right-div">
          <div
            v-if="past.monthNowWearableStress < past.monthPastWearableStress"
          >
            <p id="stress-middle-right-div-h">전달이 더 스트레스이 높습니다</p>
          </div>
          <div v-else>
            <p id="stress-middle-right-div-h">
              이번달이 더 스트레스이 높습니다
            </p>
          </div>
          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">이번달</span>
            <div class="progress col-9 px-0" id="monthNowWearableStress">
              <div
                id="stress-month-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthNowWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthNowPersent }"
              >
                <span id="stress-now-progress-p" class="sr-only">{{
                  past.monthNowWearableStress
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">저번달</span>
            <div class="progress col-9 px-0" id="monthPastWearableStress">
              <div
                id="stress-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthPastWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthPastPersent }"
              >
                <span id="stress-progress-p" class="sr-only">{{
                  past.monthPastWearableStress
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="stress-middle-right-div">
          <div v-if="past.yearNowWearableStress < past.yearPastWearableStress">
            <p id="stress-middle-right-div-h">작년에 더 스트레스이 높습니다</p>
          </div>
          <div v-else>
            <p id="stress-middle-right-div-h">올해에 더 스트레스이 높습니다</p>
          </div>
          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">올해</span>
            <div class="progress col-9 px-0" id="yearNowWearableStress">
              <div
                id="stress-year-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearNowWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearNowPersent }"
              >
                <span id="stress-now-progress-p" class="sr-only">{{
                  past.yearNowWearableStress
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="stress-middle-right-div-p" class="col-2">작년</span>
            <div class="progress col-9 px-0" id="yearPastWearableStress">
              <div
                id="stress-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearPastWearableStress"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearPastPersent }"
              >
                <span id="stress-progress-p" class="sr-only">{{
                  past.yearPastWearableStress
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
import StressChart from "@/components/wearable/StressChart.vue";

export default {
  name: "Wearablestress",
  components: {
    ComponentHeader,
    StressChart,
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
    this.pastAndNowStress();
  },
  methods: {
    async weekStress() {
      await axios
        .get(this.$store.state.url + "stress/daily", {
          headers: {
            "Content-Type": "application/json",
            token: this.$store.state.test_token,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            return { Stress: e.dailyWearableStress, date: e.date };
          });
          // this.data = res.data
        });
      this.componentKey += 1;
    },
    async monthStress() {
      await axios
        .get(this.$store.state.url + "stress/weekly", {
          headers: {
            "Content-Type": "application/json",
            token: this.$store.state.test_token,
          },
        })
        .then((res) => {
          // this.data = res.data
          this.data = res.data.map(function (e) {
            return { Stress: e.weeklyWearableStress, date: e.date };
          });
        });
      this.componentKey += 1;
    },
    async yearStress() {
      await axios
        .get(this.$store.state.url + "stress/monthly", {
          headers: {
            "Content-Type": "application/json",
            token: this.$store.state.test_token,
          },
        })
        .then((res) => {
          this.data = res.data;
          this.data = res.data.map(function (e) {
            return { Stress: e.monthlyWearableStress, date: e.date };
          });
          this.componentKey += 1;
        });
    },
    async pastAndNowStress() {
      await axios
        .get(this.$store.state.url + "stress/past", {
          headers: {
            "Content-Type": "application/json",
            token: this.$store.state.test_token,
          },
        })
        .then((res) => {
          this.past = res.data;
          console.log(this.past);
          if (
            this.past.weekPastWearableStress >= this.past.weekNowWearableStress
          ) {
            this.past["weekNowPersent"] =
              (this.past.weekNowWearableStress /
                this.past.weekPastWearableStress) *
              100;
            this.past["weekPastPersent"] = 100 + "%";
          } else {
            this.past["weekNowPersent"] = 100 + "%";
            this.past["weekPastPersent"] =
              (this.past.weekPastWearableStress /
                this.past.weekNowWearableStress) *
                100 +
              "%";
          }
          if (
            this.past.monthPastWearableStress >=
            this.past.monthNowWearableStress
          ) {
            this.past["monthNowPersent"] =
              (this.past.monthNowWearableStress /
                this.past.monthPastWearableStress) *
                100 +
              "%";
            this.past["monthPastPersent"] = 100 + "%";
          } else {
            this.past["monthNowPersent"] = 100 + "%";
            this.past["monthPastPersent"] =
              (this.past.monthPastWearableStress /
                this.past.monthNowWearableStress) *
                100 +
              "%";
          }
          if (
            this.past.yearPastWearableStress >= this.past.yearNowWearableStress
          ) {
            this.past["yearNowPersent"] =
              (this.past.yearNowWearableStress /
                this.past.yearPastWearableStress) *
                100 +
              "%";
            this.past["yearPastPersent"] = 100 + "%";
          } else {
            this.past["yearNowPersent"] = 100 + "%";
            this.past["yearPastPersent"] =
              (this.past.yearPastWearableStress /
                this.past.yearNowWearableStress) *
                100 +
              "%";
          }
        });
    },
  },
};
</script>

<style>
#stress-month-now-progess {
  background: #27e58a;
  border-radius: 10px;
}

#stress-now-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
}

#stress-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
  color: black;
}

#stress-year-now-progess {
  background: #f33249;
  border-radius: 10px;
}

#stress-past-progess {
  background: #d0d6e5;
  border-radius: 10px;
}

#stress-middle-right-div-p {
  font-size: 1rem;
  font-weight: 800;
  margin-left: -1rem;
}
#stress-middle-right-div-h {
  font-size: 1rem;
  font-weight: 800;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

#stress-middle {
  height: 400px;
  /* background-color: aqua; */
  margin: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#stress-middle-left-btn {
  width: 12%;
  height: 100%;
  /* display: flex; */
  /* justify-content: center;
  align-items: center; */
  /* background-color: rgb(255, 255, 255); */
}
#stress-middle-left {
  width: 44%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
}

#stress-middle-right {
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
#stress-middle-left-btn-up {
  height: 70%;
}
#stresschart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#stress-middle-right-div {
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