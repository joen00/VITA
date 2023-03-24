<template>
    <div>
      <div id="energychartdiv"></div>
      {{data}}
    </div>
  </template>
  
  <script>
  import * as am5 from "@amcharts/amcharts5";
  import * as am5xy from "@amcharts/amcharts5/xy";
  import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
  
  export default {
    name: 'EnergyChart',
    props: {
      data: Array,
    },
    mounted() {
    am5.ready(() => {
      const root = am5.Root.new("energychartdiv");

      root.setThemes([am5themes_Animated.new(root)]);

      const chart = root.container.children.push(
        am5xy.XYChart.new(root, {
          panX: false,
          panY: false,
          wheelX: "panX",
          wheelY: "zoomX",
        })
      );

      const cursor = chart.set(
        "cursor",
        am5xy.XYCursor.new(root, { behavior: "zoomX" })
      );
      cursor.lineY.set("visible", false);

      const xAxis = chart.xAxes.push(
        am5xy.GaplessDateAxis.new(root, {
          maxDeviation: 0,
          baseInterval: {
            timeUnit: "day",
            count: 1,
          },
          renderer: am5xy.AxisRendererX.new(root, {
            minGridDistance: 60,
          }),
          start: 0.9,
          tooltip: am5.Tooltip.new(root, {}),
        })
      );

      const yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          renderer: am5xy.AxisRendererY.new(root, {}),
        })
      );

      const series = chart.series.push(
        am5xy.ColumnSeries.new(root, {
          name: "Series",
          xAxis: xAxis,
          yAxis: yAxis,
          valueYField: "Energy",
          valueXField: "date",
          tooltip: am5.Tooltip.new(root, {
            labelText: "{valueY}",
          }),
        })
      );

      series.data.processor = am5.DataProcessor.new(root, {
          dateFormat: "yyyy-MM-dd",
          dateFields: ["date"],
        });

      series.columns.template.setAll({ strokeOpacity: 0 });

      chart.set(
        "scrollbarX",
        am5.Scrollbar.new(root, {
          orientation: "horizontal",
        })
      );

      const data = this.data

      series.data.setAll(data);

      series.appear(1000);
      chart.appear(1000, 100);
    });
  },
  }
  </script>
  
  <style scoped>
  #energychartdiv {
    width: 40rem;
    height: 400px;
    margin-top: 10rem;
    margin: auto;
  }
  </style>