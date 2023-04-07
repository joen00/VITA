<template>
  <div class="text-center">
    <div class="container">
      <VueHeader
        :VueHeaderTitle="VueHeaderTitle"
        :VueHeaderContent="VueHeaderContent"
      />

      <b-container class="bv-example-row container h-30" id="mypage-container">
        <b-row class="up">
          <div class="left">
            <div class="profile">
              <!-- 유저 아바타 -->
              <div class="avatar">
                <img
                  id="img-avatar"
                  :src="require(`/public/user-avatar/${avatar}.png`)"
                />
              </div>

              <!-- 유저 신체 정보 -->
              <div class="info">
                <div class="info-header">
                  <div class="nickname">{{ nickname }}</div>
                  <div class="edit">
                    <img
                      src="@/assets/edit.png"
                      @click="editInfo"
                      id="btn-edit-img"
                    />
                  </div>
                </div>
                <div
                  class="item"
                  v-for="(info, index) in UserInfo"
                  :key="index"
                >
                  <div class="icon">
                    <img
                      :src="require(`/public/user/${info.attr}.png`)"
                      id="item-img"
                    />
                  </div>
                  <div class="data">
                    <div
                      v-if="
                        info.editable &&
                        info.attr != 'gender' &&
                        info.attr != 'phoneType'
                      "
                    >
                      <b-input v-model="info.data" />
                    </div>
                    <div v-else-if="info.editable && info.attr == 'phoneType'">
                      <!-- 라디오 버튼으로 바꾸기 -->
                      <b-form-group>
                        <b-form-radio-group
                          v-model="info.data"
                          :options="phoneTypeOptions"
                          size="sm"
                          buttons
                        ></b-form-radio-group>
                      </b-form-group>
                    </div>
                    <div v-else>
                      <div class="info-data">
                        {{ info.data }}{{ info.unit }}
                      </div>
                    </div>
                    <div class="info-desc">{{ info.desc }}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="right">
            <b-row>
              <b-col id="btn-fileupload"
                ><b-button
                  class="btn-fileupload d-inline-flex"
                  @click="$router.push(`/fileupload`)"
                  >파일 업로드 하러 가기</b-button
                ></b-col
              >
            </b-row>

            <!-- 뱃지 리스트 -->
            <b-row class="badge-container">
              <div class="badge-list">
               <img
                v-for="badgeId in badgeList"
                :key="badgeId"
                id="badge-img"
                :src="require(`/public/badge/badge${badgeId}.png`)">
              </div>
            </b-row>
          </div>
        </b-row>

        <b-row class="down">
          <!-- 캘린더 -->
          <div class="left">
            <div class="calander">
              <VDatePicker
                timezone="UTC"
                title-position="middle"
                :attributes="attrs"
                @dayclick="dayclick"
                ref="datepicker"
              />
            </div>
          </div>

          <!-- 히스토리 -->
          <div class="right">
            <b-row class="history" id="history">
              <carousel-3d
                ref="hitoryCarousel"
                :width="400"
                :height="300"
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
                      :src="slide.src"
                    />
                    <div class="carousel-bottom">
                      <span class="carousel-date">{{ slide.index }}</span>
                    </div>
                  </template>
                </slide>
              </carousel-3d>
            </b-row>
          </div>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import { Carousel3d, Slide } from "vue-carousel-3d";
import { mapGetters, mapMutations } from "vuex";

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
    nickname: "뿡뿡 아영",
    avatar: "avatar0",
    UserInfo: [
      {
        attr: "weight",
        data: "40.0",
        desc: "체중",
        unit: "kg",
        editable: false,
      },
      { attr: "gender", data: "여자", desc: "성별", unit: "", editable: false },
      { attr: "age", data: "10", desc: "나이", unit: "세", editable: false },
      {
        attr: "height",
        data: "180.2",
        desc: "키",
        unit: "cm",
        editable: false,
      },
      {
        attr: "phoneType",
        data: "APPLE",
        desc: "휴대폰타입",
        editable: false,
      },
    ],
    componentKey: 0,
    slides: [
      // { index: "2023-03-02", src: "mytotal_1" },
      // { index: "2023-03-07", src: "mytotal_2" },
      // { index: "2023-03-23", src: "mytotal_3" },
    ],
    attrs: [
      // {
      //   key: "2023-03-02",
      //   highlight: "gray",
      //   dates: "2023-03-02",
      // },
      // {
      //   key: "2023-03-07",
      //   highlight: "gray",
      //   dates: "2023-03-07",
      // },
      // {
      //   key: "2023-03-23",
      //   highlight: "gray",
      //   dates: "2023-03-23",
      // },
    ],
    phoneTypeOptions: [
      { text: "SAMSUNG", value: "SAMSUNG" },
      { text: "APPLE", value: "APPLE" },
    ],
    badgeList: [
      // { badgeId: 1 }
    ],
    VueHeaderTitle: "마이페이지",
    VueHeaderContent: "나의 정보를 확인해보세요.",
    editMode: false,
  }),

  computed: {
    ...mapGetters(["token", "user"]),
    getSlides() {
      return this.slides;
    },
  },

  methods: {
    goToSlide(index) {
      this.$refs.hitoryCarousel.goSlide(index);
    },
    getUserInfo() {
      axios
        .get(this.$store.state.serverBaseUrl + `/users/mypage`, {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          (this.nickname = response.data.userNickname),
            (this.UserInfo[0].data = response.data.userWeight),
            (this.UserInfo[1].data = response.data.userGender == "female" ? "여자" : "남자"),
            (this.UserInfo[2].data = response.data.userAge),
            (this.UserInfo[3].data = response.data.userHeight),
            (this.UserInfo[4].data = response.data.userPhoneType),
            (this.avatar = response.data.userAvatar);
        });
    },
    getUserHistory() {
      axios
        .get(this.$store.state.serverBaseUrl + `/users/mypage/history`, {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          this.componentKey++;
          response.data.map((data) => {
            this.slides.push({
              index: data.createdDate.substr(0, 10),
              src: data.userHistoryImg,
            });
            this.attrs.push({
              key: data.createdDate.substr(0, 10),
              highlight: "gray",
              dates: data.createdDate,
            });
          });
        });
    },
    dayclick(day) {
      if (day.attributes[0]) {
        if (day.attributes[0].highlight.base.color == "gray") {
          const foundIndex = this.slides.findIndex(
            (slide) => slide.index === day.attributes[0].key
          );
          this.$refs.hitoryCarousel.goSlide(foundIndex);
        }
      }
    },
    editInfo() {
      this.UserInfo.forEach((info) => {
        info.editable = !info.editable;
      });

      if (this.UserInfo[0].editable == false) {
        axios
          .put(
            this.$store.state.serverBaseUrl + `/users/mypage/update`,
            {
              userWeight: this.UserInfo[0].data,
              userAge: this.UserInfo[2].data,
              userHeight: this.UserInfo[3].data,
              userPhoneType: this.UserInfo[4].data,
            },
            {
              headers: {
                Authorization: `Bearer ${this.token}`,
              },
            }
          )
          .then((response) => {
            this.getUserInfo();
            this.setUserPhoneType(this.UserInfo[4].data);
          });
      }
    },
    getBadge() {
      axios
        .get(this.$store.state.serverBaseUrl + `/users/mypage/badge`, {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          response.data.map((badge) => {
            if (badge.userBadgeGet) {
              this.badgeList.push(badge.badgeId);
            }
          })
        });
    },
    ...mapMutations(['setUserPhoneType'])
  },

  created() {
    this.getUserInfo();
    this.getUserHistory();
    this.getBadge();
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
}
.container {
  height: 100%;
}
.up {
  display: flex;
}
.down {
  margin-top: 1rem;
  display: flex;
}
.left {
  margin-top: 1rem;
  width: 40%;
  float: left;
}
.right {
  width: 60%;
  float: left;
  margin-top: 1rem;
  margin: 0 auto;
  padding: 20px;
}
.profile {
  padding: 40px;
  display: flex;
  align-items: center;
}
.avatar {
  float: left;
  width: 50%;
}
.info-header {
  display: flex;
  margin-bottom: 10px;
}
.info {
  margin-left: 20px;
  float: left;
  width: 50%;
}
.item {
  padding: 1px;
  display: flex;
}
.icon {
  float: left;
  width: 30%;
  padding: 0 10px 10px 10px;
}
.data {
  float: left;
  width: 70%;
}
.nickname {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 2rem;
  width: 90%;
}
.edit {
  width: 10%;
}
.calander {
  text-align: center;
}
.btn-fileupload {
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-size: 14px;
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border: solid 2px #3695be;
  border-radius: 12px;
  float: left;
  margin: 10px;
  padding-inline: 25px;
}
.btn-fileupload:hover {
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  background: rgb(255, 255, 255);
}
.badge-container {
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
  align-items: center;
  text-align: left;
  height: 200px;
  border-radius: 20px;
  box-shadow: inset 1px 1px 4px rgba(0, 0, 0, 0.25);
  background: #ffffff;
}
.badge-list {
  width: 100%;
}
.history {
  margin-top: 2rem;
}
.vc-container {
  width: 400px;
}
#carousel {
  border-radius: 10px;
  border: none;
  background: transparent;
}
.carousel-bottom {
  justify-content: center;
  text-align: center;
}
#img-avatar {
  width: 200px;
  height: 300px;
}
#item-img {
  padding: 0.7rem;
  width: 55px;
  height: 55px;
}
#badge-img {
  margin-inline: 10px;
  width: 125px;
  height: 125px;
}
.info.data {
  font-size: 15px;
}
.info-desc {
  color: rgb(150, 150, 150);
  font-size: 12px;
}
#btn-edit-img {
  width: 25px;
  height: 25px;
}
#btn-edit-img:hover {
  content: url("@/assets/edit-hover.png");
}
.carousel-date {
  font-size: 13px;
  color: #fff;
  background: #3695be;
  border-radius: 15px;
  padding: 2px 25px;
}
</style>