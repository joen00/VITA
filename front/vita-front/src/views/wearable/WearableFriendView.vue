<template>
    <div class="text-center">
      <div class="container">
        <!-- <div id="header">
        <div id="header-title">마이 헬스 데이터 with 프렌즈</div>
        <div id="header-content">친구들과 나의 건강 데이터를 비교해보세요.</div>
        <div id="header-content1">친구의 프로필을 클릭하고 비교하기 버튼을 누르면 친구의 건강 데이터가 실시간으로 그래프에 반영된답니다. </div>
        <div id="header-content2">* 친구는 한 번에 최대 4명까지만 추가 가능합니다. </div>
            
        <div id="wfriendlist"> -->
          <!-- <div style="width:90%; height:150px">
   
            <slider ref="slider" :options="options" @slide='slide' @tap='onTap' @init='onInit'>
               
                <slideritem v-for="(item,index) in someList" :key="index" :style="item.style">{{item.html}}</slideritem>
   
                <div slot="loading">loading...</div>
            </slider>
          </div> -->
          <hooper :infiniteScroll="true" :itemsToShow="9" :centerMode="true">
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <Slide>
              <div class="useritem"></div>
            </Slide>
            <hooper-navigation slot="hooper-addons"></hooper-navigation>
            <hooper-pagination slot="hooper-addons"></hooper-pagination>
          </hooper>
        </div>
       <!-- </div>
      </div> -->
    </div>
  </template>
  
  <script>
import {
  Hooper,
  Slide,
  Pagination as HooperPagination,
  Navigation as HooperNavigation
} from 'hooper';
import 'hooper/dist/hooper.css';
export default {
  name: 'App',
  components: {
    Hooper,
    Slide,
    HooperPagination,
    HooperNavigation
  },
  data() {
    return {
      hooperSettings: {
        itemsToShow: 2,
        centerMode: true
      }
    };
  }
}



// import { slider, slideritem } from 'vue-concise-slider'
//   export default {
//     el: '#app',
// data () {
//    return {
//      someList : [
//        {
//          html: 'slider1',
//          style: {
//            'background': '#4abf8a',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider2',
//          style: {
//            'background': '#4bbfc3',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider3',
//          style: {
//            'background': '#7baabe',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider4',
//          style: {
//            'background': '#7caabe',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider5',
//          style: {
//            'background': '#4abf8a',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider6',
//          style: {
//            'background': '#4bbfc3',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },
//        {
//          html: 'slider6',
//          style: {
//            'background': '#4bbfc3',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },{
//          html: 'slider6',
//          style: {
//            'background': '#4bbfc3',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        },{
//          html: 'slider6',
//          style: {
//            'background': '#4bbfc3',
//            'width': '10%',
//            'height' : '10%',
//            'margin-right': '2%'
//          }
//        }
//      ],
//      //Slider configuration [obj]
//      options: {
//        currentPage: 0,
//        tracking: false,
//        thresholdDistance: 100,
//        thresholdTime: 300,
//        infinite: 4,
//        slidesToScroll: 2,
//        loop: true,
//      }
//    }
//  },
//  components: {
//    slider,
//    slideritem
//  }
//   };

  </script>
  
  <style>
  /* ----------------------------------------- */
#header-content1{
    font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin-top: 1rem;
  color: #47474b;
}
#header-content2{
font-family: 'Inter';
font-style: normal;
font-weight: 400;
font-size: 15px;
line-height: 24px;
color: #909090;
margin-bottom: 3rem;
}
#wfriendlist{
    box-sizing: border-box;
width: 100%;
height: 180px;

background: #FFFFFF;
box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
border-radius: 30px;
}
.useritem{
  width: 100px;
  height: 200px;
  background-color: #47474b;
}
  </style>