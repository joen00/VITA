<template>
  <div id="friendviewback">
  <div class="text-center">
    <div class="container">
      <div class="header">
        <div class="header-title">랭킹</div>
        <div class="header-content">친구들의 순위를 확인해보세요.</div>
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
          <friend-walk></friend-walk>
          <friend-walk></friend-walk>
          <friend-walk></friend-walk>
          <friend-walk></friend-walk>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import FriendWalk from "@/components/friend/FriendWalk.vue";
export default {
  name: "FriendView",
  components: {
    FriendWalk,
  },
  data: () => ({
    Totalranks: [
      { id: 1, name: "김광배", score: "10" },
      { id: 2, name: "이광배", score: "20" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
      { id: 3, name: "차광배", score: "30" },
    ],
  }),
};
</script>

<style>
/* ----------------------------------------- */
#friendviewback {
  /* background-image: url("../assets/background.png"); */
  background: linear-gradient(
      146.55deg,
      rgba(174, 162, 220, 0.2) -70.09%,
      rgba(140, 112, 253, 0) 55.52%
    ),
    linear-gradient(
      0deg,
      rgba(243, 255, 228, 0.2) -26.8%,
      rgba(178, 215, 149, 0.012) 16.1%,
      rgba(220, 243, 253, 0) 131.6%
    ),
    linear-gradient(239.1deg, #e2faff -29.57%, rgba(222, 243, 248, 0) 131.52%);
  /* margin-top: 1rem;
  width: 100%;
  height: 42.5rem;
  background-size: cover; */
  height: 100vh;
  overflow: hidden;
  margin:0 auto;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
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