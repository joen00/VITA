<template>
  <div>
    <!-- {{  data  }} -->
    <div id="weightchartdiv"></div>
  </div>
</template>

<script>
import * as am5 from "@amcharts/amcharts5";
import * as am5xy from "@amcharts/amcharts5/xy";
import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";

export default {
  name: "WeightChart",
  props: {
    data: Array,
  },
  mounted() {
    am5.ready(() => {
      // Create root element
      // https://www.amcharts.com/docs/v5/getting-started/#Root_element
      var root = am5.Root.new("weightchartdiv");

      // Set themes
      // https://www.amcharts.com/docs/v5/concepts/themes/
      root.setThemes([am5themes_Animated.new(root)]);

      // Create chart
      // https://www.amcharts.com/docs/v5/charts/xy-chart/
      var chart = root.container.children.push(
        am5xy.XYChart.new(root, {
          panX: true,
          panY: true,
          wheelX: "panX",
          wheelY: "zoomX",
          pinchZoomX: true,
        })
      );

      // Add cursor
      // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
      var cursor = chart.set(
        "cursor",
        am5xy.XYCursor.new(root, {
          behavior: "none",
        })
      );
      cursor.lineY.set("visible", false);

      // The data
      var data = this.data;

      // Create axes
      // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
      var xAxis = chart.xAxes.push(
        // am5xy.CategoryAxis.new(root, {
        //   categoryField: "day",
        //   startLocation: 0.5,
        //   endLocation: 0.5,
        //   renderer: am5xy.AxisRendererX.new(root, {}),
        //   tooltip: am5.Tooltip.new(root, {}),
        // })
        am5xy.DateAxis.new(root, {
          baseInterval: { timeUnit: "day", count: 1 },
          startLocation: 0.5,
          endLocation: 0.5,
          renderer: am5xy.AxisRendererX.new(root, {}),
          tooltip: am5.Tooltip.new(root, {}),
        })
      );
      xAxis.get("dateFormats")["day"] = "yyyy-MM-dd";
      xAxis.data.setAll(data);

      var yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          renderer: am5xy.AxisRendererY.new(root, {}),
        })
      );

      // Add series
      // https://www.amcharts.com/docs/v5/charts/xy-chart/series/

      function createSeries(name, field) {
        var series = chart.series.push(
          am5xy.LineSeries.new(root, {
            name: name,
            xAxis: xAxis,
            yAxis: yAxis,
            stacked: true,
            valueYField: field,
            valueXField: "day",
            // categoryXField: "day",
            tooltip: am5.Tooltip.new(root, {
              pointerOrientation: "horizontal",
              labelText: "[bold]{name}[/]\n{categoryX}: {valueY}",
            }),
          })
        );

        series.data.processor = am5.DataProcessor.new(root, {
          dateFields: ["day"],
          dateFormat: "yyyy-MM-dd"
        });

        series.fills.template.setAll({
          fillOpacity: 0.5,
          visible: true,
        });

        series.data.setAll(data);
        series.appear(1000);
      }

      createSeries("체지방량", "체지방량");
      createSeries("골격근량", "골격근량");
      createSeries("체중", "체중");

      // Add scrollbar
      // https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
      chart.set(
        "scrollbarX",
        am5.Scrollbar.new(root, {
          orientation: "horizontal",
        })
      );

      // Make stuff animate on load
      // https://www.amcharts.com/docs/v5/concepts/animations/
      chart.appear(1000, 100);
    });
  }, // end am5.ready()
};

//       am5.ready(() => {
//         var root = am5.Root.new("weightchartdiv"); // 'chartdiv'라는 id로 차트 생성
//         root.setThemes([am5themes_Animated.new(root)]);
//         var chart = root.container.children.push(
//           am5xy.XYChart.new(root, {
//             panX: false,
//             panY: false,
//             wheelX: "panX",
//             wheelY: "zoomX",
//             layout: root.horizontalLayout,
//           })
//         );
//         // Add legend
//         var legend = chart.children.push(
//           am5.Legend.new(root, {
//             y: am5.p100,
//             x: am5.percent(10),
//             layout: root.horizontalLayout,
//             clickTarget: "none",
//           })
//         );
//         legend.valueLabels.template.set("forceHidden", true);
//         // Data
//         var data = this.data;
//         // var data = [
//         //   { year: "21.10.03", 체중: 90.0, 골격근량: 37.5, 체지방량: 27.6 },
//         //   { year: "21.10.04", 체중: 98.0, 골격근량: 34.6, 체지방량: 34.7 },
//         //   { year: "21.10.04", 체중: 90.0, 골격근량: 36.7, 체지방량: 28.6 },
//         //   { year: "21.10.05", 체중: 90.0, 골격근량: 36.8, 체지방량: 28.5 },
//         //   { year: "21.10.05", 체중: 90.0, 골격근량: 37.5, 체지방량: 27.6 },
//         //   { year: "21.10.06", 체중: 89.8, 골격근량: 36.1, 체지방량: 29.3 },
//         //   { year: "21.10.06", 체중: 89.8, 골격근량: 38.0, 체지방량: 26.9 },
//         //   { year: "21.10.07", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
//         //   { year: "21.10.09", 체중: 90.3, 골격근량: 37.0, 체지방량: 28.3 },
//         //   { year: "21.10.09", 체중: 90.3, 골격근량: 36.4, 체지방량: 29.2 },
//         //   { year: "21.10.10", 체중: 88.8, 골격근량: 37.4, 체지방량: 27.3 },
//         //   { year: "21.10.11", 체중: 89.5, 골격근량: 36.5, 체지방량: 28.7 },
//         //   { year: "21.10.13", 체중: 89.9, 골격근량: 36.7, 체지방량: 28.6 },
//         //   { year: "21.10.14", 체중: 89.0, 골격근량: 35.9, 체지방량: 29.4 },
//         //   { year: "21.10.16", 체중: 88.8, 골격근량: 36.9, 체지방량: 27.9 },
//         //   { year: "21.10.17", 체중: 88.1, 골격근량: 37.5, 체지방량: 26.9 },
//         //   { year: "21.10.18", 체중: 89.1, 골격근량: 37.4, 체지방량: 27.4 },
//         //   { year: "21.10.19", 체중: 88.9, 골격근량: 36.5, 체지방량: 28.4 },
//         //   { year: "21.10.20", 체중: 88.9, 골격근량: 36.2, 체지방량: 28.8 },
//         //   { year: "21.10.20", 체중: 89.2, 골격근량: 36.8, 체지방량: 28.2 },
//         //   { year: "21.10.20", 체중: 89.7, 골격근량: 0, 체지방량: 0 },
//         //   { year: "21.10.21", 체중: 89.4, 골격근량: 36.7, 체지방량: 28.4 },
//         //   { year: "21.10.22", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
//         //   { year: "21.10.23", 체중: 93.2, 골격근량: 33.6, 체지방량: 34.2 },
//         //   { year: "21.10.23", 체중: 89.2, 골격근량: 37.1, 체지방량: 27.8 },
//         //   { year: "21.10.23", 체중: 89.0, 골격근량: 37.3, 체지방량: 27.5 },
//         //   { year: "21.10.25", 체중: 89.0, 골격근량: 36.9, 체지방량: 28.1 },
//         //   { year: "21.10.26", 체중: 89.2, 골격근량: 37.5, 체지방량: 27.3 },
//         //   { year: "21.10.27", 체중: 89.0, 골격근량: 36.6, 체지방량: 28.3 },
//         //   { year: "21.10.29", 체중: 88.3, 골격근량: 37.1, 체지방량: 27.5 },
//         //   { year: "21.10.31", 체중: 88.5, 골격근량: 37.1, 체지방량: 27.6 },
//         //   { year: "21.11.01", 체중: 88.9, 골격근량: 36.6, 체지방량: 28.3 },
//         //   { year: "21.11.02", 체중: 88.9, 골격근량: 36.8, 체지방량: 28.1 },
//         //   { year: "21.11.03", 체중: 88.4, 골격근량: 38.0, 체지방량: 26.4 },
//         //   { year: "21.11.05", 체중: 88.3, 골격근량: 38.0, 체지방량: 26.2 },
//         //   { year: "21.11.06", 체중: 88.4, 골격근량: 37.7, 체지방량: 26.8 },
//         //   { year: "21.11.07", 체중: 89.6, 골격근량: 36.9, 체지방량: 28.3 },
//         //   { year: "21.11.08", 체중: 88.4, 골격근량: 37.8, 체지방량: 26.6 },
//         //   { year: "21.11.09", 체중: 88.5, 골격근량: 37.3, 체지방량: 27.4 },
//         //   { year: "21.11.09", 체중: 91.0, 골격근량: 36.4, 체지방량: 29.4 },
//         //   { year: "21.11.12", 체중: 87.9, 골격근량: 37.5, 체지방량: 26.9 },
//         //   { year: "21.11.13", 체중: 87.2, 골격근량: 37.0, 체지방량: 27.2 },
//         //   { year: "21.11.15", 체중: 88.8, 골격근량: 37.3, 체지방량: 27.4 },
//         //   { year: "21.11.16", 체중: 88.8, 골격근량: 36.6, 체지방량: 28.4 },
//         //   { year: "21.11.17", 체중: 88.9, 골격근량: 0, 체지방량: 0 },
//         //   { year: "21.11.17", 체중: 88.9, 골격근량: 36.7, 체지방량: 28.2 },
//         //   { year: "21.11.22", 체중: 88.8, 골격근량: 37.0, 체지방량: 27.8 },
//         //   { year: "21.11.23", 체중: 89.5, 골격근량: 37.3, 체지방량: 27.7 },
//         //   { year: "21.11.24", 체중: 88.6, 골격근량: 36.9, 체지방량: 27.9 },
//         //   { year: "21.11.25", 체중: 88.8, 골격근량: 37.3, 체지방량: 27.5 },
//         // ];
//         // Create axes
//         // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
//         var xAxis = chart.xAxes.push(
//           am5xy.CategoryAxis.new(root, {
//             categoryField: "day",
//             start: 0.89,
//             startLocation: 0.5,
//             endLocation: 0.5,
//             renderer: am5xy.AxisRendererX.new(root, {
//               minGridDistance: 40,
//             }),
//             tooltip: am5.Tooltip.new(root, {}),
//           })
//         );
//         xAxis.data.setAll(data);
//         var yAxis = chart.yAxes.push(
//           am5xy.ValueAxis.new(root, {
//             renderer: am5xy.AxisRendererY.new(root, {}),
//           })
//         );
//         // Add series
//         // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
//         function createSeries(field, name) {
//           var series = chart.series.push(
//             am5xy.SmoothedXLineSeries.new(root, {
//               name: name,
//               xAxis: xAxis,
//               yAxis: yAxis,
//               valueField: field,
//             //   valueYField: field + "_hi",
//             //   openValueYField: field + "_low",
//               categoryXField: "day",
//               tooltip: am5.Tooltip.new(root, {
//                 pointerOrientation: "horizontal",
//                 labelText:
//                   "[fontSize: 18px]{name}[/]\n{categoryX}: [bold]{" +
//                   field +
//                   "}[/]",
//               }),
//             })
//           );
//           // Do not show tooltip for zero values
//           series
//             .get("tooltip")
//             .adapters.add("visible", function (visible, target) {
//               if (target.dataItem && target.dataItem.get("value") > 0) {
//                 return true;
//               }
//               return false;
//             });
//           series.strokes.template.setAll({
//             forceHidden: true,
//           });
//           series.fills.template.setAll({
//             visible: true,
//             fillOpacity: 1,
//           });
//           // Make stuff animate on load
//           // https://www.amcharts.com/docs/v5/concepts/animations/
//           series.appear();
//           legend.data.push(series);
//           series.data.setAll(data);
//         }
//         createSeries("체지방량", "체지방량");
//         createSeries("골격근량", "골격근량");
//         createSeries("체중", "체중");
//         // Prepare data for the river-stacked series
//         // for (var i = 0; i < data.length; i++) {
//         //   var row = data[i];
//         //   var sum = 0;
//         //   // Calculate open and close values
//         //   chart.series.each(function (series) {
//         //     var field = series.get("valueField");
//         //     var val = Number(row[field]);
//         //     row[field + "_low"] = sum;
//         //     row[field + "_hi"] = val;
//         //     sum += val;
//         //   });
//         //   chart.series.each(function (series) {
//         //     var field = series.get("valueField"); // eslint-disable-line no-unused-vars
//         //     series.data.setAll(data);
//         //   });
//         // }
//         // Add cursor
//         chart.set(
//           "cursor",
//           am5xy.XYCursor.new(root, {
//             behavior: "zoomXY",
//             xAxis: xAxis,
//           })
//         );
//         // Make stuff animate on load
//         // https://www.amcharts.com/docs/v5/concepts/animations/
//         chart.appear(1000, 100);
//       }); // end am5.ready()
//     }
//   };
</script>

<style>
#weightchartdiv {
  /* width: 40rem;
  height: 400px;
  margin-top: 10rem;
  margin: auto; */
  width: 32rem;
  height: 400px;
}
</style>