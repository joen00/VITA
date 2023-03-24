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

        <div id="wfriendlist">
          <div id="wfriendlistin">
            <hooper :itemsToSlide="true" :itemsToShow="9" :centerMode="true">
              <slide v-for="slide in slides" :key="slide.id" class="slide">
                <button class="useritem" style="border: none">
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
                </button>
              </slide>
              <hooper-navigation slot="hooper-addons"></hooper-navigation>
              <hooper-pagination slot="hooper-addons"></hooper-pagination>
            </hooper>
   

          <b-button
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
          <!-- 여기서 부터 걸음수 컴포넌트 등장 -->
          <div style="clear: both"></div>
          <fwearable-walk></fwearable-walk>
          <fwearable-energy></fwearable-energy>
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
import VueHeader from "@/components/common/VueHeader.vue";


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
  },
  data() {
    return {
      VueHeaderTitle: "마이 헬스 데이터 with 프렌즈",
      VueHeaderContent:"친구의 프로필을 클릭하고 비교하기 버튼을 누르면 친구의 건강 데이터가 실시간으로 그래프에 반영된답니다. ",
      slides: [
        { id: 1, name: "김뿡" },
        { id: 2, name: "김뿡" },
        { id: 3, name: "김뿡" },
        { id: 4, name: "김뿡" },
        { id: 5, name: "김뿡" },
        { id: 6, name: "김뿡" },
        { id: 7, name: "김뿡" },
        { id: 8, name: "김뿡" },
      ],
    };
  },
};
</script>

<style>
/* ----------------------------------------- */

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
/* .hooper-slide.is-current {
  transform: scale(1.2);
} */
</style>