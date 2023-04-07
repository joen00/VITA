<template>
  <div class="text-center">
    <div class="container">
      <div class="header">
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>
      <!--  중간 유저 정보부분 -->
      <div id="wearable-middle">
        <!--  유저 정보 왼쪽 부분 -->
        <div id="wearable-middle-left">
          <!-- 유저 아바타 -->
          <div
            id="wearable-middle-left-up"
            style="font-size: 1.7rem; font-weight: 800; margin-left: 5rem"
          >
          {{ nickname }}
          </div>
          <div id="wearable-middle-left-down">
            <div id="wearable-profile-avatar">
              <img
                id="wearable-profile-img-avatar"
                style="width: 160px"
                :src="require(`/public/user-avatar/${avatar}.png`)"
              />
            </div>
            <div id="wearable-profile-info">
              <div class="wearable-profile-info-p">개별 점수</div>
              <div class="wearable-profile-info-p">
                <b-avatar
                  variant="info"
                  :src="require(`/public/user/step_icon.png`)"
                ></b-avatar
                >&nbsp;&nbsp;
                {{ lastTotalscore.totalScoreStep }}
              </div>
              <div class="wearable-profile-info-p">
                <b-avatar
                  variant="info"
                  :src="require(`/public/user/energy_icon.png`)"
                ></b-avatar
                >&nbsp;&nbsp;
                {{ lastTotalscore.totalScoreEnergy }}
              </div>
              <div class="wearable-profile-info-p">
                <b-avatar
                  variant="info"
                  :src="require(`/public/user/rhr_icon.png`)"
                ></b-avatar
                >&nbsp;&nbsp;
                {{ lastTotalscore.totalScoreRhr }}
              </div>
              <div class="wearable-profile-info-p">
                <b-avatar
                  variant="info"
                  :src="require(`/public/user/stress_icon.png`)"
                ></b-avatar
                >&nbsp;&nbsp;
                {{ lastTotalscore.totalScoreStress }}
              </div>
              <div class="wearable-profile-info-p">
                <b-avatar
                  variant="info"
                  :src="require(`/public/user/sleep_icon.png`)"
                ></b-avatar
                >&nbsp;&nbsp;
                {{ lastTotalscore.totalScoreSleep }}
              </div>
            </div>
            <!-- <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  <p>{{ totalscore[2].totalScoreWeight }}</p>
                </div> -->
          </div>
        </div>
        <!--  중간 유저 정보 현재 종합 점수 & 과거 그래프 -->
        <div id="wearable-middle-right">
          <div id="wearable-middle-right-div">
            <div id="wearable-middle-right-div-left">
              <div
                style="
                  font-size: 18px;
                  font-weight: 800;
                  color: #5b5a63;
                  margin-top: 1rem;
                  margin-bottom: -2rem;
                "
              >
                현재 종합 점수
              </div>
              <div
                style="
                  font-weight: 680;
                  font-size: 100px;
                  /* line-height: 180px; */
                  /* text-transform: uppercase; */
                  color: #5b5a63;
                "
              >
                {{ lastTotalscore.totalScore }}
              </div>
              <!-- 전체 순위, 친구 순위 -->
              <!-- <div
                style="
                  font-size: 14px;
                  font-weight: 800;
                  color: #5b5a63;
                  margin-bottom: -0.5rem;
                "
              >
                전체 순위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1등
              </div>
              <hr style="border: 1px solid #5b5a63" />
              <div
                style="
                  font-size: 14px;
                  font-weight: 800;
                  color: #5b5a63;
                  margin-top: -0.5rem;
                "
              >
                친구 순위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1등
              </div> -->
              <!-- 전체 순위, 친구 순위 끝-->
              <b-button id="total-score-rank">전체 순위 : {{ totalRank }}등</b-button>
              <b-button id="total-score-rank">친구 순위 : {{ friendRank }}등</b-button>
            </div>

            <!-- 유저 종합점수 그래프 -->
            <div id="WearablePastTotal">
              <WearablePastTotal
                :key="componentKey"
                :data="data"
                :categories="categories"
              ></WearablePastTotal>
            </div>
          </div>
        </div>
      </div>

      <!-- 히스토리 이미지를 뽑아내기 위한 div 태그. 실제로는 아무것도 안보이지만 pdf 다운로드를 누르면 여기에 이미지가 생성되었다가 사라짐 -->
      <div id="history-img">
        <img src="" id="history-score-chart" width="100%">
        <div class="d-flex mb-5">
          <img src="" id="history-step-chart" width="33.33333%">
          <img src="" id="history-energy-chart" width="33.33333%">
          <img src="" id="history-rhr-chart" width="33.33333%">
        </div>
      </div>
      <!-- ---------------------------------------------------------------------------------------------------------------------- -->
      <div id="wearable-footer">
        <wearable-total></wearable-total>
        <wearable-weight
          style="margin-top: 10rem;"
          id="pdf-weight"
        ></wearable-weight>
        <wearable-step
          style="margin-top: 10rem"
          id="pdf-step"
          :score="lastTotalscore.totalScoreStep"
          ></wearable-step>
        <step-word style="margin-top: 15rem"></step-word>
        <wearable-energy
          style=" margin-top: 15rem"
          id="pdf-energy"
          :score="lastTotalscore.totalScoreEnergy"
        ></wearable-energy>
        <wearable-rhr
          style="margin-top: 10rem" 
          id="pdf-rhr"
          :score="lastTotalscore.totalScoreRhr"
          ></wearable-rhr>
        <wearable-stress
          style="margin-top: 10rem;"
          id="pdf-stress"
          :score="lastTotalscore.totalScoreStress"
        ></wearable-stress>
        <stress-word  style=" margin-top: 15rem"></stress-word>
        <wearable-sleep
          style="margin-top: 15rem; "
          id="pdf-sleep"
          :score="lastTotalscore.totalScoreSleep"
        ></wearable-sleep>
      </div>
      <!-- pdf 다운로드 버튼(클릭 시 버튼에 processing 애니메이션이 적용됨) -->
      <button v-if="pdfBtnState == 'normal'" id="btn-pdf" @click="getPdf">
        PDF 다운로드
      </button>
      <button v-else-if="pdfBtnState =='loading'" id="btn-pdf" @click="getPdf">
        다운로드 중...
        <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
      </button>
      
      <div class="mb-5"></div>
    </div>
  </div>
</template>

<script>
import VueHeader from "@/components/common/VueHeader.vue";
import WearableTotal from "@/components/wearable/WearableTotal.vue";
import WearableStep from "@/components/wearable/WearableStep.vue";
import WearableWeight from "@/components/wearable/WearableWeight.vue";
import WearableEnergy from "@/components/wearable/WearableEnergy.vue";
import WearableRhr from "@/components/wearable/WearableRhr.vue";
import WearableStress from "@/components/wearable/WearableStress.vue";
import WearableSleep from "@/components/wearable/WearableSleep.vue";
import WearablePastTotal from "@/components/wearable/WearablePastTotal.vue";
import StepWord from "@/components/wearable/StepWord.vue";
import StressWord from "@/components/wearable/StressWord.vue";
import axios from "axios";
import { mapGetters } from "vuex";
import html2canvas from "html2canvas";
import * as pdf from "@grapecity/wijmo.pdf";


export default {
  name: "WearableView",

  components: {
    VueHeader,
    WearableTotal,
    WearableStep,
    WearableWeight,
    WearableEnergy,
    WearableRhr,
    WearableStress,
    WearableSleep,
    WearablePastTotal,
    StepWord,
    StressWord,
  },
  data: () => ({
    data: [],
    categories: [],
    componentKey: 0,
    nickname: "유저 닉네임",
    avatar: "avatar0",
    totalscore: [],
    lastTotalscore: {},
    VueHeaderTitle: "마이 헬스 데이터",
    VueHeaderContent: "나의 종합 건강 점수를 확인해보세요.",
    // userNickname: user.userNickname,
    pdfBtnState: "normal",
    totalRank: 0,
    friendRank: 0,
  }),
  created() {
    this.totalScore();
    this.getUserInfo();
    this.getUserRank();
  },

  computed: {
    ...mapGetters(["token", "user"]),
    pdfBtnNormal() {
      return this.pdfBtnState = "normal"
    },
    pdfBtnLoading() {
      return this.pdfBtnState = "loading";
    },
  },

  methods: {
    async totalScore() {
      await axios
        .get("https://j8b106.p.ssafy.io/api/wearable/" + "score", {
          // await axios.get('http://localhost:8083/wearable/' + 'score', {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.totalscore = res.data;
          let lastIndex = this.totalscore.length - 1;
          this.lastTotalscore = this.totalscore[lastIndex];
          this.data = this.totalscore.map(function (e) {
            return e.totalScore;
          });
          this.categories = this.totalscore.map(function (e) {
            return e.createdDate;
          });
          this.componentKey += 1;
        });
    },

    // 유저 히스토리 이미지 저장 및 pdf 추출하는 함수
    async getPdf() {
      this.pdfBtnLoading;
      let userInfo = document.querySelector("#wearable-middle");
      let pdfWeight = document.querySelector("#pdf-weight");
      let pdfStep = document.querySelector("#pdf-step");
      let pdfEnergy = document.querySelector("#pdf-energy");
      let pdfRhr = document.querySelector("#pdf-rhr");
      let pdfStress = document.querySelector("#pdf-stress");
      let pdfSleep = document.querySelector("#pdf-sleep");
      let pdfScore = document.querySelector("#history-img");

      let doc = new pdf.PdfDocument({
        header: {
          height: 0, // no header
        },
        footer: {
          height: 0, // no footer
        },
        ended: (sender, args) => pdf.saveBlob(args.blob, "Document.pdf"),
      });

      // ----------- 히스토리 이미지 만드는 부분 ------------------------
      // 유저의 종합 점수, 과거 점수 정보 추출
      await html2canvas(userInfo, { backgroundColor: "#E2F2FA", height: 380, y: -20 }).then((canvas) => {
        document.querySelector("#history-score-chart").setAttribute("src", canvas.toDataURL());
        doc.drawImage(canvas.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
      })

      // 체중 차트 추출
      await html2canvas(pdfWeight, {
        backgroundColor: "#E2F2FA",
        height: 700,
        y: -60,
      }).then((dataUrl) => {
        doc.drawImage(dataUrl.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
      });

      // 걸음수 추출
      await html2canvas(pdfStep, { backgroundColor: "#E2F2FA", height: 700, y: -60 }).then((canvas) => {
        document.querySelector("#history-step-chart").setAttribute("src", canvas.toDataURL());
        doc.drawImage(canvas.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
        doc.addPage();
      })

      // 활동 에너지 추출
      await html2canvas(pdfEnergy, { backgroundColor: "#E2F2FA", height: 700, y: -60 }).then((canvas) => {
        document.querySelector("#history-energy-chart").setAttribute("src", canvas.toDataURL());
        doc.drawImage(canvas.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
      })

      // 심박변이 추출
      await html2canvas(pdfRhr, { backgroundColor: "#E2F2FA", height: 700, y: -60 }).then((canvas) => {
        document.querySelector("#history-rhr-chart").setAttribute("src", canvas.toDataURL());
        doc.drawImage(canvas.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
        doc.addPage();
      })
      

      // 스트레스 차트 추출
      await html2canvas(pdfStress, {
        backgroundColor: "#E2F2FA",
        height: 700,
        y: -60,
      }).then((dataUrl) => {
        doc.drawImage(dataUrl.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
      });

      // 수면 차트 추출
      await html2canvas(pdfSleep, {
        backgroundColor: "#E2F2FA",
        height: 700,
        y: -60,
      }).then((dataUrl) => {
        doc.drawImage(dataUrl.toDataURL(), null, null, {
          width: 480,
        });
        doc.moveDown();
      });

      // 만들어진 히스토리 이미지를 DB에 저장
      await html2canvas(pdfScore).then((dataUrl) => {
        console.log(dataUrl.toDataURL());
        axios.post(
          this.$store.state.serverBaseUrl + `/wearable/savehistory`,
          { image: dataUrl.toDataURL() },
          {
            headers: {
              Authorization: `Bearer ${this.token}`,
            },
          }
        ).then((response) => {
        });
      })

      doc.end();
      // 버튼을 원래 상태로 복구
      this.pdfBtnNormal;
      // 히스토리 이미지 추출용 태그들을 모두 제거함
      document.getElementById("history-img").remove();
    },


    getUserInfo() {
      axios
        .get(this.$store.state.serverBaseUrl + `/users/mypage`, {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          this.nickname = response.data.userNickname;
          this.avatar = response.data.userAvatar;
        });
    },
    getUserRank() {
      axios
        .get(this.$store.state.serverBaseUrl + "/wearable/user/score/rank", {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          this.totalRank = response.data[0];
          this.friendRank = response.data[1];
        });  
    }
  },
};
</script>

<style>
#btn-pdf {
  width: 20%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-bottom: 1rem;
}
#btn-pdf:hover {
  width: 20%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
}

#total-score-rank {
  width: 60%;
  height: 35px;
  border: none;
  color: #3695be;
  font-weight: 600;
  background: #cfeaf7;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-top: -0.5rem;
  margin-bottom: 1.5rem;
}

#total-score-rank:hover {
  width: 60%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-top: -0.5rem;
  margin-bottom: 1.5rem;
}

#wearable-middle-left {
  width: 35%;
  height: 100%;
  /* display: flex;
  justify-content: center;
  align-items: center; */
}
#wearable-middle-left-down {
  /* width: 35%;
  height: 100%; */
  margin-top: 0.5rem;
  margin-left: 4rem;
  display: flex;
  justify-content: center;
  align-items: end;
}
#wearable-profile-img-avatar {
  background-color: #ace3fa;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 50px;
  margin-right: 1rem;
}
/* #wearable-profile-info {
  background-color: #ace3fa;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
} */

#wearable-middle {
  width: 100%;
  height: 330px;
  display: flex;
}

#wearable-middle-right {
  width: 65%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 4rem;
  margin-top: 0.5rem;
}
.wearable-profile-info-p {
  margin: 0.5rem;
  font-size: 18px;
  font-weight: 600;
  color: #172176;
}
#wearable-middle-right-div {
  width: 100%;
  height: 90%;
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#wearable-middle-right-div-left {
  width: 40%;
  height: 90%;
}
#wearable-middle-right-div-right {
  width: 60%;
  height: 90%;
}
#WearablePastTotal {
  margin-top: -1rem;
  width: 95%;
}
#wearable-footer {
  margin-top: 8rem;
}
#history-charts {
  width: 20px;
}
</style>