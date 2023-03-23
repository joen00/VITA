<template>
    <div id="AwearableSleepStageBody">
        <div id="AsleepStage">
            <div id="AsleepStageLeft">
              <ComponentHeader :ComponentHeaderTitle = ComponentHeaderTitle :ComponentHeaderContent = ComponentHeaderContent />
                <div id="AsleepStageAdvice">
                  <DoctorAdvice :Advice= Advice ></DoctorAdvice>
                </div>
            </div>
            <div id="AsleepStageRight">
                <div id="chartdiv-api-sleepStage"></div>
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
      DoctorAdvice
    },
    data() {
      return {
        ComponentHeaderTitle: "수면 단계",
        ComponentHeaderContent: "나의 수면 단계와 사람들의 평균을 비교해보세요.",
        Advice: "잘 자고 있어요! 꾸준히 푹 자볼까요?",
      };
    },
    mounted() {
      am5.ready(() => {
        var root = am5.Root.new("chartdiv-api-sleepStage");
  
        root.setThemes([am5themes_Animated.new(root)]);

        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
        var chart = root.container.children.push(am5xy.XYChart.new(root, {
          panX: false,
          panY: false,
          wheelX: "panX",
          wheelY: "zoomX",
          layout: root.verticalLayout
        }));

        // Add scrollbar
        // https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/
        // chart.set("scrollbarX", am5.Scrollbar.new(root, {
        //   orientation: "horizontal"
        // }));

        chart.get("colors").set("colors", [
          am5.color(0xF97A7A),
          am5.color(0x83D2FF),
          am5.color(0x839EFF),
          am5.color(0x623EEE),
        ]);

        var data = [{
          "target": "나",
          "stage1": 2.5,
          "stage2": 3.5,
          "stage3": 1.1,
          "stage4": 3.9
        }, {
          "target": "평균",
          "stage1": 3.5,
          "stage2": 1.5,
          "stage3": 3.2,
          "stage4": 1.8
        }
      ]


        // Create axes
        // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
        var xRenderer = am5xy.AxisRendererX.new(root, {});
        var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
          categoryField: "target",
          renderer: xRenderer,
          tooltip: am5.Tooltip.new(root, {})
        }));

        xRenderer.grid.template.setAll({
          location: 1
        })

        xAxis.data.setAll(data);

        var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
          min: 0,
          max: 100,
          numberFormat: "#'%'",
          strictMinMax: true,
          calculateTotals: true,
          renderer: am5xy.AxisRendererY.new(root, {
            strokeOpacity: 0.1
          })
        }));


        // Add legend
        // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
        var legend = chart.children.push(am5.Legend.new(root, {
          centerX: am5.p50,
          x: am5.p50,
          layout: am5.HorizontalLayout.new(root, {
            maxColumns: 2,
            fixedWidthGrid: true
          })
        }));

        // Add series
        // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
        function makeSeries(name, fieldName) {
          var series = chart.series.push(am5xy.ColumnSeries.new(root, {
            name: name,
            stacked: true,
            xAxis: xAxis,
            yAxis: yAxis,
            valueYField: fieldName,
            valueYShow: "valueYTotalPercent",
            categoryXField: "target"
          }));

          series.columns.template.setAll({
            tooltipText: "{name}, {categoryX}:{valueYTotalPercent.formatNumber('#.#')}%",
            tooltipY: am5.percent(10)
          });
          series.data.setAll(data);

          // Make stuff animate on load
          // https://www.amcharts.com/docs/v5/concepts/animations/
          series.appear();

          series.bullets.push(function() {
            return am5.Bullet.new(root, {
              sprite: am5.Label.new(root, {
                text: "{valueYTotalPercent.formatNumber('#.#')}%",
                fill: root.interfaceColors.get("alternativeText"),
                centerY: am5.p50,
                centerX: am5.p50,
                populateText: true
              })
            });
          });


          // Set column radius
          series.columns.template.setAll({
            cornerRadiusTL: 8,
            cornerRadiusTR: 8
          });

          // Set column width
          series.columns.template.setAll({
            width: am5.percent(20)
          });

          legend.data.push(series);
        }

        makeSeries("비수면", "stage1");
        makeSeries("REM 수면", "stage2");
        makeSeries("코어 수면", "stage3");
        makeSeries("깊은 수면", "stage4");

        // Make stuff animate on load
        // https://www.amcharts.com/docs/v5/concepts/animations/
        chart.appear(1000, 100);
        
        }); // end am5.ready()
    // })
    }
}
</script>
  
<style scoped>
  #AsleepStage{
    margin-top: 3rem;
  }
  #AsleepStageLeft{
    width: 50%;
    height: 250px;
    float: left;
    /* background-color: aqua; */
  }
  #AsleepStageRight{
    width: 50%;
    height: 250px;
    float: left;
    /* background-color: bisque; */
  }
  #chartdiv-api-sleepStage {
    width: 90%;
    height: 100%;
    background-color: rgb(253, 254, 255);
    border-radius: 10%;
    margin: 0 auto;
  }
  #AsleepStageAdvice {
    margin-top: 30px;
  }

</style>