<template>
  <div>
    <div id="component-header-title">{{ ComponentHeaderTitle }}</div>
    <div id="component-header-content">{{ ComponentHeaderContent }}</div>
  </div>
</template>

<script>
export default {
  name: 'ComponentHeader',
  props: {
    ComponentHeaderTitle: String,
    ComponentHeaderContent: String,
  }
}

</script>

<style>
#component-header-title {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 60px;
  color: #172176;

}
#component-header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 800;
  font-size: 0.8rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474b;
}
</style>