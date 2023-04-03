<template>
  <div id="stresschartdiv"></div>
</template>

<script>
import * as am5 from "@amcharts/amcharts5";
import * as am5xy from "@amcharts/amcharts5/xy";
import * as am5radar from "@amcharts/amcharts5/radar";
import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";

export default {
  name: "StressChart",
  props: {
    data: Array,
  },
  mounted() {
    am5.ready(() => {
      var root = am5.Root.new("stresschartdiv");
      root.setThemes([am5themes_Animated.new(root)]);
      var chart = root.container.children.push(
        am5radar.RadarChart.new(root, {
          panX: false,
          panY: false,
          wheelX: "none",
          wheelY: "none",
          startAngle: -84,
          endAngle: 264,
          innerRadius: am5.percent(40),
        })
      );

      const cursor = chart.set(
        "cursor",
        am5radar.RadarCursor.new(root, {
          behavior: "zoomX",
        })
      );
      cursor.lineY.set("forceHidden", true);

      // Add scrollbar
      // https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
      chart.set(
        "scrollbarX",
        am5.Scrollbar.new(root, {
          orientation: "horizontal",
          exportable: false,
          start: 0.7,
        })
      );

      // Create axes
      // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
      var xRenderer = am5radar.AxisRendererCircular.new(root, {
        minGridDistance: 30,
      });

      xRenderer.grid.template.set("forceHidden", true);

      // var xAxis = chart.xAxes.push(
      //   am5xy.CategoryAxis.new(root, {
      //     maxDeviation: 7,
      //     categoryField: "date",
      //     renderer: xRenderer,
      //   })
      // );
      var xAxis = chart.xAxes.push(
        am5xy.DateAxis.new(root, {
          maxDeviation: 7,
          categoryField: "date",
          renderer: xRenderer,
        })
      );

      var yRenderer = am5radar.AxisRendererRadial.new(root, {});
      yRenderer.labels.template.set("centerX", am5.p50);

      var yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          maxDeviation: 0.3,
          min: 0,
          renderer: yRenderer,
        })
      );

      // Add series
      // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
      var series = chart.series.push(
        am5radar.RadarColumnSeries.new(root, {
          name: "Series 1",
          sequencedInterpolation: true,
          xAxis: xAxis,
          yAxis: yAxis,
          valueYField: "Stress",
          categoryXField: "date",
        })
      );

      // Rounded corners for columns
      series.columns.template.setAll({
        cornerRadius: 5,
        tooltipText: "{categoryX}: {valueY}",
      });

      series.data.processor = am5.DataProcessor.new(root, {
        dateFormat: "yyyy-MM-dd",
        dateFields: ["date"],
      });

      // Make each column to be of a different color
      series.columns.template.adapters.add("fill", function (fill, target) {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
      });

      series.columns.template.adapters.add("stroke", function (stroke, target) {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
      });

      // Set data
      var data = this.data;

      xAxis.data.setAll(data);
      series.data.setAll(data);

      // Make stuff animate on load
      // https://www.amcharts.com/docs/v5/concepts/animations/
      series.appear(1000);
      chart.appear(1000, 100);
    });
  },
};
</script>

<style>
#stresschartdiv {
  /* width: 40rem;
  height: 400px;
  margin-top: 10rem;
  margin: auto; */
  width: 32rem;
  height: 400px;
}
</style>