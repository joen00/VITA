<template>
  <div id="stressword-main">
    <img style="width: 10px" src="../../../public/wearable/dot.png" />
    <div id="stresslang">
      <img id="foot" src="../../../public/wearable/foot.png" />
      <div>
        <div style="color: #666666" id="stresslang-top">
          2022년 5월 19일 스트레스가 가장 적었습니다.
        </div>
        <div style="color: #172176" id="stresslang-down">
          그날의 하루는 어떠셨나요?
        </div>
      </div>
    </div>
    <img
      style="width: 10px"
      id="foot"
      src="../../../public/wearable/dot2.png"
    />
  </div>
</template>

<script>
export default {
  name: "stressWord",
};
</script>

<style>
#stressword-main {
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#foot {
  width: 120px;
}
#stresslang {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.3rem;
}
</style>