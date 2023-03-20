<template>
 <div class="text-center">
    <div class="container">
        <div id="header">
          <div id="header-title">프렌즈</div>
          <div id="header-content">친구들의 정보를 확인해보세요.</div>
        </div>
            <div>
                <b-button style="background: #B9C6F4; border: none; color: black; font-weight: 600;" to="/friendlist">친구 추가</b-button>
                <div id="nick-search">닉네임을 입력하세요.</div>
            </div>
            <div id="rank-list">
              <h3 id="list-h3">친구리스트</h3>
            </div>
    </div>
    </div>

</template>

<script>
export default {

}
</script>

<style>

/* ----------------------------------------- */
#header {
  width: 100%;
  height: 16%;
  margin-bottom: 2rem;
}
#header-title{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 3rem;
  line-height: 97px;
  color: #172176;
}
#header-content{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474B;
}
#rank-list {
  padding: 0.5rem;
  width: 40%;
  height: 25rem;
  background-color: aquamarine;
  margin: 0 auto;
  margin-top: 0.5rem;
  box-sizing: border-box;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
#list-h3{
  font-family: 'Exo 2';
  font-style: normal;
  font-weight: 700;
  font-size: 26px;
  color: #172176;
}
#nick-search{
    box-sizing: border-box;
    width: 200px;
    height: 40px;

    border: 2px solid #3695BE;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
    border-radius: 20px;
}
</style>