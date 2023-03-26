<template>
  <div class="text-center">
    <div class="container">
      <VueHeader
        :VueHeaderTitle="VueHeaderTitle"
        :VueHeaderContent="VueHeaderContent"
      />

      <b-container class="bv-example-row container h-30" id="mypage-container">
        <b-col class="left">
          <b-row class="profile">
            <!-- 유저 아바타 -->
            <b-col class="avatar">
              <img id="img-avatar" :src="require(`/public/user/avatar.png`)" />
            </b-col>

            <!-- 유저 신체 정보 -->
            <b-col class="info">
              <b-row class="nickname">
                {{ nickname }}
              </b-row>
              <b-row class="item" v-for="info in UserInfo" :key="info">
                <b-col cols="3" class="icon">
                  <b-row
                    ><img id="item-img" :src="require(`/public/user/${info.attr}.png`)"
                  /></b-row>
                </b-col>
                <b-col cols="9" class="data">
                  <b-row class="info-data">{{ info.data }}</b-row>
                  <b-row class="info-desc">{{ info.desc }}</b-row>
                </b-col>
              </b-row>
            </b-col>
          </b-row>

          <!-- 캘린더 -->
          <b-row class="calander">
            <VDatePicker
              title-position="middle"
              :attributes="attrs"
              @dayclick="dayclick"
              ref="datepicker"
            />
          </b-row>
        </b-col>
        <!-- left 끝 -->

        <!-- right 시작 -->
        <b-col class="right">
          <b-row>
            <b-col id="btn-fileupload"
              ><b-button class="btn-fileupload d-inline-flex"
                >파일 업로드 하러 가기</b-button
              ></b-col
            >
          </b-row>

          <!-- 뱃지 리스트 -->
          <b-row class="badge-list"> </b-row>

          <!-- 히스토리 -->
          <b-row class="history" id="history">
            <carousel-3d
              ref="hitoryCarousel"
              :width="400"
              :height="240"
              :key="componentKey"
            >
              <slide
                id="carousel"
                v-for="(slide, i) in slides"
                :index="i"
                :key="i"
              >
                <template
                  slot-scope="{ index, isCurrent, leftIndex, rightIndex }"
                >
                  <img
                    :data-index="index"
                    :class="{
                      current: isCurrent,
                      onLeft: leftIndex >= 0,
                      onRight: rightIndex >= 0,
                    }"
                    :src="require(`/public/user/${slide.src}.png`)"
                  />
                </template>
              </slide>
            </carousel-3d>
          </b-row>
        </b-col>
      </b-container>
    </div>
  </div>
</template>

<script>
import { Carousel3d, Slide } from "vue-carousel-3d";

import VueHeader from "@/components/common/VueHeader.vue";
import VDatePicker from "v-calendar/lib/components/date-picker.umd";
import axios from "axios";

export default {
  name: "MypageView",
  components: {
    Carousel3d,
    Slide,
    VueHeader,
    VDatePicker,
  },
  data: () => ({
    nickname: `뿡뿡 아영`,
    gender: "female",
    UserInfo: [
      { attr: "weight", data: '40.0kg', desc: "체중" },
      { attr: "gender", data: '여자', desc: "성별" },
      { attr: "age", data: '10세', desc: "나이" },
      { attr: "height", data: '180.2cm', desc: "키" },
    ],
    componentKey: 0,
    slides: [
      { index: "0302", src: "mytotal_1" },
      { index: "0307", src: "mytotal_2" },
      { index: "0323", src: "mytotal_3" },
    ],
    attrs: [
      {
        key: "0302",
        highlight: "gray",
        dates: "2023-03-02",
      },
      {
        key: "0307",
        highlight: "gray",
        dates: "2023-03-07",
      },
      {
        key: "0323",
        highlight: "gray",
        dates: "2023-03-23",
      },
    ],
    VueHeaderTitle: "마이페이지",
    VueHeaderContent: "나의 정보를 확인해보세요.",
  }),

  methods: {
    goToSlide(index) {
      this.$refs.hitoryCarousel.goSlide(index);
    },
    getUserInfo() {
      axios
        .get(
          this.$store.state.serverBaseUrl +
            `/mypage/user` +
            `/${this.$store.state.myUserId}`
        )
        .then((response) => {
          console.log(response);
          (this.nickname = response.data.userNickname),
            (this.UserInfo[0].data = response.data.userWeight + "kg"),
            (this.UserInfo[1].data = response.data.userGender),
            (this.UserInfo[2].data = response.data.userAge + "살"),
            (this.UserInfo[3].data = response.data.userHeight + "cm");
        });
    },
    getUserHistory() {
      axios
        .get(
          this.$store.state.serverBaseUrl +
            `/mypage/user/history` +
            `/${this.$store.state.myUserId}`
        )
        .then((response) => {
          response.data.map((data) => {
            this.slides.push({
              index: data.created_date,
              src: data.user_history_img,
            });
            this.attrs.push({
              key: data.created_date,
              highlight: "gray",
              dates: data.created_date,
            });
          });
          console.log(response);
        });
    },
    dayclick(day) {
      if (day.attributes[0]) {
        if (day.attributes[0].highlight.base.color == "gray") {
          this.componentKey++;
          const foundIndex = this.slides.findIndex(
            (slide) => slide.index === day.attributes[0].key
          );
          const deletedItem = this.slides.splice(foundIndex, 1);
          console.log(deletedItem);
          this.slides.splice(0, 0, deletedItem[0]);
          // console.log(found);
        }
      }
    },
  },
  created() {
    this.getUserInfo();
    this.getUserHistory();
  },
};
</script>

<style scoped>
.text-center {
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
  width: 100%;
  height: 42.5rem;
}
.container {
  height: 100%;
}
.left {
  margin-top: 1rem;
  width: 40%;
  height: 84%;
  float: left;
}
.right {
  width: 60%;
  height: 84%;
  float: left;
  margin-top: 1rem;
  margin: 0 auto;
  padding: 30px;
}
.profile {
  padding: 30px;
}
.avatar {
  float: left;
}
.icon {
  float: left;
}
.item {
  padding: 8px;
}
.info {
  float: left;
  padding-left: 30px;
}
.data {
  float: left;
}
.nickname {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 2rem;
}
.calander {
  text-align: center;
}
.btn-fileupload {
  background-color: #3695be;
  border-radius: 10px;
  font-size: 12px;
  float: left;
  border: none;
  padding-inline: 20px;
}
.btn-fileupload {
  margin: 10px;
}
.badge-list {
  height: 200px;
  border-radius: 20px;
  box-shadow: inset 1px 1px 4px rgba(0, 0, 0, 0.25);
  background: #ffffff;
}
.history {
  margin-top: 100px;
}
.vc-container {
  width: 400px;
}
#carousel {
  border-radius: 10px;
  border: none;
  box-shadow: 3px 7px 7px rgba(0, 0, 0, 0.25);
  background: #ffffff;
}
#img-avatar {
  width: 200px;
  height: 300px;
}
#item-img {
  padding: 0.7rem;
}
.info.data {
  font-size: 17px;
}
.info-desc {
  color: rgb(150, 150, 150);
  font-size: 13px;
}
</style>