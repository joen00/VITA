<template>
  <div>
    <div id="main-middle">
    <div id="main-top">
        <div id="main-top-title">
          <div id="main-top-title-p">건강한 일상을 위한 지름길</div>
          <div id="main-top-title-p">VITA와 함께 건강을 시각화하다</div>
          <b-button class="btn" v-b-modal.modal-scrollable id="btn-page" to="/wearable"
          >VITA 시작하기</b-button
        >
        </div>
        <div id="main-top-img">
          <img style="width: 400px;" src="../../public/main/watch3d.gif">

        </div>
    </div>
    
      <div id="main-middle1">
        <div id="main-middle1-title">나의 건강상태 알아보고 싶었던 적 없나요?</div>
          <div id="main-middle1-div">
            <div id="main-middle1-div-item">
              <img style="width: 100%; height: 65%;" src="../../public/main/maingraph.png">
              <pre id="main-middle1-div-p" style="height: 15%;">
    “발음을 교정하고 싶은데,
    학원 가격이 너무 부담돼요”</pre>
              <div style="font-weight:550; color: gray; font-size:12px; margin-top: 1rem; height: 10%;">20살 대학생 김00 </div>
            </div>
            <div id="main-middle1-div-item">
              <img style="width: 100%; height: 65%;" src="../../public/main/maingraph.png">
              <pre id="main-middle1-div-p" style="height: 15%;">
    “발음을 교정하고 싶은데,
    학원 가격이 너무 부담돼요”</pre>
              <div style="font-weight:550; color: gray; font-size:12px; margin-top: 1rem; height: 10%;">20살 대학생 김00 </div>
            </div>
            <div id="main-middle1-div-item">
              <img style="width: 100%; height: 65%;" src="../../public/main/maingraph.png">
              <pre id="main-middle1-div-p" style="height: 15%;">
    “발음을 교정하고 싶은데,
    학원 가격이 너무 부담돼요”</pre>
              <div style="font-weight:550; color: gray; font-size:12px; margin-top: 1rem; height: 10%;">20살 대학생 김00 </div>
            </div>
          </div>
        </div>
      <div id="main-middle2">
        <div id="main-middle2-div-title">
          <p>VITA가 제안하는</p>
          <p>건강리포트</p>
        </div>
        <!-- middle2 -->
        <div id="main-middle2-div">
          <!-- div 1 -->
          <div id="main-middle2-div-img">
            <img style="box-shadow: 0px 0px 10px lightgray; width: 100%; border-radius: 25px;" src="@/../public/main/wearablegif.gif">
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4E8AFF;">Step 1.</p>
            <pre style="font-size: 1.2rem; font-weight: 550; font-family: 'Inter';
  font-style: normal;">
삼성 헬스앱에서 건강정보
내보내기기
            </pre>
            <pre style="color: gray; margin-top: -1rem; font-size: 1rem; font-weight: 800; font-family: 'Inter';
  font-style: normal;">
가이드를 따라,
삼성 헬스앱의 건강정보를 업로드하세요.
            </pre>
          </div>
          <!-- div1 끝 -->
        </div>
        <div id="main-middle2-div">
          <!-- div2 시작 -->
          <div id="main-middle2-div-img">
            <img style="box-shadow: 0px 0px 10px lightgray; width: 100%; border-radius: 25px;" src="@/../public/main/wearablegif.gif">
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4E8AFF;">Step 1.</p>
            <pre style="font-size: 1.2rem; font-weight: 550; font-family: 'Inter';
  font-style: normal;">
삼성 헬스앱에서 건강정보
내보내기기
            </pre>
            <pre style="color: gray; margin-top: -1rem; font-size: 1rem; font-weight: 800; font-family: 'Inter';
  font-style: normal;">
가이드를 따라,
삼성 헬스앱의 건강정보를 업로드하세요.
            </pre>
          </div>
          <!-- div2 끝 -->
        </div>
        <div id="main-middle2-div">
          <!-- div3 시작 -->
          <div id="main-middle2-div-img">
            <img style="box-shadow: 0px 0px 10px lightgray; width: 100%; border-radius: 25px;" src="@/../public/main/wearablegif.gif">
          </div>
          <div id="main-middle2-div-items">
            <p style="font-size: 1.8rem; font-weight: 800; color: #4E8AFF;">Step 1.</p>
            <pre style="font-size: 1.2rem; font-weight: 550; font-family: 'Inter';
  font-style: normal;">
삼성 헬스앱에서 건강정보
내보내기기
            </pre>
            <pre style="color: gray; margin-top: -1rem; font-size: 1rem; font-weight: 800; font-family: 'Inter';
  font-style: normal;">
가이드를 따라,
삼성 헬스앱의 건강정보를 업로드하세요.
            </pre>
          </div>
          <!-- div3 끝 -->
        </div>
      </div>
      <div id="main-footer">
        <div id="main-footer-title-p">건강한 일상을 위한 지름길</div>
          <div id="main-footer-title-p">VITA와 함께 건강을 시각화하다</div>
          <b-button class="btn" v-b-modal.modal-scrollable id="btn-footer-page" to="/wearable"
          >VITA 시작하기</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>


export default {
  name: 'MainView'
}
</script>

<style>
#main-middle2-div-img{
  width: 50%;
  height: 100%;
  /* background-color: #3695be; */
  display: flex;
  flex-direction: column;
  justify-content:center;
  align-items: center;
  
}
#main-middle2-div-items{
  width: 50%;
  height: 100%;
  /* background-color: #d1d7d9; */
  display: flex;
  flex-direction: column;
  justify-content:center;
  padding-left: 10%;
  padding-right: 10%;
  /* align-items: center; */
}


#main-top-img{
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content:center;
  align-items: center;
  /* margin-left: 10rem; */
}
#main-middle{
  height: 490vh;
  background-color: #ffffff;
}

#main-middle1{
  height: 100vh;
  background-color: #ffffff;
  /* background-color: #172176; */
}

#main-middle1-title{
  height: 15%;
  /* background-color: #3695be; */
  display: flex;
  flex-direction: column;
  justify-content:center;
  align-items: center;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}
#main-middle1-div{
  height: 80%;
  background-color:#ebf7fd;
  display: flex;
  justify-content:center;
  align-items: center;
}
#main-middle1-div-item{
  width: 280px;
  height: 350px;
  left: 215px;
  top: 1688px;
  background: #ffffff;
  box-shadow: 0px 0px 15px #d0ecfd;
  border-radius: 30px;
  margin: 2.5rem;
  display: flex;
  justify-content:center;
  align-items: center;
  flex-direction: column;
}
#main-middle1-div-p{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  margin-bottom: 1rem;
}

#main-middle2{
  height: 200vh;
  background-color: #ffffff;
  display: flex;
  justify-content:center;
  align-items: center;
  flex-direction: column;
  padding-left: 15rem;
  padding-right: 15rem;
}

#main-middle2-div{
  width: 100%;
  height: 60vh;
  /* background-color: #c8cdff; */
  display: flex;
  justify-content:center;
  align-items: center;
  /* flex-direction: column; */
}
#main-middle2-div-title{
  width: 100%;
  height: 50vh;
  /* background-color: #c8cdff; */
  display: flex;
  justify-content:center;
  align-items: center;
  flex-direction: column;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}

#main-footer{
  height: 50vh;
  background-color: #ffffff;
  height: 710px;
  background-size: cover;
  background-position: center;
  overflow: auto;
  background-image: url("@/../public/main/footer.png");
  background-repeat: no-repeat;
  display: flex;
  justify-content:center;
  align-items: center;
  flex-direction: column;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  color: #172176;
}



#btn-footer-page {
  margin-top: 1rem;
  width: 15%;
  height: 45px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items:center;
  justify-content: center;
}

#btn-footer-page:hover {
  margin-top: 1rem;
  width: 15%;
  height: 45px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items:center;
  justify-content: center;
}




#btn-page {
  margin-top: 1rem;
  width: 40%;
  height: 45px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items:center;
  justify-content: center;
}

#btn-page:hover {
  margin-top: 1rem;
  width: 40%;
  height: 45px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  display: flex;
  align-items:center;
  justify-content: center;
}


#main-top-title-p{
  margin-bottom: -1rem;
}

#main-top-title{
  /* width: 703px;
  height: 123px; */
  width: 30%;
  height: 100%;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 27px;
  line-height: 61px;
  text-align: center;
  color: #172176;
  display: flex;
  flex-direction: column;
  justify-content:center;
  align-items: center;
  margin-left: 10rem;
}
#main-top{
  height: 710px;
  background-size: cover;
  background-position: center;
  overflow: auto;
  background-color: #ffffff;
  background-image: url("@/../public/main/Group.png");
  background-repeat: no-repeat;
  display: flex;
  margin-bottom: 5rem;
}
</style>
  