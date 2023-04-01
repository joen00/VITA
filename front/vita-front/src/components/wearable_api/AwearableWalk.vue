<template>
    <div id="AwearableWalkBody">
        <div id="Awalk">
            <div id="AwalkLeft">
              <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
                <div id="AwalkAdvice">
                  <DoctorAdvice :Advice= Advice ></DoctorAdvice>
                </div>
            </div>
            <div id="AwalkRight">
                <div id="chartdiv-api-walk"></div>
            </div>
        </div>
    </div>
</template>
  
  <!-- Resources -->
  
  
<script>
  import * as am5 from "@amcharts/amcharts5";
  import * as am5xy from "@amcharts/amcharts5/xy";
  import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";
  import ComponentHeader from '../common/ComponentHeader.vue';
  import DoctorAdvice from '../wearable_api/DoctorAdvice.vue';

  export default {
  components: { 
    ComponentHeader,
    DoctorAdvice,
  },
    props: {
      userData: Number,
      apiData: Number,
    },
    data() {
      return {
        ComponentHeaderTitle: "걸음수",
        ComponentHeaderContent: "나의 걸음수와 사람들의 평균을 비교해보세요.",
        Advice: "잘 걷고 있어요. 앞으로도 꾸준히 걸어볼까요?"
      };
    },
    mounted() {
      am5.ready(() => {
        var root = am5.Root.new("chartdiv-api-walk");
  
        root.setThemes([am5themes_Animated.new(root)]);
        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
        var chart = root.container.children.push(am5xy.XYChart.new(root, {
            panX: true,
            panY: true,
            // wheelX: "panX",
            // wheelY: "zoomX",
            // pinchZoomX: true
        }));

        chart.get("colors").set("colors", [
            am5.color(0xFC9EFE),
            am5.color(0x7038CA),
        ]);

        // Add cursor
        // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
        var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
        cursor.lineY.set("visible", false);


        // Create axes
        // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
        var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });
            xRenderer.labels.template.setAll({
            centerY: am5.p50,
            centerX: am5.p50,
        });

        xRenderer.grid.template.setAll({
            location: 1
        })

        var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
            maxDeviation: 0.3,
            categoryField: "target",
            renderer: xRenderer,
            tooltip: am5.Tooltip.new(root, {})
        }));

        var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
            maxDeviation: 0.3,
            renderer: am5xy.AxisRendererY.new(root, {
                strokeOpacity: 0.1
            })
        }));


        // Create series
        // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
        var series = chart.series.push(am5xy.ColumnSeries.new(root, {
            name: "Series 1",
            xAxis: xAxis,
            yAxis: yAxis,
            valueYField: "value",
            sequencedInterpolation: true,
            categoryXField: "target",
            tooltip: am5.Tooltip.new(root, {
                labelText: "{valueY}"
            })
        }));

        series.columns.template.setAll({ cornerRadiusTL: 60, cornerRadiusTR: 60, strokeOpacity: 0 });
        series.columns.template.adapters.add("fill", function(fill, target) {
            return chart.get("colors").getIndex(series.columns.indexOf(target));
        });

        series.columns.template.adapters.add("stroke", function(stroke, target) {
            return chart.get("colors").getIndex(series.columns.indexOf(target));
        });

        // Set column width
        series.columns.template.setAll({
          width: am5.percent(20)
        });

        // Set data
        var data = [
            {
                target: "나",
                value: this.userData
            }, 
            {
                target: "평균",
                value: this.apiData
            }
        ];

        xAxis.data.setAll(data);
        series.data.setAll(data);


        // Make stuff animate on load
        // https://www.amcharts.com/docs/v5/concepts/animations/
        series.appear(1000);
        chart.appear(1000, 100);

        }); // end am5.ready()
    // })
    }
}
</script>
  
<style scoped>
  #Awalk{
    margin-top: 3rem;
  }
  #AwalkLeft{
    width: 50%;
    height: 250px;
    float: left;
  }
  #AwalkRight{
    width: 50%;
    height: 250px;
    float: left;
  }
  #chartdiv-api-walk {
    width: 90%;
    height: 100%;
    background-color: rgb(253, 254, 255);
    border-radius: 10%;
    margin: 0 auto;
  }
  #AwalkAdvice {
    margin-top: 30px;
  }
</style>