<template>
  <div id="FwearableWalkBody">
    <div class="header">
      <div class="header-title">걸음수</div>
      <div class="header-content">
        친구들과 나의 평균 걸음수 기록을 비교해 보여줘요.
      </div>
      <div id="fwWalk">
          <div id="fwWalkLeft">
            <div id="fwenergychartdiv"></div>
          </div>
          <div id="fwWalkRight">
            <div id="fwWalkrank">
              <h1>랭킹</h1>
              <div id="frwalk" v-for="rank in franks" :key="rank">
                  {{ rank.id }}
                  <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
                  {{ rank.name }}
              </div>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<!-- Resources -->


<script>
import * as am5 from "@amcharts/amcharts5";
import * as am5xy from "@amcharts/amcharts5/xy";
import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";

export default {
  data() {
    return {
      franks: [
        { id: 1, name: "김뿡1" },
        { id: 2, name: "김뿡2" },
        { id: 3, name: "김뿡3" },
        { id: 4, name: "김뿡4" },
        { id: 5, name: "김뿡5" },
      ],
    };
  },
  mounted() {
    am5.ready(() => {
      var root = am5.Root.new("fwenergychartdiv");
      root.setThemes([am5themes_Animated.new(root)]);
      var chart = root.container.children.push(
        am5xy.XYChart.new(root, {
          panX: false,
          panY: false,
          wheelX: "none",
          wheelY: "none",
        })
      );

      // Add cursor
      // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
      var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
      cursor.lineY.set("visible", false);

      // Create axes
      // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
      var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });

      var xAxis = chart.xAxes.push(
        am5xy.CategoryAxis.new(root, {
          maxDeviation: 0,
          categoryField: "name",
          renderer: xRenderer,
          tooltip: am5.Tooltip.new(root, {}),
        })
      );

      xRenderer.grid.template.set("visible", false);

      var yRenderer = am5xy.AxisRendererY.new(root, {});
      var yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
          maxDeviation: 0,
          min: 0,
          extraMax: 0.1,
          renderer: yRenderer,
        })
      );

      yRenderer.grid.template.setAll({
        strokeDasharray: [2, 2],
      });

      // Create series
      // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
      var series = chart.series.push(
        am5xy.ColumnSeries.new(root, {
          name: "Series 1",
          xAxis: xAxis,
          yAxis: yAxis,
          valueYField: "value",
          sequencedInterpolation: true,
          categoryXField: "name",
          tooltip: am5.Tooltip.new(root, { dy: -25, labelText: "{valueY}" }),
        })
      );

      series.columns.template.setAll({
        cornerRadiusTL: 5,
        cornerRadiusTR: 5,
        strokeOpacity: 0,
      });

      series.columns.template.adapters.add("fill", (fill, target) => {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
      });

      series.columns.template.adapters.add("stroke", (stroke, target) => {
        return chart.get("colors").getIndex(series.columns.indexOf(target));
      });

      // Set data
      var data = [
        {
          name: "John",
          value: 35654,
          bulletSettings: {
            src: "https://www.amcharts.com/lib/images/faces/A04.png",
          },
        },
        {
          name: "Damon",
          value: 65456,
          bulletSettings: {
            src: "https://www.amcharts.com/lib/images/faces/C02.png",
          },
        },
        {
          name: "Patrick",
          value: 45724,
          bulletSettings: {
            src: "https://www.amcharts.com/lib/images/faces/D02.png",
          },
        },
        {
          name: "Joen",
          value: 13654,
          bulletSettings: {
            src: "https://www.amcharts.com/lib/images/faces/E01.png",
          },
        },
        {
          name: "Mark",
          value: 13654,
          bulletSettings: {
            src: "https://www.amcharts.com/lib/images/faces/E01.png",
          },
        },
      ];

      series.bullets.push(function () {
        return am5.Bullet.new(root, {
          locationY: 1,
          sprite: am5.Picture.new(root, {
            templateField: "bulletSettings",
            width: 50,
            height: 50,
            centerX: am5.p50,
            centerY: am5.p50,
            shadowColor: am5.color(0x000000),
            shadowBlur: 4,
            shadowOffsetX: 4,
            shadowOffsetY: 4,
            shadowOpacity: 0.6,
          }),
        });
      });

      xAxis.data.setAll(data);
      series.data.setAll(data);

      // Make stuff animate on load
      // https://www.amcharts.com/docs/v5/concepts/animations/
      series.appear(1000);
      chart.appear(1000, 100);
    }); // end am5.ready()
  },
};
</script>

<style>

#FwearableWalkBody{
  margin-top: 10rem;
}
#fwWalk{
  margin-top: 3rem;
}
#fwWalkLeft{
  width: 70%;
  height: 500px;
  float: left;
  /* background-color: aqua; */
}

#fwenergychartdiv {
  width: 90%;
  height: 500px;
  background-color: rgb(253, 254, 255);
  border-radius: 10%;
  margin: 0 auto;
}
#fwWalkRight{
  width: 30%;
  height: 500px;
  float: left;
  /* background-color: bisque; */
}
#fwWalkrank{
  box-sizing: border-box;
  width: 250px;
  height: 343px;

  background: #FFFFFF;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  margin: 0 auto;
  margin-top: 24%;
  vertical-align: middle;
}
</style>