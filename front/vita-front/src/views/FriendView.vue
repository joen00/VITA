<template>
   <div class="container">
        <div class="header"></div>
        <div class="left">
          <div class ="left-meau"></div>
        </div>
        <div class="right"></div>
    </div>
</template>

<script>

export default {
  name: 'FriendView',

}
</script>

<style>
.container {
  margin-top: 1rem;
  width: 100%;
}
.header {
  width: 100%;
  height: 150px;
  background-color: cornflowerblue;
}
.left {
  width: 40%;
  height: 500px;
  background-color: rgb(250, 151, 151);
  float: left;
}
.right {
  width: 60%;
  height: 500px;
  background-color: aquamarine;
  float: left;
}
</style>
 