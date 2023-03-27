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
        <div id="wearable-middle-left">
          
              <!-- 유저 아바타 -->
              <div id="wearable-profile-avatar">
                <img id="wearable-profile-img-avatar" style="width: 200px;" :src="require(`/public/user/avatar.png`)" />
              </div>
              <div id="wearable-profile-info">
                <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  {{ lastTotalscore.totalScoreEnergy }}
                </div>
                <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  {{ lastTotalscore.totalScoreRhr }}
                </div>
                <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  {{ lastTotalscore.totalScoreSleep }}
                </div>
                <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  {{ lastTotalscore.totalScoreStep }}
                </div>
                <div class="wearable-profile-info-p">
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>&nbsp;&nbsp;
                  {{ lastTotalscore.totalScoreStress }}
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
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;
                          margin-top: 1rem;
                          margin-bottom: -2rem;">현재 종합 점수</div>
              <div style="font-weight: 680;
                          font-size: 100px;
                          /* line-height: 180px; */
                          /* text-transform: uppercase; */
                          color: #5B5A63;"> {{ lastTotalscore.totalscore }} </div>
              <div style="font-size: 14px;
                          font-weight: 800;
                          color: #5B5A63;
                          margin-bottom: -0.5rem;">전체 순위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1등</div>
              <hr style="border: 1px solid #5B5A63;">
              <div style="font-size: 14px;
                          font-weight: 800;
                          color: #5B5A63;
                          margin-top: -0.5rem;">친구 순위&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1등</div>
            </div>
            <div id="wearable-middle-right-div-right">
              <div id="pastchart">
                <VueApexCharts
                  type="line"
                  height="260"
                  :options="chartOptions"
                  :series="series"
                ></VueApexCharts>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div id="wearable-footer">
        <wearable-total></wearable-total>
        <wearable-weight style="margin-top: 10rem;"></wearable-weight>
        <wearable-walk style="margin-top: 10rem;"></wearable-walk>
        <wearable-energy style="margin-top: 10rem;"></wearable-energy>
        <wearable-rhr style="margin-top: 10rem;"></wearable-rhr>
        <wearable-stress style="margin-top: 10rem;"></wearable-stress>
      </div>
    </div>
  </div>

</template>

<script>
import VueHeader from "@/components/common/VueHeader.vue";
import VueApexCharts from "vue-apexcharts";
import WearableTotal from "@/components/wearable/WearableTotal.vue";
import WearableWalk from "@/components/wearable/WearableWalk.vue";
import WearableWeight from "@/components/wearable/WearableWeight.vue";
import WearableEnergy from "@/components/wearable/WearableEnergy.vue";
import WearableRhr from "@/components/wearable/WearableRhr.vue";
import WearableStress from "@/components/wearable/WearableStress.vue";
import axios from "axios"
import html2canvas from "html2canvas"

export default {
  name: "FriendView",
  components: {
    VueHeader,
    VueApexCharts,
    WearableTotal,
    WearableWalk,
    WearableWeight,
    WearableEnergy,
    WearableRhr,
    WearableStress
  },
  data: () => ({
    totalscore:[],
    lastTotalscore:{},
    VueHeaderTitle: "마이 헬스 데이터",
    VueHeaderContent: "나의 종합 건강 점수를 확인해보세요.",
    series: [
      {
        name: "종합 점수",
        data: [],
      },
    ],
    chartOptions: {
        chart: {
          height: 200,
          type: "line",
          dropShadow: { // 그림자 넣는 곳
            enabled: true,
            color: "#000",
            top: 18,
            left: 6,
            blur: 10,
            opacity: 0.1,
          },
          toolbar: {
            show: false,
          },
        },
        colors: ["#77B6EA"], // 색상 지정하는 곳
        dataLabels: {
          enabled: true,
        },
        stroke: { // 그래프 형식 없으면 완젼 꺽은선으로 나옴
          curve: "smooth",
        },
        title: {
          text: "과거 종합 점수 그래프",
          align: "left",
        },
        grid: { // 이건 잘 모르겠음
          borderColor: "#e7e7e7",
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5,
          },
        },
        markers: {
          size: 1,
        },
        xaxis: {
          categories: [],
          // title: {
          //   text: "day",
          // },
        },
        yaxis: {
          // title: {
          //   text: "체중",
          // },
          min: 0,
          max: 100,
        },
        legend: {
          position: "top",
          horizontalAlign: "right",
          floating: true,
          offsetY: -25,
          offsetX: -5,
        },
      },
  }),
  created() {
    this.totalScore();
  },
  methods: {
        async totalScore() {
          await axios.get('https://j8b106.p.ssafy.io/api/wearable/' + 'score', {
            // await axios.get('http://localhost:8083/wearable/' + 'score', {
        headers: {'Content-Type': 'application/json',
                  'token': this.$store.state.test_token},
        }).then(res => {
          this.totalscore = res.data
          this.lastTotalscore = res.data[this.totalScore.length - 1]
        }) 
      this.series[0].data = this.totalscore.map(function (e) { 
        return e.totalScore;
      })
      this.chartOptions.xaxis.categories  = this.totalscore.map(function (e) { 
        const week = ['일', '월', '화', '수', '목', '금', '토'];
        const dayOfWeek = week[new Date(e.date).getDay()];
        return dayOfWeek;
      })
      console.log(this.chartOptions.xaxis.categories)
    },
    makePDF (selector = 'body') {
			window.html2canvas = html2canvas //Vue.js 특성상 window 객체에 직접 할당해야한다.
			let that = this
			let pdf = new jsPDF('p', 'mm', 'a4')
			let canvas = pdf.canvas
			const pageWidth = 210 //캔버스 너비 mm
			const pageHeight = 295 //캔버스 높이 mm
			canvas.width = pageWidth

			let ele = document.querySelector(selector)
			let width = ele.offsetWidth // 셀렉트한 요소의 px 너비
			let height = ele.offsetHeight // 셀렉트한 요소의 px 높이
			let imgHeight = pageWidth * height/width // 이미지 높이값 px to mm 변환

			if(!ele){
				console.warn(selector + ' is not exist.')
				return false
			}

			html2canvas(ele, {
				onrendered: function(canvas) {
					let position = 0
					const imgData = canvas.toDataURL('image/png')
					pdf.addImage(imgData, 'png', 0, position, pageWidth, imgHeight, undefined, 'slow')

					//Paging 처리
					let heightLeft = imgHeight //페이징 처리를 위해 남은 페이지 높이 세팅.
					heightLeft -= pageHeight
					while (heightLeft >= 0) {
						position = heightLeft - imgHeight
						pdf.addPage();
						pdf.addImage(imgData, 'png', 0, position, pageWidth, imgHeight)
						heightLeft -= pageHeight
					}

					pdf.save(that.propTitle.toLowerCase() +'.pdf')
				},

			});	

		},
      
    },
};
</script>

<style>
#wearable-middle{
  width: 100%;
  height: 300px;
  display: flex;

}
#wearable-middle-left{
  width: 30%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
#wearable-middle-right{
  width: 70%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wearable-profile-info-p{
  margin: 0.5rem;
  font-size: 18px;
  font-weight: 600;
  color: #172176;
}
#wearable-middle-right-div{
  width: 80%;
  height: 90%;
  background: #FFFFFF;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
#wearable-middle-right-div-left{
  width: 40%;
  height: 90%;
}
#wearable-middle-right-div-right{
  width: 60%;
  height: 90%;
}
#pastchart{
  width: 90%;
}
#wearable-footer{
  margin-top: 8rem;
}
</style>