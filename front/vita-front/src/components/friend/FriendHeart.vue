<!-- <template>
  <div id="walk-rank-list">
    <h3 id="walk-rank-list-h3"> 걸음수</h3> 
    <div id="walk-rank-list-div">
      <div class="rankwalk-item" v-for="rank in walkranks" :key="rank">
              <span v-bind:style="{ color: rank.color }">{{ rank.id }}</span>
              <b-avatar variant="info" src="https://placekitten.com/300/300"></b-avatar>
              <span>{{ rank.name }}</span>
              <span>{{ rank.score }}</span>
      </div>
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
      walkranks: [{ id: 1,name: '김광배', score:'10',color:'#FFB800' },
      { id: 2, name: '이광배', score:'20',color:'silver'  },
      { id: 3, name: '차광배', score: '30',color:'#BB6C23'  }],
    }),
}
</script>

<style>
/* .rankwalk{
    width: 90%;
    height: 100px;
    margin: 0 auto;
    margin-top: 1.5rem;
    margin-bottom: 1.5rem;
    background: rgba(255, 255, 255, 0.8);
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
} */

.rankwalk-item{
    width: 33%;
    margin: 0 auto;
    font-weight: 800;
    display: flex;
    justify-content: center;
    align-items: center;
}

#walk-rank-list-div{
  display: flex;
  justify-content: center;
}

#walk-rank-list {
  padding: 0.5rem;
  width: 80%;
  height: 6rem;
  background-color: rgb(255, 255, 255);
  margin: 0 auto;
  margin-top: 1rem;
  box-sizing: border-box;
  box-shadow: 1px 1px 1px 1px rgba(0.25, 0.25, 0.25, 0.25);
  border-radius: 30px;
  /* display: flex;
  justify-content: center; */
  /* overflow: scroll; */
}
/* #walk-rank-list::-webkit-scrollbar {
  display: none;
}

#walk-rank-list {
  -ms-overflow-style: none; 
  scrollbar-width: none; 
} */

#walk-rank-list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 800;
  font-size: 20px;
  color: #172176;
}
</style> -->