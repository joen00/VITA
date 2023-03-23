<template>
  <div id="energy-rank-list">
    <h3 id="energy-rank-list-h3" >활동에너지</h3> 
    <div id="energy-rank-list-div">
      <div class="rankenergy-item" v-for="rank in energyranks" :key="rank">
              <span v-bind:style="{ color: rank.color}" >{{ rank.id }}</span>
              <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
              <span>{{ rank.name }}</span>
              <span>{{ rank.score }}</span> |
      </div>
  </div>   
  </div>
</template>

<script>
export default {
    name: 'FriendEnergy',
    props: {
    msg: String
    },
    data: () => ({
      energyranks: [{ id: 1,name: '김광배', score:'10',color:'#FFB800' },
      { id: 2, name: '이광배', score:'20',color:'silver'  },
      { id: 3, name: '차광배', score: '30',color:'#BB6C23'  }],
    }),
}
</script>

<style>

/* .rankenergy{
    width: 90%;
    height: 100px;
    margin: 0 auto;
    margin-top: 1.5rem;
    margin-bottom: 1.5rem;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
} */

.rankenergy-item{
    width: 33%;
    margin: 0 auto;
    font-weight: 800;
    display: flex;
    justify-content: space-around;
    align-items: center;
}

#energy-rank-list-div{
  display: flex;
  justify-content: center;
}

#energy-rank-list {
  padding: 0.5rem;
  width: 80%;
  height: 6rem;
  background-color: rgb(212, 206, 255);
  margin: 0 auto;
  margin-top: 1rem;
  margin-bottom: 2rem;
  box-sizing: border-box;
  border-radius: 30px;

}


#energy-rank-list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 800;
  font-size: 20px;
  color: #172176;
}
</style>