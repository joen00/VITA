<template>
  <div class="rankstress">
    <h5>스트레스</h5>
    <div class="rankstress-item" v-for="rank in stressRanks" :key="rank">
      {{ rank.id }}
      <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
      {{ rank.name }}
      {{ rank.score }}
    </div>
  </div>
</template>

<script>
import axios from "axios";
// const SERVER_URL = "http://localhost:8080/friend";
const SERVER_URL = "https://j8b106.p.ssafy.io:8084/friend";
// 유저 검색하거나 친구추가 테스트용
// user_id : 2703564897, user_name: 박서윤, user_nickname: bboong
// user_id : 2715879100, user_name: 이강윤, user_nickname: asdf
const MY_USER_ID = 2715879100;

export default {
  name: "FriendStress",
  props: {
    msg: String,
  },
  data: () => ({
    stressRanks: [],
  }),
  methods: {
    getFriendStepRankList() {
      axios
        .get(SERVER_URL + `/rank/stress`, {
          headers: {
            userID: MY_USER_ID,
          },
        })
        .then((response) => {
          console.log(response);
          var i = 0;
          response.data.map((data) => {
            i++;
            this.stressRanks.push({
              id: i,
              name: data.user_nickname,
              score: data.total_score_stress,
            });
          });
        });
    },
  },
  created() {
    this.getFriendStepRankList();
  },
};
</script>

<style>
.rankstress {
  width: 90%;
  height: 100px;
  margin: 0 auto;
  margin-top: 1.5rem;
  margin-bottom: 1.5rem;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}

.rankstress-item {
  float: left;
  width: 33%;
  margin: 0 auto;
}
</style>