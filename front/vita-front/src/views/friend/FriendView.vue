<template>
  <div id="friendviewback">
  <div class="text-center">
    <div class="container">
      <div class="header">
        <VueHeader :headerTitle="headerTitle" :headerContent="headerContent"/>
      </div>
      <div class="left">
        <b-button class="btn" v-b-modal.modal-scrollable id="btn-show-friend" to="/friendlist"
          >친구 리스트 보러가기</b-button
        >
        <div id="rank-list">
          <h3 class="rank-list-h3">종합점수</h3>
          <div
            class="rank-list-div"
            v-for="totalrank in Totalranks"
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
          <friend-walk></friend-walk>
          <friend-energy></friend-energy>
          <friend-heart></friend-heart>
          <friend-stress></friend-stress>
          <!-- <friend-walk></friend-walk> -->
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import FriendWalk from "@/components/friend/FriendWalk.vue";
import FriendEnergy from "@/components/friend/FriendEnergy.vue";
import FriendHeart from "@/components/friend/FriendHeart.vue";
import FriendStress from "@/components/friend/FriendStress.vue";
import VueHeader from "@/components/common/VueHeader.vue";

export default {
  name: "FriendView",
  components: {
    FriendWalk,
    VueHeader,
    FriendEnergy,
    FriendHeart,
    FriendStress
  },
  data: () => ({
    Totalranks: [
      { id: 1, name: "김광배", score: "10" },
      { id: 2, name: "이광배", score: "20" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
    ],
    headerTitle: "랭킹",
    headerContent: "친구들의 순위를 확인해보세요."
  }),
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
#rank-list {
  padding: 0.5rem;
  width: 80%;
  height: 26rem;
  background-color: aquamarine;
  margin: 0 auto;
  margin-top: 0.5rem;
  box-sizing: border-box;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 1px 1px 1px 1px rgba(0.25, 0.25, 0.25, 0.25);
  border-radius: 30px;
  overflow: scroll;
}
#rank-list::-webkit-scrollbar {
  display: none;
}

#rank-list {
  -ms-overflow-style: none; /* 인터넷 익스플로러 */
  scrollbar-width: none; /* 파이어폭스 */
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
  height: 3.3rem;
  background: #E0F4FD;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  color: rgb(0, 0, 0);
  display: flex;
  justify-content:space-around;
  align-items: center;
  font-weight: 600;
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











#btn-show-friend {
  width: 50%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}
#btn-show-friend:hover {
  width: 50%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}


</style>