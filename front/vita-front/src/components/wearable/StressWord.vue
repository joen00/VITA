<template>
  <div id="stressword-main">
    <img style="width: 10px" src="../../../public/wearable/dot.png" />
    <div id="stresslang">
      <img id="foot" src="../../../public/wearable/foot.png" />
      <div v-if="word">
        <div style="color: #666666" id="stresslang-top">
          {{ word }} 스트레스가 가장 적었습니다.
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
import axios from "axios";

export default {
  name: "stressWord",
  data: () => ({
    word: "",
  }),
  created() {
    this.getWord();
  },
  methods: {
    getWord() {
      axios
        .get(this.$store.state.url + "stress/less", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((res) => {
          this.word = res.data
        });
    },
  }
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