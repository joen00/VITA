<template>
  <div id="friendviewback">
    <div class="text-center">
      <div class="container">
        <div class="header">
          <VueHeader
            :headerTitle="headerTitle"
            :headerContent="headerContent"
          />
        </div>
        <div class="left">
          <b-button
            style="
              background: #b9c6f4;
              border: none;
              color: black;
              font-weight: 600;
            "
            to="/friendlist"
            >친구 리스트 보러가기</b-button
          >
          <div class="rank-list">
            <h3 class="rank-list-h3">종합점수</h3>
            <div
              class="rank-list-div"
              v-for="totalrank in totalranks"
              :key="totalrank.id"
            >
              <span> {{ totalrank.id }} <b-avatar src=""></b-avatar></span>
              <span>{{ totalrank.name }} </span>
              <span>{{ totalrank.score }} </span>
            </div>
          </div>
        </div>
        <div class="right">
          <p style="font-weight: 700; font-size: 26px; color: #172176">
            각 종합점수
          </p>
          <div class="scroll">
            <FriendWalk />
            <FriendEnergy />
            <FriendRhr />
            <FriendStress />
            <FriendSleep />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FriendWalk from "@/components/friend/FriendWalk.vue";
import FriendEnergy from "@/components/friend/FriendEnergy.vue";
import FriendRhr from "@/components/friend/FriendRhr.vue";
import FriendStress from "@/components/friend/FriendStress.vue";
import FriendSleep from "@/components/friend/FriendSleep.vue";

import VueHeader from "@/components/common/VueHeader.vue";
import axios from "axios";

const SERVER_URL = "http://localhost:8080/friend";
// const SERVER_URL = "http://j8b106.p.ssafy.io:8000/friend";
// 유저 검색하거나 친구추가 테스트용
// user_id : 2703564897, user_name: 박서윤, user_nickname: bboong
// user_id : 2715879100, user_name: 이강윤, user_nickname: asdf
const MY_USER_ID = 1;

export default {
  name: "FriendView",
  components: {
    VueHeader,
    FriendWalk,
    FriendEnergy,
    FriendRhr,
    FriendStress,
    FriendSleep,
  },
  data: () => ({
    totalranks: [
      // { id: 1, name: "김광배", score: "50" },
      // { id: 2, name: "이광배", score: "20" },
      // { id: 3, name: "차광배", score: "30" },
      // { id: 3, name: "차광배", score: "30" },
      // { id: 3, name: "차광배", score: "30" },
    ],
    headerTitle: "랭킹",
    headerContent: "친구들의 순위를 확인해보세요.",
  }),
  methods: {
    getFriendTotalRankList() {
      axios
        .get(SERVER_URL + `/rank/total`, {
          headers: {
            userID: MY_USER_ID,
          },
        })
        .then((response) => {
          console.log(response);
          var i = 0;
          response.data.map((data) => {
            i++;
            this.totalranks.push({
              id: i,
              name: data.user_nickname,
              score: data.total_score,
            });
          });
        });
    },

    // getFriendEnergyRankList() {
    //   axios
    //     .get(SERVER_URL + `/rank/step`, {
    //       headers: {
    //         userID: MY_USER_ID,
    //       },
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       var i = 0;
    //       response.data.map((data) => {
    //         i++;
    //         this.energyRanks.push({
    //           id: i,
    //           name: data.user_nickname,
    //           score: data.total_score,
    //         });
    //       });
    //     });
    // },
    // getFriendRhrRankList() {
    //   axios
    //     .get(SERVER_URL + `/rank/rhr`, {
    //       headers: {
    //         userID: MY_USER_ID,
    //       },
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       var i = 0;
    //       response.data.map((data) => {
    //         i++;
    //         this.heartRateRanks.push({
    //           id: i,
    //           name: data.user_nickname,
    //           score: data.total_score,
    //         });
    //       });
    //     });
    // },
    // getFriendStressRankList() {
    //   axios
    //     .get(SERVER_URL + `/rank/stress`, {
    //       headers: {
    //         userID: MY_USER_ID,
    //       },
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       var i = 0;
    //       response.data.map((data) => {
    //         i++;
    //         this.stressRanks.push({
    //           id: i,
    //           name: data.user_nickname,
    //           score: data.total_score,
    //         });
    //       });
    //     });
    // },
    // getFriendSleepRankList() {
    //   axios
    //     .get(SERVER_URL + `/rank/sleep`, {
    //       headers: {
    //         userID: MY_USER_ID,
    //       },
    //     })
    //     .then((response) => {
    //       console.log(response);
    //       var i = 0;
    //       response.data.map((data) => {
    //         i++;
    //         this.sleepRanks.push({
    //           id: i,
    //           name: data.user_nickname,
    //           score: data.total_score,
    //         });
    //       });
    //     });
    // },
  },
  created() {
    this.getFriendTotalRankList();
    // this.getFriendStepRankList();
    // this.getFriendEnergyRankList();
    // this.getFriendRhrRankList();
    // this.getFriendStressRankList();
    // this.getFriendSleepRankList();
  },
};
</script>

<style>
/* ----------------------------------------- */
.header {
  width: 100%;
  height: 16%;
  margin-bottom: 2rem;
}
.header-title {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 3rem;
  line-height: 97px;
  color: #172176;
}
.header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474b;
}

.left {
  padding: 0.1rem;
  width: 40%;
  height: 84%;
  float: left;
}
.right {
  width: 60%;
  height: 84%;
  float: left;
  padding-top: 1rem;
  margin: 0 auto;
}
.rank-list {
  padding: 0.5rem;
  width: 80%;
  height: 90%;
  background-color: aquamarine;
  margin: 0 auto;
  margin-top: 0.5rem;
  box-sizing: border-box;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.rank-list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 700;
  font-size: 26px;
  color: #172176;
}
.rank-list-div {
  width: 90%;
  height: 4rem;
  background: #e0f4fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  padding-top: 0.9rem;
}
.scroll {
  overflow: scroll;
  width: 100%;
  height: 25rem;
}
.scroll::-webkit-scrollbar {
  display: none;
}

.scroll {
  -ms-overflow-style: none; /* 인터넷 익스플로러 */
  scrollbar-width: none; /* 파이어폭스 */
}
</style>