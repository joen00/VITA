<template>
  <div id="energy-rank-list">
    <h3 id="energy-rank-list-h3" >활동에너지</h3> 
    <div id="energy-rank-list-div">

      <div class="rankenergy-item" v-for="rank in energyRanks" :key="rank">
      <span v-if="rank.id == 1" style="color:#FFB800; text-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);">{{ rank.id }}</span>
      <span v-else-if="rank.id == 2"  style="color:silver; text-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);">{{ rank.id }}</span>
      <span v-else-if="rank.id == 3"  style="color:#BB6C23; text-shadow: 0px 2px 2px rgba(0, 0, 0, 0.25);">{{ rank.id }}</span>
      <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
      <span>{{ rank.name }}</span>
      <span>{{ rank.score }}</span>
    </div>
  </div>   
  </div>

</template>

<script>
import axios from "axios";
// const SERVER_URL = "http://localhost:8080/friend";
// const SERVER_URL = "https://j8b106.p.ssafy.io:8084/friend";
// 유저 검색하거나 친구추가 테스트용
// user_id : 2703564897, user_name: 박서윤, user_nickname: bboong
// user_id : 2715879100, user_name: 이강윤, user_nickname: asdf
// const MY_USER_ID = 2715879100;

export default {
  name: "FriendEnergy",
  props: {
    msg: String,
  },
  data: () => ({
    energyRanks: [],
  }),
  methods: {
    getFriendEnergyRankList() {
      axios
        .get(this.$store.state.serverBaseUrl + `/friend` + `/rank/energy`, {
          headers: {
            userID: this.$store.state.myUserId,
          },
        })
        .then((response) => {
          console.log(response);
          var i = 0;
          response.data.map((data) => {
            i++;
            this.energyRanks.push({
              id: i,
              name: data.user_nickname,
              score: data.total_score_energy,
            });
          });
        });
    },
  },
  created() {
    this.getFriendEnergyRankList();
  },
};
</script>

<style>

/* .rankenergy{
    width: 90%;
    height: 100px;
    margin: 0 auto;
    margin-top: 1.5rem;
    margin-bottom: 1.5rem;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
} */

.rankenergy-item{
    width: 33%;
    margin: 0 auto;
    font-weight: 800;
    display: flex;
    justify-content: space-around;
    align-items: center;
}

#energy-rank-list-div{
  display: flex;
  justify-content: center;
}

#energy-rank-list {
  padding: 0.5rem;
  width: 90%;
  height: 6rem;
  /* background-color: rgb(200, 255, 204); */
  margin: 0 auto;
  margin-top: 1rem;
  margin-bottom: 2rem;
  border: 4px solid #79aec4;
  box-sizing: border-box;
  background-color: rgb(255, 255, 255);
  /* box-shadow: 1px 1px 1px 1px rgba(0.25, 0.25, 0.25, 0.25); */
  border-radius: 30px;

}


#energy-rank-list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 800;
  font-size: 20px;
  color: #172176;
  margin-bottom: -0.1rem;
}

</style>