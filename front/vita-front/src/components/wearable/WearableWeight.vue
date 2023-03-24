<template>
    <div>
      <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
      <div id="walk-middle">
          <div id="walk-middle-left-btn">
            <div id="walk-middle-left-btn-up">
              <button id="btn-walk">1주</button>
              <br>
              <button id="btn-walk">1개월</button>
              <br>
              <button id="btn-walk">1년</button>
            </div>
            <div id="walk-middle-left-btn-down">
              <div style="font-size: 18px;
                          font-weight: 800;
                          color: #5B5A63;">체중</div>
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
            <div id="weight1chartdiv"></div>
          </div>
          <div id="walk-middle-right">
            <div id="walk-middle-right-div">
              <div>이번주 평균 체중이 그 전주보다 줄었습니다.</div>
            </div>
            <div id="walk-middle-right-div">a</div>
            <div id="walk-middle-right-div">dfdfz</div>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  import ComponentHeader from "@/components/common/ComponentHeader.vue";
  import * as am5 from "@amcharts/amcharts5";
  import * as am5xy from "@amcharts/amcharts5/xy";
  import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
  
  export default {
    name: "WearableWalk",
    components: {
        ComponentHeader,
  
      },
    data: () => ({
          ComponentHeaderTitle: "체중",
          ComponentHeaderContent: "나의 BMI 기록을 보여줘요.",
    }),
    mounted() {
      am5.ready(() => {
        var root = am5.Root.new("weight1chartdiv"); // 'chartdiv'라는 id로 차트 생성
        root.setThemes([am5themes_Animated.new(root)]);
        var chart = root.container.children.push(
          am5xy.XYChart.new(root, {
            panX: false,
            panY: false,
            wheelX: "panX",
            wheelY: "zoomX",
            layout: root.horizontalLayout,
          })
        );
        // Add legend
        var legend = chart.children.push(
          am5.Legend.new(root, {
            y: am5.p100,
            x: am5.percent(10),
            layout: root.horizontalLayout,
            clickTarget: "none",
          })
        );
        legend.valueLabels.template.set("forceHidden", true);
        // Data
        var data = [
          { year: "21.10.03", 체중: 90.0, 골격근량: 37.5, 체지방량: 27.6 },
          { year: "21.10.04", 체중: 98.0, 골격근량: 34.6, 체지방량: 34.7 },
          { year: "21.10.04", 체중: 90.0, 골격근량: 36.7, 체지방량: 28.6 },
          { year: "21.10.05", 체중: 90.0, 골격근량: 36.8, 체지방량: 28.5 },
          { year: "21.10.05", 체중: 90.0, 골격근량: 37.5, 체지방량: 27.6 },
          { year: "21.10.06", 체중: 89.8, 골격근량: 36.1, 체지방량: 29.3 },
          { year: "21.10.06", 체중: 89.8, 골격근량: 38.0, 체지방량: 26.9 },
          { year: "21.10.07", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
          { year: "21.10.09", 체중: 90.3, 골격근량: 37.0, 체지방량: 28.3 },
          { year: "21.10.09", 체중: 90.3, 골격근량: 36.4, 체지방량: 29.2 },
          { year: "21.10.10", 체중: 88.8, 골격근량: 37.4, 체지방량: 27.3 },
          { year: "21.10.11", 체중: 89.5, 골격근량: 36.5, 체지방량: 28.7 },
          { year: "21.10.13", 체중: 89.9, 골격근량: 36.7, 체지방량: 28.6 },
          { year: "21.10.14", 체중: 89.0, 골격근량: 35.9, 체지방량: 29.4 },
          { year: "21.10.16", 체중: 88.8, 골격근량: 36.9, 체지방량: 27.9 },
          { year: "21.10.17", 체중: 88.1, 골격근량: 37.5, 체지방량: 26.9 },
          { year: "21.10.18", 체중: 89.1, 골격근량: 37.4, 체지방량: 27.4 },
          { year: "21.10.19", 체중: 88.9, 골격근량: 36.5, 체지방량: 28.4 },
          { year: "21.10.20", 체중: 88.9, 골격근량: 36.2, 체지방량: 28.8 },
          { year: "21.10.20", 체중: 89.2, 골격근량: 36.8, 체지방량: 28.2 },
          { year: "21.10.20", 체중: 89.7, 골격근량: 0, 체지방량: 0 },
          { year: "21.10.21", 체중: 89.4, 골격근량: 36.7, 체지방량: 28.4 },
          { year: "21.10.22", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
          { year: "21.10.23", 체중: 93.2, 골격근량: 33.6, 체지방량: 34.2 },
          { year: "21.10.23", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
          { year: "21.10.23", 체중: 89.0, 골격근량: 37.3, 체지방량: 27.5 },
          { year: "21.10.25", 체중: 89.0, 골격근량: 36.9, 체지방량: 28.1 },
          { year: "21.10.26", 체중: 89.2, 골격근량: 37.5, 체지방량: 27.3 },
          { year: "21.10.27", 체중: 89.0, 골격근량: 36.6, 체지방량: 28.3 },
          { year: "21.10.29", 체중: 88.3, 골격근량: 37.1, 체지방량: 27.5 },
          { year: "21.10.31", 체중: 88.5, 골격근량: 37.1, 체지방량: 27.6 },
          { year: "21.11.01", 체중: 88.9, 골격근량: 36.6, 체지방량: 28.3 },
          { year: "21.11.02", 체중: 88.9, 골격근량: 36.8, 체지방량: 28.1 },
          { year: "21.11.03", 체중: 88.4, 골격근량: 38.0, 체지방량: 26.4 },
          { year: "21.11.05", 체중: 88.3, 골격근량: 38.0, 체지방량: 26.2 },
          { year: "21.11.06", 체중: 88.4, 골격근량: 37.7, 체지방량: 26.8 },
          { year: "21.11.07", 체중: 89.6, 골격근량: 36.9, 체지방량: 28.3 },
          { year: "21.11.08", 체중: 88.4, 골격근량: 37.8, 체지방량: 26.6 },
          { year: "21.11.09", 체중: 88.5, 골격근량: 37.3, 체지방량: 27.4 },
          { year: "21.11.09", 체중: 91.0, 골격근량: 36.4, 체지방량: 29.4 },
          { year: "21.11.12", 체중: 87.9, 골격근량: 37.5, 체지방량: 26.9 },
          { year: "21.11.13", 체중: 87.2, 골격근량: 37.0, 체지방량: 27.2 },
          { year: "21.11.15", 체중: 88.8, 골격근량: 37.3, 체지방량: 27.4 },
          { year: "21.11.16", 체중: 88.8, 골격근량: 36.6, 체지방량: 28.4 },
          { year: "21.11.17", 체중: 88.9, 골격근량: 0, 체지방량: 0 },
          { year: "21.11.17", 체중: 88.9, 골격근량: 36.7, 체지방량: 28.2 },
          { year: "21.11.22", 체중: 88.8, 골격근량: 37.0, 체지방량: 27.8 },
          { year: "21.11.23", 체중: 89.5, 골격근량: 37.3, 체지방량: 27.7 },
          { year: "21.11.24", 체중: 88.6, 골격근량: 36.9, 체지방량: 27.9 },
          { year: "21.11.25", 체중: 88.8, 골격근량: 37.3, 체지방량: 27.5 },
        ];
        // Create axes
        // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
        var xAxis = chart.xAxes.push(
          am5xy.CategoryAxis.new(root, {
            categoryField: "year",
            start: 0.89,
            startLocation: 0.5,
            endLocation: 0.5,
            renderer: am5xy.AxisRendererX.new(root, {
              minGridDistance: 40,
            }),
            tooltip: am5.Tooltip.new(root, {}),
          })
        );
        xAxis.data.setAll(data);
        var yAxis = chart.yAxes.push(
          am5xy.ValueAxis.new(root, {
            renderer: am5xy.AxisRendererY.new(root, {}),
          })
        );
        // Add series
        // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
        function createSeries(field, name) {
          var series = chart.series.push(
            am5xy.SmoothedXLineSeries.new(root, {
              name: name,
              xAxis: xAxis,
              yAxis: yAxis,
              valueField: field,
              valueYField: field + "_hi",
              openValueYField: field + "_low",
              categoryXField: "year",
              tooltip: am5.Tooltip.new(root, {
                pointerOrientation: "horizontal",
                labelText:
                  "[fontSize: 18px]{name}[/]\n{categoryX}: [bold]{" +
                  field +
                  "}[/]",
              }),
            })
          );
          // Do not show tooltip for zero values
          series
            .get("tooltip")
            .adapters.add("visible", function (visible, target) {
              if (target.dataItem && target.dataItem.get("value") > 0) {
                return true;
              }
              return false;
            });
          series.strokes.template.setAll({
            forceHidden: true,
          });
          series.fills.template.setAll({
            visible: true,
            fillOpacity: 1,
          });
          // Make stuff animate on load
          // https://www.amcharts.com/docs/v5/concepts/animations/
          series.appear();
          legend.data.push(series);
        }
        createSeries("체지방량", "체지방량");
        createSeries("골격근량", "골격근량");
        createSeries("체중", "체중");
        // Prepare data for the river-stacked series
        for (var i = 0; i < data.length; i++) {
          var row = data[i];
          var sum = 0;
          // Calculate open and close values
          chart.series.each(function (series) {
            var field = series.get("valueField");
            var val = Number(row[field]);
            row[field + "_low"] = sum;
            row[field + "_hi"] = val;
            sum += val;
          });
          chart.series.each(function (series) {
            var field = series.get("valueField"); // eslint-disable-line no-unused-vars
            series.data.setAll(data);
          });
        }
        // Add cursor
        chart.set(
          "cursor",
          am5xy.XYCursor.new(root, {
            behavior: "zoomXY",
            xAxis: xAxis,
          })
        );
        // Make stuff animate on load
        // https://www.amcharts.com/docs/v5/concepts/animations/
        chart.appear(1000, 100);
      }); // end am5.ready()
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
  #weight1chartdiv {
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