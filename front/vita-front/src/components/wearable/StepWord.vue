<template>
  <div id="stepword-main">
    <img style="width: 10px" src="../../../public/wearable/dot.png" />
    <div id="steplang">
      <img id="foot" src="../../../public/wearable/foot.png" />
      <div>
        <div style="color: #666666" id="steplang-top">
          2022년 3월 19일 제일 많이 걸었네요.
        </div>
        <div style="color: #172176" id="steplang-down">
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
  name: "StepWord",
};
</script>

<style>
#stepword-main {
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
#foot {
  width: 120px;
}
#steplang {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.3rem;
}
</style>