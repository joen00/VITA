<template>
  <div>
    <div id="main-middle">
      <div id="main-top">
        <div id="main-top-title">
          <div id="main-top-title-p">건강한 일상을 위한 지름길</div>
          <div id="main-top-title-p">VITA와 함께 건강을 시각화하다</div>
          <b-button
            class="btn"
            v-b-modal.modal-scrollable
            id="btn-page"
            to="/wearables"
            >VITA 시작하기</b-button
          >
        </div>
        <div id="main-top-img">
          <img style="width: 400px" src="../../public/main/watch3d.gif" />
        </div>
      </div>

      <div id="main-middle1">
        <div id="main-middle1-title">
          나의 건강상태 알아보고 싶었던 적 없나요?
        </div>
        <div id="main-middle1-div">
          <div id="main-middle1-div-item" data-sal="slide-right" style="--sal-duration: 1s;" data-sal-repeat>
            <img
              style="width: 70%; height: 70%"
              src="../../public/main/mainre1.png"
            />
            <pre id="main-middle1-div-p" style="height: 15%">
“건강정보 받고 싶은데,
        모르겠어요.”</pre
            >
            <div
              style="
                font-weight: 550;
                color: gray;
                font-size: 12px;
                margin-top: 1rem;
                height: 10%;
              "
            >
              20살 대학생 김00
            </div>
          </div>
          <div id="main-middle1-div-item" data-sal="slide-up" style="--sal-duration: 1s; " data-sal-repeat>
            <img
              style="width: 70%; height: 70%"
              src="../../public/main/mainre2.png"
            />
            <pre id="main-middle1-div-p" style="height: 15%">
“내가 건강한지 주변사람들과 
         비교하고 싶어요”</pre
            >
            <div
              style="
                font-weight: 550;
                color: gray;
                font-size: 12px;
                margin-top: 1rem;
                height: 10%;
              "
            >
              50살 주부 이00
            </div>
          </div>
          <div id="main-middle1-div-item" data-sal="slide-left" style="--sal-duration: 1s; " data-sal-repeat>
            <img
              style="width: 70%; height: 70%"
              src="../../public/main/mainre4.png"
            />
            <pre id="main-middle1-div-p" style="height: 15%">
“현재의 나와 과거의 나를 
       비교하고싶어요”</pre
            >
            <div
              style="
                font-weight: 550;
                color: gray;
                font-size: 12px;
                margin-top: 1rem;
                height: 10%;
              "
            >
              30살 회사원 박00
            </div>
          </div>
        </div>
      </div>
      <div id="main-middle2">
        <div id="main-middle2-div-title">
          <p>VITA가 제안하는</p>
          <p>건강리포트</p>
        </div>
        <!-- middle2 -->
        <div id="main-middle2-div" data-sal="fade" style="--sal-duration: 1s; " data-sal-repeat>
          <!-- div 1 -->
          <div id="main-middle2-div-img">
            <img style="width: 70%" src="@/../public/main/animation2.gif" />
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4e8aff">
              Step 1.
            </p>
            <pre
              style="
                font-size: 1.2rem;
                font-weight: 550;
                font-family: 'Inter';
                font-style: normal;
              "
            >
웨어러블 앱에서 건강정보
내보내기
            </pre>
            <pre
              style="
                color: gray;
                margin-top: -1rem;
                font-size: 1rem;
                font-weight: 800;
                font-family: 'Inter';
                font-style: normal;
              "
            >
가이드를 따라,
웨어러블 건강정보를 업로드하세요.
            </pre>
          </div>
          <!-- div1 끝 -->
        </div>
        <div id="main-middle2-div" data-sal="fade" style="--sal-duration: 1s; " data-sal-repeat>
          <!-- div2 시작 -->
          <div id="main-middle2-div-img">
            <img style="width: 77%" src="@/../public/main/animation3.gif" />
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4e8aff">
              Step 2.
            </p>
            <pre
              style="
                font-size: 1.2rem;
                font-weight: 550;
                font-family: 'Inter';
                font-style: normal;
              "
            >
HealthReport를 통해
나의 건강 정보를 확인하세요.
            </pre>
            <pre
              style="
                color: gray;
                margin-top: -1rem;
                font-size: 1rem;
                font-weight: 800;
                font-family: 'Inter';
                font-style: normal;
              "
            >
그래프를 통해 한눈에 건강정보를 확인하고,
과거의 데이터와 비교할 수 있습니다.
            </pre>
          </div>
          <!-- div2 끝 -->
        </div>
        <div id="main-middle2-div" data-sal="fade" style="--sal-duration: 1s; " data-sal-repeat>
          <!-- div3 시작 -->
          <div id="main-middle2-div-img">
            <img style="width: 77%" src="@/../public/main/animation4.gif" />
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4e8aff">
              Step 3.
            </p>
            <pre
              style="
                font-size: 1.2rem;
                font-weight: 550;
                font-family: 'Inter';
                font-style: normal;
              "
            >
친구들과 건강을 비교해보세요.
            </pre>
            <pre
              style="
                color: gray;
                margin-top: -1rem;
                font-size: 1rem;
                font-weight: 800;
                font-family: 'Inter';
                font-style: normal;
              "
            >
친구들을 등록한 후,
건강 비교와 랭킹을 확인해보세요.
            </pre>
          </div>
          <!-- div3 끝 -->
        </div>
      </div>
      <div id="main-footer">
        <div id="main-footer-title-p">여러분의 건강을 관리하고,</div>
        <div id="main-footer-title-p">
          더 나은 생활을 위한 정보를 제공하는 Vita와 함께하세요.
        </div>
        <b-button
          class="btn"
          v-b-modal.modal-scrollable
          id="btn-footer-page"
          to="/wearables"
          >VITA 시작하기</b-button
        >
        <div class="kakao-share" @click="kakaoShare">카카오 공유하기</div>
      </div>
    </div>
  </div>
</template>

<script>
import sal from "sal.js";
export default {
  name: "MainView",
  mounted() {
    sal();
  },
  head() {
    return {
      script: [{ src: "//developers.kakao.com/sdk/js/kakao.min.js" }],
    };
  },

  methods: {
    kakaoShare() {
      window.Kakao.Share.sendCustom({
        templateId: 91901,
        installTalk: true,
      });
    },
  },
};
</script>

<style>
.kakao-share {
  width: 300px;
  height: 50px;
  line-height: 50px;
  color: black;
  text-align: center;
  background: #ffe812;
  font-size: 15px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 1rem;
  width: 15%;
  height: 45px;
  border: none;
  font-weight: 600;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
}

#main-middle2-div-img {
  width: 50%;
  height: 100%;
  /* background-color: #3695be; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#main-middle2-div-items {
  width: 50%;
  height: 100%;
  /* background-color: #d1d7d9; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 10%;
  padding-right: 10%;
  /* align-items: center; */
}

#main-top-img {
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* margin-left: 10rem; */
}
#main-middle {
  height: 490vh;
  background-color: #ffffff;
}

#main-middle1 {
  height: 100vh;
  background-color: #ffffff;
  /* background-color: #172176; */
}

#main-middle1-title {
  height: 15%;
  /* background-color: #3695be; */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}
#main-middle1-div {
  height: 80%;
  background-color: #ebf7fd;
  display: flex;
  justify-content: center;
  align-items: center;
}
#main-middle1-div-item {
  width: 280px;
  height: 350px;
  left: 215px;
  top: 1688px;
  background: #ffffff;
  box-shadow: 0px 0px 15px #d0ecfd;
  border-radius: 30px;
  margin: 2.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
#main-middle1-div-p {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  margin-bottom: 1rem;
}

#main-middle2 {
  height: 300vh;
  background-color: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding-left: 15rem;
  padding-right: 15rem;
}

#main-middle2-div {
  width: 100%;
  height: 100vh;
  /* background-color: #c8cdff; */
  display: flex;
  justify-content: center;
  align-items: center;
  /* flex-direction: column; */
}
#main-middle2-div-title {
  width: 100%;
  height: 50vh;
  /* background-color: #c8cdff; */
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}

#main-footer {
  height: 50vh;
  background-color: #ffffff;
  height: 710px;
  background-size: cover;
  background-position: center;
  overflow: auto;
  background-image: url("@/../public/main/footer.png");
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}

#btn-footer-page {
  margin-top: 1rem;
  width: 15%;
  height: 45px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#btn-footer-page:hover {
  margin-top: 1rem;
  width: 15%;
  height: 45px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#btn-page {
  margin-top: 1rem;
  width: 40%;
  height: 45px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#btn-page:hover {
  margin-top: 1rem;
  width: 40%;
  height: 45px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
}

#main-top-title-p {
  margin-bottom: -1rem;
}

#main-top-title {
  /* width: 703px;
  height: 123px; */
  width: 30%;
  height: 100%;
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 27px;
  line-height: 61px;
  text-align: center;
  color: #172176;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 10rem;
}
#main-top {
  height: 710px;
  background-size: cover;
  background-position: center;
  overflow: auto;
  background-color: #ffffff;
  background-image: url("@/../public/main/Group.png");
  background-repeat: no-repeat;
  display: flex;
  margin-bottom: 5rem;
}
</style>
