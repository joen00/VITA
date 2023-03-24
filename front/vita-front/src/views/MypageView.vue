<template>
  <div class="text-center">
    <div class="container">
        <div class="header">
          <VueHeader :VueHeaderTitle = VueHeaderTitle :VueHeaderContent = VueHeaderContent />
        </div>

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
                  <b-col class="icon">
                    <b-row><img :src="require(`/public/user/${info.attr}.png`)" /></b-row>
                  </b-col>
                  <b-col cols="8" class="data">
                    <b-row class="info-data">{{ info.data }}</b-row>
                    <b-row class="info-desc">{{ info.desc }}</b-row>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>

            <!-- 캘린더 -->
            <b-row class="calander">
              <b-calendar
                selected-variant="primary"
                today-variant="info"
                nav-button-variant="primary">
              </b-calendar>
            </b-row>

            <v-calendar
              :attributes='attributes'
              />
          </b-col>
          <!-- left 끝 -->

          <!-- right 시작 -->
          <b-col class="right">
            <b-row>
              <b-col id="btn-fileupload"><b-button class="btn-fileupload d-inline-flex">파일 업로드 하러 가기</b-button></b-col>
            </b-row>
            
            <!-- 뱃지 리스트 -->
            <b-row class="badge-list">
            </b-row>

            <!-- 히스토리 -->
            <b-row class="history">
              <carousel-3d ref="hitoryCarousel" :width="400" :height="240">
                  <slide id="carousel" v-for="(slide, i) in slides" :index="i" :key="i">
                      <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
                          <img :data-index="index" :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" :src="require(`/public/user/${slide.src}.png`)">
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
import { Carousel3d, Slide } from 'vue-carousel-3d';

import VueHeader from '@/components/common/VueHeader.vue';
import axios from 'axios';

export default {
  name: 'MypageView',
  components: {
    Carousel3d,
    Slide,
    VueHeader,
  },
  data: () => ({
    nickname: `뿡뿡 아영`,
    gender: "female",
    UserInfo: [
      { attr: 'weight', data: null, desc: '체중' },
      { attr: 'gender', data: null, desc: '성별' },
      { attr: 'age', data: null, desc: '나이' },
      { attr: 'height', data: null, desc: '키' },
    ],
    slides: [
      { src: 'mytotal' },
      { src: 'mytotal' },
      { src: 'mytotal' },
      // { src: 'mytotal' },
      // { src: 'mytotal' },
      // { src: 'mytotal' },
      // { src: 'mytotal' },
      // { src: 'mytotal' },
      // { src: 'mytotal' },
    ],
    VueHeaderTitle: "마이페이지",
    VueHeaderContent: "나의 정보를 확인해보세요."
  }),

  methods: {
    goToSlide(index) {
      this.$refs.hitoryCarousel.goSlide(index)
    },
    getUserInfo() {
      axios
        .get(this.$store.state.serverBaseUrl + `/mypage/user` + `/${this.$store.state.myUserId}`)
        .then((response) => {
          console.log(response)
          this.nickname = response.data.userNickname,
          this.UserInfo[0].data = response.data.userWeight + 'kg',
          this.UserInfo[1].data = response.data.userGender,
          this.UserInfo[2].data = response.data.userAge + '살',
          this.UserInfo[3].data = response.data.userHeight + 'cm'
        });
    },
    // getUserHistory() {
    //   axios
    //     .get(this.$store.state.serverBaseUrl + `/mypage/user/history` + `/${this.$store.state.myUserId}`)
    //     .then((response) => {
    //       console.log(response)
    //       this.nickname = response.data.userNickname,
    //       this.UserInfo[0].data = response.data.userWeight + 'kg',
    //       this.UserInfo[1].data = response.data.userGender,
    //       this.UserInfo[2].data = response.data.userAge + '살',
    //       this.UserInfo[3].data = response.data.userHeight + 'cm'
    //     });
    // }
  },
  created() {
    this.getUserInfo();
    this.getUserHistory();
  }

}

</script>

<style scoped>
.text-center {
  /* background-image: url("../assets/background.png"); */
  background: linear-gradient(146.55deg, rgba(174, 162, 220, 0.2) -70.09%, rgba(140, 112, 253, 0) 55.52%), linear-gradient(0deg, rgba(243, 255, 228, 0.2) -26.8%, rgba(178, 215, 149, 0.012) 16.1%, rgba(220, 243, 253, 0) 131.6%), linear-gradient(239.1deg, #E2FAFF -29.57%, rgba(222, 243, 248, 0) 131.52%);
  width: 100%;
  height: 42.5rem;
}
.header {
  width: 100%;
  height: 16%;
  margin-bottom: 2rem;
}
.header-title {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 3rem;
  line-height: 97px;
  color: #172176;
}
.header-content {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474B;
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
  padding: 5px;
}
.info {
  float: left;
  padding: 30px;
}
.data {
  float: left;
}
.nickname {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 2rem;
}
.calander {
  text-align: center;
}
.btn-fileupload {
  background-color:#3695BE;
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
  background: #FFFFFF;
}
#carousel {
  border-radius: 10px;
  border: none;
  box-shadow: 3px 7px 7px rgba(0, 0, 0, 0.25);
  background: #FFFFFF;
}
#img-avatar {
  width: 200px;
  height: 300px;
}
</style>