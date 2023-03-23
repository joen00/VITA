<template>
    <div id="backgroundcolor">
    <div class="text-center">
      <div class="container">
        <div id="header">
          <div id="header-title">마이 헬스 데이터 with 평균</div>
          <div id="header-content">사람들과 나의 건강 데이터를 비교해보세요.</div>
          <div id="header-content1">
            통계 자료에 기반한 건강 항목 별 평균 데이터와 나의 건강 데이터를 비교해줍니다.
          </div>
  
            <b-button class="btn-datatype">전체</b-button>
            <b-button class="btn-datatype">연령 & 성별</b-button>
  
            <div class="description">
              평균 데이터와의 비교는 최근 1년 동안의 데이터를 기준으로 평균을 계산해
              비교해줍니다.
            </div>
            <!-- 여기서 부터 걸음수 컴포넌트 등장 -->
            <!-- <div style="clear: both"></div> -->
        </div>
        <div class="body">
            <b-container class="bv-example-row container" id="chart-container">
                <b-row>
                    <b-col><awearable-walk></awearable-walk></b-col>
                    <b-col><awearable-energy></awearable-energy></b-col>
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

  export default {
    name: "App",
    components: { 
        AwearableWalk,
        AwearableEnergy
    },
    data() {
      return {
        slides: [
          { id: 1, name: "김뿡" },
        ],
      };
    },
  };
</script>
    
<style>
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
    #header-content1 {
        font-family: "Inter";
        font-style: normal;
        font-weight: 600;
        font-size: 1rem;
        line-height: 24px;
        text-align: center;
        margin-top: 1rem;
        color: #47474b;
    }

    .btn-datatype {
        background-color: #3D4870;
        float: center;
        margin: 2.5rem;
        font-weight: 600;
        font-size: 1rem;
    }

    .description {
        text-align: right;
        margin-top: 1rem;
        font-size: 0.7rem;
        margin-right: 1rem;
    }  
</style>