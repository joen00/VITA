<template>
  <div class="text-center">
    <div class="container">
      <div class="header">
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>
      <div id="header-content2">
        * 친구는 한 번에 최대 4명까지만 추가 가능합니다.
      </div>
      {{ selected }}
      <div id="wfriendlist">
        <!-- 여기 slide -->
        <div id="wfriendlistin">
          <hooper :itemsToSlide="true" :itemsToShow="9" :centerMode="true">
            <slide v-for="slide in slides" :key="slide.id" class="slide">
              <label class="useritem">
                <input
                  type="checkbox"
                  class="useritem"
                  v-model="selected"
                  :name="slide.id"
                  :value="slide.id"
                />
                <span>
                  <b-avatar
                    variant="info"
                    src="https://placekitten.com/300/300"
                    style="width: 50px; height: 50px; margin-top: -1.5rem"
                  ></b-avatar>
                  <br />
                  <p
                    style="
                      margin-top: -4.5rem;
                      font-weight: 600;
                      font-size: 1rem;
                    "
                  >
                    {{ slide.name }}
                  </p>
                </span>
              </label>
            </slide>
            <hooper-navigation slot="hooper-addons"></hooper-navigation>
            <hooper-pagination slot="hooper-addons"></hooper-pagination>
          </hooper>
          <!-- 여기 slide 끝 -->
          <!-- 비교하기 버튼 -->
          <b-button
            @click="getAverage(selected)"
            style="
              float: right;
              margin: 2.5rem;
              font-weight: 600;
              font-size: 1rem;
            "
            >비교하기</b-button
          >

          <div
            style="
              clear: right;
              float: right;
              margin-top: -2rem;
              font-size: 0.7rem;
              margin-right: 1rem;
            "
          >
            친구와의 비교는 최근 1년 동안의 데이터를 기준으로 평균을 계산해
            비교해줍니다.
          </div>
          <!-- 비교하기 버튼 -->
          <!-- 여기서 부터 걸음수 컴포넌트 등장 -->
          <div style="clear: both"></div>
          <fwearable-walk :walkData="walkData" style="margin-top: 8rem"></fwearable-walk>
          <div style="clear: both"></div>
          <fwearable-energy :energyData="energyData" style="margin-top: 8rem"></fwearable-energy>
          <div style="clear: both"></div>
          <fwearable-rhr :rhrData="rhrData" style="margin-top: 8rem"></fwearable-rhr>
          <div style="clear: both"></div>
          <fwearable-stress :stressData="stressData" style="margin-top: 8rem"></fwearable-stress>
          <div style="clear: both"></div>
          <fwearable-sleep :sleepData="sleepData" style="margin-top: 8rem"></fwearable-sleep>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import {
  Hooper,
  Slide,
  Pagination as HooperPagination,
  Navigation as HooperNavigation,
} from "hooper";
import "hooper/dist/hooper.css";
import FwearableWalk from "@/components/wearable_friend/FwearableWalk.vue";
import FwearableEnergy from "@/components/wearable_friend/FwearableEnergy.vue";
import FwearableRhr from "@/components/wearable_friend/FwearableRhr.vue";
import FwearableStress from "@/components/wearable_friend/FwearableStress.vue";
import FwearableSleep from "@/components/wearable_friend/FwearableSleep.vue";
import VueHeader from "@/components/common/VueHeader.vue";
import axios from 'axios';

export default {
  name: "App",
  components: {
    Hooper,
    Slide,
    HooperPagination,
    HooperNavigation,
    FwearableWalk,
    FwearableEnergy,
    VueHeader,
    FwearableRhr,
    FwearableStress,
    FwearableSleep,
  },
  methods: {},
  data() {
    return {
      selected: [], // Must be an array reference!
      Data: [],
      energyData: [], // {name:userNickname, value:userAverageEnergy}
      rhrData: [],    // {name:userNickname, value:userAverageEnergyRhr}
      sleepData: [],  // {name:userNickname, value:userAverageSleep}
      stressData: [], // {name:userNickname, value:userAverageStress}
      walkData: [],   // {name:userNickname, value:userAverageStep}
      VueHeaderTitle: "마이 헬스 데이터 with 프렌즈",
      VueHeaderContent:
        "친구의 프로필을 클릭하고 비교하기 버튼을 누르면 친구의 건강 데이터가 실시간으로 그래프에 반영된답니다. ",
      slides: [],
    };
  },
  created() {
    this.getFriendList();
  },
  methods: {
    getFriendList() {
      axios.get(this.$store.state.friendUrl , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token,
                'Access-Control-Allow-Origin': '*'},
      }).then(res => {
        this.slides = res.data.map(function(e){
          return {"id": e.userId, "name": e.userNickname};
        })
      })
    },
    getAverage(selected) {
      console.log(selected)
      axios.post(this.$store.state.friendUrl + "/all" , selected , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        console.log(res.data)
        this.data = res.data;
        axios.get(this.$store.state.friendUrl + "/user" , {
        headers: {'Content-Type': 'application/json',
                  'token': this.$store.state.test_token},
        }).then(res => {
          console.log(res.data)
          this.data.push(res.data);
          this.energyData = res.data.map(function(e){
          return {"name":e.userNickname, "value":e.userAverageEnergy};
          })
          this.rhrData = res.data.map(function(e){
          return {"name":e.userNickname, "value":e.userAverageRhr};
          })
          this.sleepData = res.data.map(function(e){
          return {"name":e.userNickname, "value":e.userAverageSleep};
          })
          this.stressData = res.data.map(function(e){
          return {"name":e.userNickname, "value":e.userAverageStress};
          })
          this.walkData = res.data.map(function(e){
          return {"name":e.userNickname, "value":e.userAverageStep};
          })
        })
      })

    }
  }
};
</script>

<style>
/* ----------------------------------------- */
/* 여기 checkbox 버튼 */
.useritem[type="checkbox"] {
  display: none;
}

.useritem input[type="checkbox"] + span {
  display: inline-block;
  text-align: center;
  cursor: pointer;
  width: 100px;
  height: 100px;
  background-color: #ffffff;
  line-height: 100px;
  border-radius: 20%;
}

.useritem input[type="checkbox"]:checked + span {
  background-color: #113a6b;
  color: #ffffff;
}
/* 여기 checkbox 버튼 */
#header-content2 {
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 24px;
  color: #909090;
  margin-top: -2rem;
  margin-bottom: 3rem;
}
#wfriendlist {
  box-sizing: border-box;
  width: 100%;
  height: 180px;
  background: #a0cfee;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  /* line-height: 100px; */
}
.useritem {
  width: 100px;
  height: 100px;
  background-color: #ffffff;
  line-height: 100px;
  border-radius: 20%;
}
.useritem:hover {
  width: 100px;
  height: 100px;
  background-color: #f2ff79;
  line-height: 100px;
  border-radius: 20%;
}

.hooper {
  height: 21vh;
}
.slide {
  height: 100px;
  padding: 30px;
  line-height: 100px;
}
.hooper-indicator {
  height: 8px;
  width: 8px;
  border-radius: 50%;
  background-color: darkgrey;
}
.hooper-pagination {
  bottom: -20px;
}
/* .hooper-slide.is-current {
  transform: scale(1.2);
} */
</style>