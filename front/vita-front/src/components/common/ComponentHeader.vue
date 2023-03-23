<template>
  <div>
    <div id="header-title">{{ headerTitle }}</div>
    <div id="header-content">{{ headerContent }}</div>
  </div>
</template>

<script>
export default {
  name: 'ComponentHeader',
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
  font-weight: 600;
  font-size: 60px;
  line-height: 97px;
  color: #172176;
}
#header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474b;
}
</style>