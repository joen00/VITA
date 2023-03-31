<template>
  <div id="backgroundcolor">
    <div class="text-center">
      <div class="container">

        <div id="header">
          <VueHeader :VueHeaderTitle = VueHeaderTitle :VueHeaderContent = VueHeaderContent />
          <div id="extraDesc">{{ VueHeaderContentDesc }}</div>
          <div id="btn-section">
            <b-button class="btn-datatype">전체</b-button>
            <b-button class="btn-datatype">연령 & 성별</b-button>
          </div>
          <div class="description">{{ description }}</div>
        </div>

        <div class="body">
            <b-container class="bv-example-row" id="chart-container">
                <b-row class="my-5">
                    <b-col><awearable-walk :key="componentKey" :apiData="apiData.apiAverageStep" :userData="userData.userAverageStep"></awearable-walk></b-col>
                    <b-col><awearable-energy :key="componentKey" :apiData="apiData.apiAverageEnergy" :userData="userData.userAverageEnergy"></awearable-energy></b-col>
                </b-row>
                <b-row class="my-5">
                    <b-col><awearable-heart :key="componentKey" :apiData="apiData.apiAverageRhr" :userData="userData.userAverageRhr"></awearable-heart></b-col>
                    <b-col><awearable-stress :key="componentKey" :apiData="apiData.apiAverageStress" :userData="userData.userAverageStress"></awearable-stress></b-col>
                </b-row>
                <b-row class="my-5">
                    <b-col><awearable-sleep-time :key="componentKey" :apiData="apiData.apiAverageSleep" :userData="userData.userAverageSleep"></awearable-sleep-time></b-col>
                    <b-col></b-col>
                    <!-- <b-col><awearable-sleep-stage></awearable-sleep-stage></b-col> -->
                </b-row>
            </b-container>
        </div>
      </div>
    </div>
  </div>
</template>
    
<script>
  import AwearableWalk from '@/components/wearable_api/AwearableWalk.vue'; 
  import AwearableEnergy from '@/components/wearable_api/AwearableEnergy.vue';
  import AwearableHeart from '@/components/wearable_api/AwearableHeart.vue'; 
  import AwearableStress from '@/components/wearable_api/AwearableStress.vue';
  import AwearableSleepTime from '@/components/wearable_api/AwearableSleepTime.vue';
  // import AwearableSleepStage from '@/components/wearable_api/AwearableSleepStage.vue';
  import VueHeader from '@/components/common/VueHeader.vue';
  import axios from "axios";
  import { mapGetters } from "vuex";
  
  export default {
    name: "App",
    components: { 
        AwearableWalk,
        AwearableEnergy,
        AwearableHeart,
        AwearableStress,
        AwearableSleepTime,
        // AwearableSleepStage,
        VueHeader
    },
    data() {
      return {
        VueHeaderTitle : "마이 헬스 데이터 with 평균",
        VueHeaderContent : "사람들과 나의 건강 데이터를 비교해보세요.",
        VueHeaderContentDesc : '통계 자료에 기반한 건강 항목 별 평균 데이터와 나의 건강 데이터를 비교해줍니다.',
        description: "평균 데이터와의 비교는 최근 1년 동안의 데이터를 기준으로 평균을 계산해 비교해줍니다.",
        apiData: {},
        userData: {},
      };
    },
    created() {
      this.getApiData();
    },
    computed: {
      ...mapGetters(["token", "user"]),
    },
    methods: {
      async getApiData() {
        await axios
          .get("https://j8b106.p.ssafy.io/api/wearable/average", {
            headers: {
              "Content-Type": "application/json",
              // Authorization: `Bearer ${this.token}`,
            },
          })
          .then((res) => {
            this.apiData = res.data
            console.log(this.apiData);
            axios
            .get("https://j8b106.p.ssafy.io/api/wearable/average/user", {
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${this.token}`,
              },
            })
            .then((res) => {
              this.userData = res.data
              console.log(this.userData);
              this.componentKey += 1;
            });
          });
      }
    },
  };
</script>
    
<style scoped>
  #backgroundcolor {
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
      height: 1000vh;
      overflow: hidden;
      margin:0;
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
  }
  /* ----------------------------------------- */
  #extraDesc {
    font-family: "Inter";
    font-style: normal;
    font-weight: 600;
    font-size: 1rem;
    line-height: 24px;
    text-align: center;
    color: #47474b;
  }
  .btn-datatype {
    width: 10%;
    height: 35px;
    border: none;
    color: rgb(255, 255, 255);
    font-weight: 600;
    background: #3695be;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 12px;
    margin-right: 10px;
    margin-left: 10px;
  }
  .btn-datatype:hover {
    width: 10%;
    height: 35px;
    border: none;
    color: #3695be;
    border: solid 2px #3695be;
    font-weight: 600;
    background: rgb(255, 255, 255);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 12px;
    margin-right: 10px;
    margin-left: 10px;
  }
  .description {
    text-align: right;
    margin-top: 2rem;
    font-size: 15px;
    font-weight: 400;
    color:rgb(118, 118, 118);
    margin-right: 1rem;
  } 
  #btn-section {
    margin-top: 30px;
    /* 자식 요소(.child)를 중앙 정렬하기 */
    display: flex;
    justify-content : center;
  }
  #container {
    width: 100%;
  }
</style>