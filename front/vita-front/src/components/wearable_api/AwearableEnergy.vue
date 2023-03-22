<template>
    <div id="AwearableEnergyBody">
        <div id="Aenergy">
            <div id="AenergyLeft">
                <div id="AenergyTitle">활동 에너지</div>
                <div id="AenergyDesc">나의 활동 에너지와 사람들의 평균을 비교해보세요.</div>
                <div id="AenergyAdvice"></div>
            </div>
            <div id="AenergyRight">
                <div id="chartdiv"></div>
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
      };
    },
    mounted() {
      am5.ready(() => {
        var root = am5.Root.new("chartdiv");
  
        root.setThemes([am5themes_Animated.new(root)]);
        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
        var chart = root.container.children.push(am5xy.XYChart.new(root, {
            panX: true,
            panY: true,
            wheelX: "panX",
            wheelY: "zoomX",
            pinchZoomX: true
        }));

        // Add cursor
        // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
        var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
        cursor.lineY.set("visible", false);


        // Create axes
        // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
        var xRenderer = am5xy.AxisRendererX.new(root, { minGridDistance: 30 });
            xRenderer.labels.template.setAll({
            rotation: -90,
            centerY: am5.p50,
            centerX: am5.p100,
            paddingRight: 15
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


        // Set data
        var data = [
            {
                target: "나",
                value: 2025
            }, 
            {
                target: "평균",
                value: 2200
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
  #AwearableEnergyBody{
    margin-top: 10rem;
  }
  #Aenergy{
    margin-top: 3rem;
  }
  #AenergyLeft{
    width: 50%;
    height: 300px;
    float: left;
    /* background-color: aqua; */
  }
  #AenergyRight{
    width: 50%;
    height: 300px;
    float: left;
    /* background-color: bisque; */
  }
  #chartdiv {
    width: 90%;
    height: 100%;
    background-color: rgb(253, 254, 255);
    border-radius: 10%;
    margin: 0 auto;
  }
  #AenergyTitle {
    font-family: "Exo 2";
    font-style: normal;
    font-weight: 700;
    font-size: 26px;
    color: #172176;
  }
  #AenergyDesc {
    font-family: "Exo 2";
    font-style: normal;
    font-weight: 700;
    font-size: 12px;
    color: #47474B;
  }
</style>