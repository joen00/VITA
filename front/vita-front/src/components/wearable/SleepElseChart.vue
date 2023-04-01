.<template>
  <div id="chart">
        <apexchart type="bar" height="350" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
    name: "SleepElseChart",
    components: {
        apexchart: VueApexCharts,
    },
    props: {
        awakedata: Array,
        lightdata: Array,
        remdata: Array,
        deepdata: Array,
        date: Array
    },
    data: function () {
    return {
        series: [{
            name: 'awake',
            data: this.awakedata
          }, 
          {
            name: 'light',
            data: this.lightdata
          }, 
          {
            name: 'remdata',
            data: this.remdata
          }, 
          {
            name: 'deep',
            data: this.deepdata
        }],
        chartOptions: {
            chart: {
              type: 'bar',
              height: 350,
              stacked: true,
              toolbar: {
                show: true
              },
              zoom: {
                enabled: true
              }
            },
            responsive: [{
              breakpoint: 480,
              options: {
                legend: {
                  position: 'bottom',
                  offsetX: -10,
                  offsetY: 0
                }
              }
            }],
            plotOptions: {
              bar: {
                horizontal: false,
                borderRadius: 10,
                dataLabels: {
                  total: {
                    enabled: true,
                    style: {
                      fontSize: '13px',
                      fontWeight: 900
                    }
                  }
                }
              },
            },
            xaxis: {
              type: 'datetime',
              categories: this.date,
            },
            legend: {
              position: 'right',
              offsetY: 40
            },
            fill: {
              opacity: 1
            }
        }
    }}
}
</script>

<style>

</style>