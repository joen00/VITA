<template>
  <div class="pt-5">
    <div id="vue-header-title">{{ VueHeaderTitle }}</div>
    <div id="vue-header-content">{{ VueHeaderContent }}</div>
  </div>
</template>

<script>
export default {
  name: 'VueHeader',
  props: {
    VueHeaderTitle: String,
    VueHeaderContent: String,
  }
}

</script>

<style>
#vue-header-title {
  font-family: "Inter";
  font-style: normal;
  font-weight: bolder;
  font-size: 2.5rem;
  line-height: 97px;
  color: #172176;
}
#vue-header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  /* margin-top: -2%; */
  color: #47474b;
}
</style>