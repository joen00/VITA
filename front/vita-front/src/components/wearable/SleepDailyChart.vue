<template>
    <div id="sleepGraph">
      <div id="chart">
        <apexchart
          type="rangeBar"
          height="350"
          :options="chartOptions"
          :series="series"
        ></apexchart>
      </div>
    </div>
  </template>
    
  <script>
  import VueApexCharts from "vue-apexcharts";
  const sleep_data = [
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T01:21:00+09:00",
        "dailySleepEnd": "2023-03-14T01:47:00+09:00",
        "dailySleepTotal": 26
    },
    {
        "dailySleepStage": "DEEP",
        "dailySleepStart": "2023-03-14T01:47:00+09:00",
        "dailySleepEnd": "2023-03-14T02:12:00+09:00",
        "dailySleepTotal": 25
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T02:12:00+09:00",
        "dailySleepEnd": "2023-03-14T02:15:00+09:00",
        "dailySleepTotal": 3
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:15:00+09:00",
        "dailySleepEnd": "2023-03-14T02:18:00+09:00",
        "dailySleepTotal": 3
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T02:18:00+09:00",
        "dailySleepEnd": "2023-03-14T02:19:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:19:00+09:00",
        "dailySleepEnd": "2023-03-14T02:24:00+09:00",
        "dailySleepTotal": 5
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T02:24:00+09:00",
        "dailySleepEnd": "2023-03-14T02:25:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:25:00+09:00",
        "dailySleepEnd": "2023-03-14T02:26:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T02:26:00+09:00",
        "dailySleepEnd": "2023-03-14T02:27:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:27:00+09:00",
        "dailySleepEnd": "2023-03-14T02:42:00+09:00",
        "dailySleepTotal": 15
    },
    {
        "dailySleepStage": "DEEP",
        "dailySleepStart": "2023-03-14T02:42:00+09:00",
        "dailySleepEnd": "2023-03-14T02:52:00+09:00",
        "dailySleepTotal": 10
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:52:00+09:00",
        "dailySleepEnd": "2023-03-14T02:53:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "DEEP",
        "dailySleepStart": "2023-03-14T02:53:00+09:00",
        "dailySleepEnd": "2023-03-14T02:54:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T02:54:00+09:00",
        "dailySleepEnd": "2023-03-14T02:55:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "DEEP",
        "dailySleepStart": "2023-03-14T02:55:00+09:00",
        "dailySleepEnd": "2023-03-14T02:58:00+09:00",
        "dailySleepTotal": 3
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T02:58:00+09:00",
        "dailySleepEnd": "2023-03-14T03:00:00+09:00",
        "dailySleepTotal": 2
    },
    {
        "dailySleepStage": "LIGHT",
        "dailySleepStart": "2023-03-14T03:00:00+09:00",
        "dailySleepEnd": "2023-03-14T03:07:00+09:00",
        "dailySleepTotal": 7
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T03:57:00+09:00",
        "dailySleepEnd": "2023-03-14T03:58:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T04:46:00+09:00",
        "dailySleepEnd": "2023-03-14T04:47:00+09:00",
        "dailySleepTotal": 1
    },
    {
        "dailySleepStage": "AWAKE",
        "dailySleepStart": "2023-03-14T05:07:00+09:00",
        "dailySleepEnd": "2023-03-14T05:12:00+09:00",
        "dailySleepTotal": 5
    }
]
  
  export default {
    name: "SleepGraph",
    components: {
      apexchart: VueApexCharts,
    },
    props: {
        data: Array
    },
    data: function () {
      return {
        chartOptions: {
          chart: {
            heigth: 450,
            type: "rangeBar",
          },
          xaxis: {
            type: "datetime",
          },
          plotOptions: {
            bar: {
              horizontal: true,
              barHeight: "80%",
            },
          },
          stroke: {
            width: 1,
          },
          fill: {
            type: "solid",
            opacity: 0.6,
          },
          legend: {
            position: "top",
            horizontalAlign: "left",
          },
        },
  
        series: [
          {
            name: "비수면",
            data: this.data
              .filter((items) => items.dailySleepStage == "AWAKE")
              .map((filterd_items) => {
                return {
                  x: 
                  new Date(filterd_items.dailySleepStart).getYear() + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getMonth() ,
                  y: [
                    new Date(filterd_items.dailySleepStart).getTime(),
                    new Date(filterd_items.dailySleepEnd).getTime(),
                  ],
                };
              }),
          },
          {
            name: "렘수면",
            data: this.data
              .filter((items) => items.dailySleepStage == "REM")
              .map((filterd_items) => {
                return {
                  x: 
                  new Date(filterd_items.dailySleepStart).getYear() + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getMonth() ,
                  y: [
                    new Date(filterd_items.dailySleepStart).getTime(),
                    new Date(filterd_items.dailySleepEnd).getTime(),
                  ],
                };
              }),
          },
          {
            name: "깊은수면",
            data: this.data
              .filter((items) => items.dailySleepStage == "DEEP")
              .map((filterd_items) => {
                return {
                  x: 
                  new Date(filterd_items.dailySleepStart).getYear() + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getMonth() ,
                  y: [
                    new Date(filterd_items.dailySleepStart).getTime(),
                    new Date(filterd_items.dailySleepEnd).getTime(),
                  ],
                };
              }),
          },
          {
            name: "얕은수면",
            data: this.data
              .filter((items) => items.dailySleepStage == "LIGHT")
              .map((filterd_items) => {
                return {
                  x: 
                  new Date(filterd_items.dailySleepStart).getYear() + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getMonth() ,
                  y: [
                    new Date(filterd_items.dailySleepStart).getTime(),
                    new Date(filterd_items.dailySleepEnd).getTime(),
                  ],
                };
              }),
          },
        ],
      };
    },
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
  #sleepGraph {
    width: 40rem;
    height: 400px;
    margin-top: 10rem;
    margin: auto;
  }
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
  </style>
  