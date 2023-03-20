<template>
    <div>
      <div id="chartdiv"></div>
      {{data}}
    </div>
  </template>
  
  <script>
  import * as am5 from "@amcharts/amcharts5";
  import * as am5xy from "@amcharts/amcharts5/xy";
  // import * as am5radar from "@amcharts/amcharts5/radar";
  import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
import { List } from '@amcharts/amcharts5/.internal/core/util/List';
  
  export default {
    name: 'WearableWalkChart',
    props: {
      data: List,
    },
    mounted() {
      console.log(this.data);
      am5.ready(() => {
        var root = am5.Root.new("chartdiv"); // 'chartdiv'라는 id로 차트 생성
        root.setThemes([
          am5themes_Animated.new(root), //
        ]);
        const chart = root.container.children.push(
          am5xy.XYChart.new(root, {
            panX: true,
            panY: true,
            wheelX: "panX",
            wheelY: "zoomX",
            layout: root.verticalLayout,
            pinchZoomX: true,
          })
        );
  
        const cursor = chart.set(
          "cursor",
          am5xy.XYCursor.new(root, {
            behavior: "none",
          })
        );
        cursor.lineY.set("visible", false);
  
        
        // The data
        const data = this.datas;
        
        const xRenderer = am5xy.AxisRendererX.new(root, {});
        xRenderer.grid.template.set("location", 0.5);
        xRenderer.labels.template.setAll({ location: 0.5, multiLocation: 0.5 });
        
        const xAxis = chart.xAxes.push(
          am5xy.DateAxis.new(root, {
            baseInterval: { timeUnit: "day", count: 1 },
            renderer: xRenderer,
            start: 0.9951,
            tooltip: am5.Tooltip.new(root, {}),
          })
        );
        
  
        const yRenderer = am5xy.AxisRendererY.new(root, {});
        yRenderer.grid.template.set("forceHidden", true);
        yRenderer.labels.template.set("minPosition", 0.05);
  
        const yAxis = chart.yAxes.push(
          am5xy.ValueAxis.new(root, {
            maxPrecision: 0,
            extraMin: 0.1,
            renderer: yRenderer,
          })
        );
  
        const series = chart.series.push(
          am5xy.LineSeries.new(root, {
            xAxis: xAxis,
            yAxis: yAxis,
            valueYField: "dailyWearableStep",
            valueXField: "date",
            maskBullets: false,
            tooltip: am5.Tooltip.new(root, {
              pointerOrientation: "vertical",
              dy: -20,
              labelText: "{valueY}",
            }),
          })
        );
  
        series.data.processor = am5.DataProcessor.new(root, {
          dateFormat: "yyyy-MM-dd HH:mm",
          dateFields: ["date"],
        });
  
        series.strokes.template.setAll({
          strokeDasharray: [3, 3],
          strokeWidth: 1,
        });
  
        let i = -1;
        series.bullets.push(function () {
          i++;
  
          if (i > 7) {
            i = 0;
          }
  
          const container = am5.Container.new(root, {
            centerX: am5.p50,
            centerY: am5.p50,
          });
  
          container.children.push(
            am5.Circle.new(root, { radius: 20, fill: series.get("fill") })
          );
  
          container.children.push(
            am5.Picture.new(root, {
              centerX: am5.p50,
              centerY: am5.p50,
              width: 23,
              height: 23,
              src: "https://cdn-icons-png.flaticon.com/512/556/556196.png",
            })
          );
  
          return am5.Bullet.new(root, {
            sprite: container,
          });
        });
        console.log("여기가 에러임?")  
        series.data.setAll(data);
        console.log("아닌가?")
        series.appear(1000);
  
        // Make stuff animate on load
        // https://www.amcharts.com/docs/v5/concepts/animations/
        chart.appear(1000, 100);
  
        // var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
        // chart.events.on("ready", function (ev) {
        //   valueAxis.min = valueAxis.minZoomed;
        //   valueAxis.max = valueAxis.maxZoomed;
        // });
      });
      
    },
  }
  </script>
  
  <style scoped>
  #chartdiv {
    width: 40rem;
    height: 400px;
    margin-top: 10rem;
    margin: auto;
  }
  </style>