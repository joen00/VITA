<template>
  <div>
    <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
    <div id="total-middle">
        <div id="total-middle-left">
            <div id="total-middle-left-div-mom">
                <div id="chart">
                    <apexchart type="heatmap" height="300" :options="chartOptions" :series="data"></apexchart>
                </div>
                <div v-if="infovalue" id="total-middle-left-div-child">
                    매일의 걸음수, 활동에너지, 심박수등 여러 항목을 종합하여 Vita만의 점수를 산출하여 나타낸 종합점수입니다.
                </div>

            </div>
        </div>
        <div id="total-middle-right">
            <!-- <button id="btn-year" v-on:click="getTotalScore(2023)">2023</button>
            <br>
            <button id="btn-year" v-on:click="getTotalScore(2022)">2022</button>
            <br>
            <button id="btn-year" v-on:click="getTotalScore(2021)">2021</button> -->
            <input type="radio" name="btn-year" id="2023" value="2023" v-on:click="getTotalScore(2023)" checked>
            <label for="2023">2023</label>

            <input type="radio" name="btn-year" id="2022" value="2022" v-on:click="getTotalScore(2022)">
            <label for="2022">2022</label>

            <input type="radio" name="btn-year" id="2021" value="2021" v-on:click="getTotalScore(2021)">
            <label for="2021">2021</label>
            <div>
              <img @click="info()" style="width: 10%" src="@/../public/wearable/question.png" />
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import ComponentHeader from "@/components/common/ComponentHeader.vue";
import VueApexCharts from "vue-apexcharts";
import axios from 'axios'
import { mapGetters } from "vuex";

export default {
  name: "WearableTotal",
  components: {
      ComponentHeader,
      apexchart: VueApexCharts,
    },

    data: () => ({
        ComponentHeaderTitle: "종합점수 잔디",
        ComponentHeaderContent: "나의 종합점수 기록을 보여줘요.",
        data: [],
        chartOptions: {
          chart: {
            height: 300,
            type: 'heatmap',
          },
          dataLabels: {
            enabled: false
          },
          colors: ["#008FFB"],
        },
        // 설명란
        infovalue:false,
    }),

    created() {
      this.getTotalScore(2023);
    },

    computed: {
    ...mapGetters(["token", "user"]),
    },
  
  methods: {
    info() { 
          if (this.infovalue == true) {
            this.infovalue = false;
          } else { 
            this.infovalue = true;
          }
          console.log(this.infovalue)
    },
      getTotalScore(year) {
        // axios.get(this.$store.state.url + 'score/' + year , {
        axios.get(this.$store.state.url + "score/" + year , {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${this.token}`,
      },
        }).then(res => {
          this.data = res.data;
          },
        )} 
    }
}
</script>

<style scoped>
#total-middle{
    height: 300px;
    /* background-color: aqua; */
    margin: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
#total-middle-left{
    width: 80%;
    height: 100%;
    display: flex;
    /* justify-content: center;
    align-items: center; */
    /* background-color: rgb(56, 160, 160); */
}
/* 여기 */
#total-middle-left-div-mom{
    width: 100%;
    height: 100%;
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
    position: relative;
}
#total-middle-left-div-child{
    width: 100%;
    height: 100%;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
    /* 부모랑 똑같이 */
    position: absolute;
    left: 0;
    top: 0;
    z-index: 9;
    opacity: 0.45;
    display: flex;
    justify-content: space-around;
    align-items: center;
    flex-direction: column;
    font-size: 1.2rem;
    padding: 4rem;
    background-color: #bbe5f7;
    color:black;
}
#total-middle-right{
    width: 20%;
    height: 100%;
}

/* #btn-year {
  width: 60%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-bottom: 1rem;
}
#btn-year:hover {
  width: 60%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
} */

input[type="radio"] {
  display: none;
}

input[type="radio"] + label {
  display: inline-block;
  width: 70%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-bottom: 1rem;
  text-align: center;
  line-height: 35px;
}

input[type="radio"]:checked + label {
  width: 70%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  cursor: pointer;
}

input[type="radio"]:hover + label {
  cursor: pointer;
}

</style>