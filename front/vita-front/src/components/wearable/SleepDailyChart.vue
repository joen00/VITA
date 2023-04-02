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
                  (new Date(filterd_items.dailySleepStart).getMonth()+1) + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getDate() ,
                  y: [
                    new Date(2023,1,1,
                      new Date(filterd_items.dailySleepStart).getHours(),
                      new Date(filterd_items.dailySleepStart).getMinutes())
                    .getTime(),
                      new Date(2023,1,1,
                      new Date(filterd_items.dailySleepEnd).getHours(),
                      new Date(filterd_items.dailySleepEnd).getMinutes())
                    .getTime()
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
                  (new Date(filterd_items.dailySleepStart).getMonth()+1) + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getDate() ,
                  y: [
                    new Date(2023,1,1,
                      new Date(filterd_items.dailySleepStart).getHours(),
                      new Date(filterd_items.dailySleepStart).getMinutes())
                    .getTime(),
                      new Date(2023,1,1,
                      new Date(filterd_items.dailySleepEnd).getHours(),
                      new Date(filterd_items.dailySleepEnd).getMinutes())
                    .getTime()
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
                  (new Date(filterd_items.dailySleepStart).getMonth()+1) + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getDate() ,
                  y: [
                    new Date(2023,1,1,
                      new Date(filterd_items.dailySleepStart).getHours(),
                      new Date(filterd_items.dailySleepStart).getMinutes())
                    .getTime(),
                      new Date(2023,1,1,
                      new Date(filterd_items.dailySleepEnd).getHours(),
                      new Date(filterd_items.dailySleepEnd).getMinutes())
                    .getTime()
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
                  (new Date(filterd_items.dailySleepStart).getMonth()+1) + 
                  "-" +
                  new Date(filterd_items.dailySleepStart).getDate() ,
                  y: [
                    new Date(2023,1,1,
                      new Date(filterd_items.dailySleepStart).getHours(),
                      new Date(filterd_items.dailySleepStart).getMinutes())
                    .getTime(),
                      new Date(2023,1,1,
                      new Date(filterd_items.dailySleepEnd).getHours(),
                      new Date(filterd_items.dailySleepEnd).getMinutes())
                    .getTime()
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
  