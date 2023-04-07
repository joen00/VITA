<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  components: {
  },
  data: () => ({
  }),
};
</script>

<style>
#app {
  background: linear-gradient(
      146.55deg,
      rgba(174, 162, 220, 0.2) -70.09%,
      rgba(140, 112, 253, 0) 55.52%
    ),
    linear-gradient(
      0deg,
      rgba(243, 255, 228, 0.2) -26.8%,
      rgba(178, 215, 149, 0.012) 16.1%,
      rgba(220, 243, 253, 0) 131.6%
    ),
    linear-gradient(239.1deg, #e2faff -29.57%, rgba(222, 243, 248, 0) 131.52%);
  height: 100vh;
  overflow: auto;
  margin:0;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

@font-face {
  font-family: 'Cafe24Ssurround';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
* {
  font-family: "Cafe24Ssurround" !important;
}
</style>
