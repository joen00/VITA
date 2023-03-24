<template>
    <div class="home">
      <h1>마이 헬스 데이터 with 프렌즈</h1>
      <p>친구들과 나의 건강 데이터를 비교해보세요.</p>
      <FriendList :friendList="friendList"/>
      <p>나의 평균입니다</p>
      {{ userData }}
      <p>친구들의 평균입니다</p>
      {{ friendData }}
      <!-- <FriendWalk />
      <FriendEnergy />
      <FriendRhr />
      <FriendStress />
      <FriendSleep /> -->
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import FriendList from '@/components/wearable/friend/FriendList.vue'
  // import FriendWalk from '@/components/wearable/friend/FriendWalk.vue'
  // import FriendEnergy from '@/components/wearable/friend/FriendEnergy.vue'
  // import FriendRhr from '@/components/wearable/friend/FriendRhr.vue'
  // import FriendStress from '@/components/wearable/friend/FriendStress.vue'
  // import FriendSleep from '@/components/wearable/friend/FriendSleep.vue'
  
  export default {
    name: 'HomeView',data () {
      return {
        userData: {},
        friendData: [{}], 
        friendList: {},
    }
   },
    components: {
      FriendList,
      // FriendWalk,
      // FriendEnergy,
      // FriendRhr,
      // FriendStress,
      // FriendSleep
    },
   created() {
    this.getFriendList();
    // this.friendAverage();
    this.userAverage();
  },
    methods: {
      async getFriendList() {
        await axios.get(this.$store.state.friendUrl , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token}
      }).then(res => {
        this.friendList = res.data;
        console.log("친구리스트 가져오기 완료",this.friendList)
        var friendUserIdList = this.friendList.map(function(e){return e.userId;})
        console.log("친구유저ID만 추출", this.friendUserIdList)
          axios.post(this.$store.state.friendUrl + '/all' , friendUserIdList,{
          headers: {'Content-Type': 'application/json'},
          }).then(res => {
          this.friendData = res.data;
          console.log(this.friendData)
        })
      })
      },
      async userAverage() {
        await axios.get(this.$store.state.friendUrl + '/user' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.userData = res.data;
        console.log(this.userData)
      })
      },
      getFriendStep() {
        
      },
      getFriendEnergy() {

      },
      getFriendRhr() {

      },
      getFriendStress() {

      },
      getFriendSleep() {

      },
    },

  }
  </script>
  