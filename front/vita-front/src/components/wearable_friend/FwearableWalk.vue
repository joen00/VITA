<template>
  <div id="FwearableWalkBody">
    <div class="header">
      <ComponentHeader
        :ComponentHeaderTitle="ComponentHeaderTitle"
        :ComponentHeaderContent="ComponentHeaderContent"
      />
    </div>
    <div id="wearable-friend-Walk">
      <div id="wearable-friend-Walk-Left">
        <div id="wearable-friend-Walk-Left-div">
          <div id="wearable-friend-Walk-chartdiv"></div>
        </div>
      </div>
      <div id="wearable-friend-Walk-Right">
        <div id="wearable-friend-Walk-rank">
          <img
            id="wearable-friend-Walk-rank-img"
            :src="require(`/public/wearable-friend/fwrank.png`)"
          />
          <div id="wearable-friend-Walk-rank-h1">랭킹</div>
          <div
            id="wearable-friend-Walk-rank-item"
            v-for="rank in franks"
            :key="rank"
          >
            {{ rank.id }}
            <b-avatar
              variant="info"
              :src="rank.img"
            ></b-avatar>
            {{ rank.name }}
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
import ComponentHeader from "@/components/common/ComponentHeader.vue";

export default {
  components: {
    ComponentHeader,
  },
  props: {
    walkData: Array
  },
  data() {
    return {
      ComponentHeaderTitle: "걸음수",
      ComponentHeaderContent:
        "친구들과 나의 평균 걸음수 기록을 비교해 보여줘요.",
      franks: [],
    };
  },
  mounted() {

    // 등수 매기기
    let sortData = this.walkData.sort(function(a,b) {
      return b.userAverageWalk - a.userAverageWalk;
    })
    let count = 0;
    this.franks = [];

    for (var data of sortData) {
      count += 1;
      this.franks.push({id: count, name: data.name, img: data.bulletSettings.src})
      if (count >= 5) {
        break;
      }
    }

    am5.ready(() => {
      // Create root element
      // https://www.amcharts.com/docs/v5/getting-started/#Root_element
      var root = am5.Root.new("wearable-friend-Walk-chartdiv");

      // Set themes
      // https://www.amcharts.com/docs/v5/concepts/themes/
      root.setThemes([am5themes_Animated.new(root)]);

      // Create chart
      // https://www.amcharts.com/docs/v5/charts/xy-chart/
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
      var data = this.walkData

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
#wearable-friend-Walk-Left {
  width: 70%;
  height: 500px;
  float: left;
  /*수평 수직 정렬*/
  display: flex;
  flex-wrap: wrap;
  flex-direction: row; /*수평 정렬*/
  align-items: center;
  justify-content: center;
}
#wearable-friend-Walk-Left-div {
  width: 80%;
  height: 90%;
  float: left;
  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  /*수평 수직 정렬*/
  display: flex;
  flex-wrap: wrap;
  flex-direction: row; /*수평 정렬*/
  align-items: center;
  justify-content: center;
}
#wearable-friend-Walk-chartdiv {
  width: 80%;
  height: 80%;
  float: left;
  background: #ffffff;
  /* box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px; */
}
#wearable-friend-Walk-Right {
  width: 30%;
  height: 500px;
  float: left;
  /* background-color: bisque; */
}
#wearable-friend-Walk-rank {
  box-sizing: border-box;
  width: 250px;
  height: 343px;

  background: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  margin: 0 auto;
  margin-top: 20%;
  margin-left: -5%;
  vertical-align: middle;
}
#wearable-friend-Walk-rank-h1 {
  font-weight: 600;
  font-size: 1.5rem;
}
#wearable-friend-Walk-rank-img {
  margin-top: -30px;
}
#wearable-friend-Walk-rank-item {
  font-weight: 600;
  margin: 0.5rem;
}
</style>