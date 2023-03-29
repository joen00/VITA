<template>
  <div>
    <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
    <div id="total-middle">
        <div id="total-middle-left">
            <div id="total-middle-left-div">
                <div id="chart">
                    <!-- <apexchart type="heatmap" height="300" :options="chartOptions" :series="data"></apexchart> -->
                    <apexchart type="heatmap" height="300" :options="chartOptions" :series="data"></apexchart>
                </div>


            </div>
        </div>
        <div id="total-middle-right">
            <button id="btn-year" v-on:click="getTotalScore(2023)">2023</button>
            <br>
            <button id="btn-year" v-on:click="getTotalScore(2022)">2022</button>
            <br>
            <button id="btn-year" v-on:click="getTotalScore(2021)">2021</button>
        </div>
    </div>
  </div>
</template>

<script>
import ComponentHeader from "@/components/common/ComponentHeader.vue";
import VueApexCharts from "vue-apexcharts";
import axios from 'axios'
import { mapGetters } from "vuex";

function generateData(count, yrange) {
    var i = 0;
    var series = [];
    while (i < count) {
    var x = (i + 1).toString();
    var y =
    Math.floor(Math.random() * (yrange.max - yrange.min + 1)) + yrange.min;

    series.push({
        x: x,
        y: y
    });
    i++;
    }
    return series;
}

export default {
  name: "WearableTotal",
  components: {
      ComponentHeader,
      apexchart: VueApexCharts,
    },
    data: () => ({
        ComponentHeaderTitle: "종합점수 잔디",
        ComponentHeaderContent: "나의 종합점수 기록을 보여줘요.",
        // data: [],
        data: [{
            name: '1월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '2월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '3월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '4월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '5월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '6월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '7월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '8월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },
          {
            name: '9월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
            },
            {
            name: '10월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },{
            name: '11월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          },{
            name: '12월',
            data: generateData(31, {
              min: 0,
              max: 90
            })
          }
          ],
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
    }),
    created() {
      this.getTotalScore(2023);
    },

    computed: {
    ...mapGetters(["token", "user"]),
    },
  
    methods: {
      getTotalScore(year) {
        // axios.get(this.$store.state.url + 'score/' + year , {
        axios.get(this.$store.state.url + "score/" + year , {
      headers: {'Content-Type': 'application/json',
                Authorization: `Bearer ${this.token}`,},
      }).then(res => {
        // console.log(res.data)
        this.data = res.data
        // this.data = res.data.map(function(e){
        //   return {"dailyWearableScore": e.dailyWearableScore, "date": e.date};
        // })
        res.data.map(function(e){
          this.data["name"]
        })
        // this.data = res.data
      })
      // this.componentKey += 1;  
      },
    }
}
</script>

<style>
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
#total-middle-left-div{
    width: 100%;
    height: 100%;
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
}
#total-middle-right{
    width: 20%;
    height: 100%;
}

#btn-year {
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
}

</style>