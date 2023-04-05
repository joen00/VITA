<template>
  <div>
    <ComponentHeader
      :ComponentHeaderTitle="ComponentHeaderTitle"
      :ComponentHeaderContent="ComponentHeaderContent"
    />
    <div id="weight-middle">
      <div id="weight-middle-left-btn">
        <div id="weight-middle-left-btn-up">
          <input type="radio" name="weight-period" id="weight-week" value="week" v-on:click="weekWeight" checked>
          <label for="weight-week">1주</label>

          <input type="radio" name="weight-period" id="weight-month" value="month" v-on:click="monthWeight">
          <label for="weight-month">1개월</label>

          <input type="radio" name="weight-period" id="weight-year" value="year" v-on:click="yearWeight">
          <label for="weight-year">1년</label>
          <div>
            <img @click="info()" style="width: 18%" src="@/../public/wearable/question.png" />
          </div>
        </div>
      </div>
      <div id="weight-middle-left">
        <!-- 그래프 그려지는 곳 -->
        <div v-if="data.length == 0" >
          <img :src="require(`/public/wearable/no_data_found.png`)" id="no_data_found" width="200px">
          <p> 표시할 체중 데이터가 없습니다 <br>
              tip : 체지방, 근육량, 체중 데이터가 정상적으로 입력되어 있어야합니다. <br>
              체성분 검사를 통해 데이터를 입력해주세요. </p>
        </div>
        <div v-else>
          <WeightChart :key="componentKey" :data="data" ></WeightChart>
        </div>
        <div v-if="infovalue" id="weight-middle-left-child">여기</div>

      </div>
      <div id="weight-middle-right">
        <div id="weight-middle-right-div">
          <div v-if="past.weekNowWearableWeight < past.weekPastWearableWeight">
            <p id="weight-middle-right-div-h">이번 주 평균 체중이 지난주보다 감소했습니다</p>
          </div>
          <div v-else-if="past.weekNowWearableWeight == past.weekPastWearableWeight">
            <p id="weight-middle-right-div-h">이번 주 평균 체중은 지난주와 동일합니다</p>
          </div>
          <div v-else>
            <p id="weight-middle-right-div-h">이번 주 평균 체중이 지난주보다 증가했습니다</p>
          </div>
          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">이번 주</span>
            <div class="progress col-9 px-0" id="weekNowWearableWeight">
              <div
                id="weight-week-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekNowWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekNowPersent }"
              >
                <span id="weight-now-progress-p" class="sr-only">{{
                  past.weekNowWearableWeight
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">지난주</span>
            <div class="progress col-9 px-0" id="weekPastWearableWeight">
              <div
                id="weight-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.weekPastWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.weekPastPersent }"
              >
                <span id="weight-progress-p" class="sr-only">{{
                  past.weekPastWearableWeight
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="weight-middle-right-div">
          <div
            v-if="past.monthNowWearableWeight < past.monthPastWearableWeight"
          >
            <p id="weight-middle-right-div-h">이번 달 평균 체중이 지난달보다 감소했습니다</p>
          </div>
          <div v-else-if="past.monthNowWearableWeight == past.monthPastWearableWeight">
            <p id="weight-middle-right-div-h">이번 달 평균 체중은 지난달과 동일합니다</p>
          </div>
          <div v-else>
            <p id="weight-middle-right-div-h">이번 달 평균 체중이 지난달보다 증가했습니다</p>
          </div>
          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">이번 달</span>
            <div class="progress col-9 px-0" id="monthNowWearableWeight">
              <div
                id="weight-month-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthNowWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthNowPersent }"
              >
                <span id="weight-now-progress-p" class="sr-only">{{
                  past.monthNowWearableWeight
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">지난달</span>
            <div class="progress col-9 px-0" id="monthPastWearableWeight">
              <div
                id="weight-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.monthPastWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.monthPastPersent }"
              >
                <span id="weight-progress-p" class="sr-only">{{
                  past.monthPastWearableWeight
                }}</span>
              </div>
            </div>
          </div>
        </div>

        <div id="weight-middle-right-div">
          <div v-if="past.yearNowWearableWeight < past.yearPastWearableWeight">
            <p id="weight-middle-right-div-h">올해 평균 체중이 작년보다 감소했습니다</p>
          </div>
          <div v-else-if="past.yearNowWearableWeight == past.yearPastWearableWeight">
            <p id="weight-middle-right-div-h">올해 평균 체중은 작년과 동일합니다</p>
          </div>
          <div v-else>
            <p id="weight-middle-right-div-h">올해 평균 체중이 작년보다 증가했습니다</p>
          </div>

          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">올해</span>
            <div class="progress col-9 px-0" id="yearNowWearableWeight">
              <div
                id="weight-year-now-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearNowWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearNowPersent }"
              >
                <span id="weight-now-progress-p" class="sr-only">{{
                  past.yearNowWearableWeight
                }}</span>
              </div>
            </div>
          </div>

          <div class="row">
            <span id="weight-middle-right-div-p" class="col-2">작년</span>
            <div class="progress col-9 px-0" id="yearPastWearableWeight">
              <div
                id="weight-past-progess"
                class="progress-bar"
                role="progressbar"
                :aria-valuenow="past.yearPastWearableWeight"
                aria-valuemin="0"
                aria-valuemax="100"
                :style="{ width: past.yearPastPersent }"
              >
                <span id="weight-progress-p" class="sr-only">{{
                  past.yearPastWearableWeight
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
import ComponentHeader from "@/components/common/ComponentHeader.vue";
import WeightChart from "./WeightChart.vue";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "WearableWeight",
  components: {
    ComponentHeader,
    WeightChart,
  },

  data: () => ({
    ComponentHeaderTitle: "체중",
    ComponentHeaderContent: "나의 BMI 기록을 보여줘요.",
    data: [],
    past: {},
// 설명란
infovalue:false,
    componentKey: 0,
  }),

  // 데이터 가져오는 곳
  created() {
    this.weekWeight();
    this.pastAndNowWeight();
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
    async weekWeight() {
      await axios
        .get(this.$store.state.url + "weight/daily", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data.map(function (e) {
            // return {"Weight": e.dailyWearableWeight, "date": e.date.slice(0,10)};
            return {
              체중: e.dailyWearableWeight,
              골격근량: e.dailyWearableMuscle,
              체지방량: e.dailyWearableFat,
              day: e.date.slice(0, 10),
            };
           
          });
          
          // this.data = res.data
        });

      this.componentKey += 1;
    },
    async monthWeight() {
      await axios
        .get(this.$store.state.url + "weight/weekly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          // this.data = res.data
          this.data = res.data.map(function (e) {
            // return {"Weight": e.weeklyWearableWeight, "date": e.date.slice(0,10)};
            return {
              체중: e.weeklyWearableWeight,
              골격근량: e.weeklyWearableMuscle,
              체지방량: e.weeklyWearableFat,
              day: e.date.slice(0, 10),
            };
          });
        });

      this.componentKey += 1;
    },
    async yearWeight() {
      await axios
        .get(this.$store.state.url + "weight/monthly", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.data = res.data;
          this.data = res.data.map(function (e) {
            return {
              체중: e.monthlyWearableWeight,
              골격근량: e.monthlyWearableMuscle,
              체지방량: e.monthlyWearableFat,
              day: e.date.slice(0, 10),
            };
          });

          this.componentKey += 1;
        });
    },
    async pastAndNowWeight() {
      await axios
        .get(this.$store.state.url + "weight/past", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.past = res.data;
          if (
            this.past.weekPastWearableWeight >= this.past.weekNowWearableWeight
          ) {
            this.past["weekNowPersent"] =
              (this.past.weekNowWearableWeight /
                this.past.weekPastWearableWeight) *
              100 +
              "%";
            this.past["weekPastPersent"] = 100 + "%";
          } else {
            this.past["weekNowPersent"] = 100 + "%";
            this.past["weekPastPersent"] =
              (this.past.weekPastWearableWeight /
                this.past.weekNowWearableWeight) *
                100 +
              "%";
          }
          if (
            this.past.monthPastWearableWeight >=
            this.past.monthNowWearableWeight
          ) {
            this.past["monthNowPersent"] =
              (this.past.monthNowWearableWeight /
                this.past.monthPastWearableWeight) *
                100 +
              "%";
            this.past["monthPastPersent"] = 100 + "%";
          } else {
            this.past["monthNowPersent"] = 100 + "%";
            this.past["monthPastPersent"] =
              (this.past.monthPastWearableWeight /
                this.past.monthNowWearableWeight) *
                100 +
              "%";
          }
          if (
            this.past.yearPastWearableWeight >= this.past.yearNowWearableWeight
          ) {
            this.past["yearNowPersent"] =
              (this.past.yearNowWearableWeight /
                this.past.yearPastWearableWeight) *
                100 +
              "%";
            this.past["yearPastPersent"] = 100 + "%";
          } else {
            this.past["yearNowPersent"] = 100 + "%";
            this.past["yearPastPersent"] =
              (this.past.yearPastWearableWeight /
                this.past.yearNowWearableWeight) *
                100 +
              "%";
          }
        });
    },
  },
};
</script>
  
<style scoped>
#weight-week-now-progess {
  background: #3027e5;
  border-radius: 10px;
}

#weight-month-now-progess {
  background: #27e58a;
  border-radius: 10px;
}

#weight-now-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
}

#weight-progress-p {
  font-size: 0.8rem;
  font-weight: 600;
  color: black;
}

#weight-year-now-progess {
  background: #f33249;
  border-radius: 10px;
}

#weight-past-progess {
  background: #d0d6e5;
  border-radius: 10px;
}

#weight-middle-right-div-p {
  font-size: 1rem;
  font-weight: 800;
  margin-left: -1rem;
}
#weight-middle-right-div-h {
  font-size: 1rem;
  font-weight: 800;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}

#weight-middle {
  height: 400px;
  /* background-color: aqua; */
  margin: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
#weight-middle-left-btn {
  width: 12%;
  height: 100%;
  /* display: flex; */
  /* justify-content: center;
    align-items: center; */
  /* background-color: rgb(255, 255, 255); */
}
#weight-middle-left {
  width: 44%;
  height: 100%;
  align-items: center;
  background-color: rgb(255, 255, 255);
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  position: relative;
}

#weight-middle-left-child{
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


#weight-middle-right {
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

#weight-middle-left-btn-up {
  height: 70%;
}
#weightchart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#weight-middle-right-div {
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