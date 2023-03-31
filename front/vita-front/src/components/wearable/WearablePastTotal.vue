<template>
    <div>
        <div id="wearable-middle-right-div-right">
            <div style="font-size: 1.1rem; font-weight: 800">
            과거 종합 점수 그래프
            </div>
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
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
    name: "WearablePastTotal",
    components: {
        VueApexCharts,
    },
    props: {
        data: Array,
        categories: Array,
    },
    data: function () {
    return {
        chartOptions: {
            chart: {
            height: 200,
            type: "line",
            dropShadow: {
                // 그림자 넣는 곳
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
            stroke: {
            // 그래프 형식 없으면 완젼 꺽은선으로 나옴
            curve: "smooth",
            },
            // title: {
            //   text: "과거 종합 점수 그래프",
            //   align: "left",
            // },
            grid: {
            // 이건 잘 모르겠음
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
            categories: categories,
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

            series: [
                {
                name: "종합 점수",
                data: data,
                },
            ],
            }
        }
    }
    
</script>


<style>
#wearable-middle-right-div-right {
  width: 60%;
  height: 90%;
}
</style>