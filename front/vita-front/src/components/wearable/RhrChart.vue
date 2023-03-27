<template>
    <div id="heartGraph">
      <div id="chart">
        <apexchart
          type="line"
          height="350"
          :options="chartOptions"
          :series="series"
        ></apexchart>
      </div>
      {{ data}}
    </div>
  </template>
  
  <script>
  import VueApexCharts from "vue-apexcharts";
  
  export default {
    name: "RhrGraph",
    components: {
      apexchart: VueApexCharts,
    },
    props: {
        data:Array,
        date:Array
    },
    data: function () {
      return {
        series: [
          {
            name: "Rhr",
            data: this.data
          },
        ],
        chartOptions: {
          chart: {
            height: 350,
            type: "line",
          },
          stroke: {
            width: 5,
            curve: "smooth",
          },
          xaxis: {
            type: "datetime",
            categories: this.date,
            tickAmount: 10,
            labels: {
              formatter: function (value, timestamp, opts) {
                // return opts.dateFormatter(new Date(timestamp), "dd MMM");
                return opts.dateFormatter(new Date(timestamp), "yyyy-MM-dd");
              },
            },
          },
          title: {
            text: "심박수",
            align: "left",
            style: {
              fontSize: "16px",
              color: "#666",
            },
          },
          fill: {
            type: "gradient",
            gradient: {
              shade: "dark",
              gradientToColors: ["#FDD835"],
              shadeIntensity: 1,
              type: "horizontal",
              opacityFrom: 1,
              opacityTo: 1,
            },
          },
        },
      };
    },
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>

  #heartGraph {
    /* width: 40rem;
    margin-top: 10rem;
    margin: auto; */
    width: 30rem;
    height: 400px;
  }
  </style>
  