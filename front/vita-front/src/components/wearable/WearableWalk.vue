<template>
  <div>
    <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
    <div id="walk-middle">
        <div id="walk-middle-left-btn">
          <div id="walk-middle-left-btn-up">
            <button id="btn-walk" v-on:click="weekWalk">1주</button>
            <br>
            <button id="btn-walk" v-on:click="monthWalk">1개월</button>
            <br>
            <button id="btn-walk" v-on:click="yearWalk">1년</button>
          </div>
          <div id="walk-middle-left-btn-down">
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">걸음수</div>
            <div style="font-size: 18px;
                        font-weight: 800;
                        color: #5B5A63;">종합점수</div>
            <div style="font-size: 60px;
                        margin-top: -0.8rem;
                        font-weight: 800;
                        color: #5B5A63;">80</div>
          </div>
        </div>
        <div id="walk-middle-left">
          <!-- 그래프 그려지는 곳 -->
          <!-- <div id="walkchart"></div> -->
          <WalkChart :key="componentKey" :data="data" />
        </div>
        <div id="walk-middle-right">
          <div id="walk-middle-right-div">
            <div>이번주 걸음수가 그 전주보다 줄었습니다.</div>
          </div>
          <div id="walk-middle-right-div">a</div>
          <div id="walk-middle-right-div">dfdfz</div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ComponentHeader from "@/components/common/ComponentHeader.vue";
import WalkChart from "@/components/wearable/WalkChart.vue";

export default {
  name: "WearableWalk",
  components: {
      ComponentHeader,
      WalkChart
    },
  data: () => ({
        ComponentHeaderTitle: "걸음수",
        ComponentHeaderContent: "나의 걸음수 기록을 보여줘요.",
        data: [],
        past: null,
        componentKey: 0,
  }),
// 데이터 가져오는 곳
created() {
    this.weekWalk();
    this.pastAndNowWalk();
  },
  methods: {
      async weekWalk() {
        await axios.get(this.$store.state.url + 'step/daily' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data.map(function(e){
          // return {"Step": e.dailyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.dailyWearableStep, "date": e.date};
        })
        // this.data = res.data
      })
      this.componentKey += 1;  
      },
      async monthWalk() {
        await axios.get(this.$store.state.url + 'step/weekly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        // this.data = res.data
        this.data = res.data.map(function(e){
          // return {"Step": e.weeklyWearableStep, "date": e.date.slice(0,10)};
          return {"Step": e.weeklyWearableStep, "date": e.date};
        })
      })
      this.componentKey += 1;  
      },
      async yearWalk() {
        await axios.get(this.$store.state.url + 'step/monthly' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.data = res.data
        this.data = res.data.map(function(e){
          return {"Step": e.monthlyWearableStep, "date": e.date};
        })
        this.componentKey += 1; 
      })
      },
      async pastAndNowWalk() {
        await axios.get(this.$store.state.url + 'step/past' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.past = res.data
      })
      },
    },


}
</script>

<style>
#walk-middle{
    height: 400px;
    /* background-color: aqua; */
    margin: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
#walk-middle-left-btn{
    width: 12%;
    height: 100%;
    /* display: flex; */
    /* justify-content: center;
    align-items: center; */
    /* background-color: rgb(255, 255, 255); */
}
#walk-middle-left{
    width: 44%;
    height: 100%;
    align-items: center;
    background-color: rgb(255, 255, 255);
    background: #FFFFFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 20px;
}

#walk-middle-right{
    margin-left: 2%;
    width: 42%;
    height: 100%;
    /* background-color: rgb(0, 0, 0); */
    display: flex; 
    flex-direction: column;
    justify-content: space-between;
    /* align-items: center; */
}

#btn-walk {
  width: 70%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-bottom: 1rem;
}
#btn-walk:hover {
  width: 70%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
}
#walk-middle-left-btn-up{
  height: 70%;
}
#walkchart {
  width: 150%;
  height: 90%;
  margin-top: 1rem;
  /* margin: auto; */
}
#walk-middle-right-div{
  width: 100%;
  height: 28%;
  background: #FFFFFF;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;

  /* display: flex; 
  flex-direction: column;
  justify-content: space-between; */
}
</style>