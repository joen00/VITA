<template>
  <div class="pt-5">
    <div id="header-title">{{ headerTitle }}</div>
    <div id="header-content">{{ headerContent }}</div>
  </div>
</template>

<script>
export default {
  name: 'VueHeader',
  props: {
    headerTitle: String,
    headerContent: String,
  }
}

</script>

<style>
#header-title {
  font-family: "Inter";
  font-style: normal;
  font-weight: bolder;
  font-size: 2.5rem;
  line-height: 97px;
  color: #172176;
}
#header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 0.8rem;
  line-height: 24px;
  text-align: center;
  margin-top: -2%;
  color: #47474b;
}
</style>