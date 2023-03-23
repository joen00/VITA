<template>
  <div class="rankwalk">
    <h5>걸음수</h5>
        <div class="rankwalk-item" v-for="rank in walkranks" :key="rank">
            {{ rank.id }}
            <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
            {{ rank.name }}
            {{ rank.score }}
        </div>
  </div>
</template>

<script>
export default {
    name: 'FriendWalk',
    props: {
    msg: String
    },
    data: () => ({
    walkranks: [  { id: 1, name: '김광배', score:'10' },
      { id: 2, name: '이광배', score:'20' },
      { id: 3, name: '차광배', score: '30' }],
    }),
}
</script>

<style>
.rankwalk{
    width: 90%;
    height: 100px;
    margin: 0 auto;
    margin-top: 1.5rem;
    margin-bottom: 1.5rem;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
}

.rankwalk-item{
    float: left;
    width: 33%;
    margin: 0 auto;
}
</style>