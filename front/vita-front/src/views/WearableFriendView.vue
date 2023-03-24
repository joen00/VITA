<template>
    <div class="home">
      <h1>마이 헬스 데이터 with 프렌즈</h1>
      <p>친구들과 나의 건강 데이터를 비교해보세요.</p>
      <FriendList :friendList="friendList"/>
      <FriendWalk :data="stepList" />
      <FriendEnergy :data="energyList" />
      <FriendRhr :data="rhrList" />
      <FriendStress :data="stressList"/>
      <FriendSleep :data="sleepList"/>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import FriendList from '@/components/wearable/friend/FriendList.vue'
  import FriendWalk from '@/components/wearable/friend/FriendWalk.vue'
  import FriendEnergy from '@/components/wearable/friend/FriendEnergy.vue'
  import FriendRhr from '@/components/wearable/friend/FriendRhr.vue'
  import FriendStress from '@/components/wearable/friend/FriendStress.vue'
  import FriendSleep from '@/components/wearable/friend/FriendSleep.vue'
  
  export default {
    name: 'HomeView',data () {
      return {
        friendList: {},
        stepList: [],
        energyList: [],
        rhrList: [],
        stressList: [],
        sleepList: [],
    }
   },
    components: {
      FriendList,
      FriendWalk,
      FriendEnergy,
      FriendRhr,
      FriendStress,
      FriendSleep
    },
   created() {
    this.userAverage();
    this.getFriendList();
    // this.friendAverage();
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
          this.getFriendData(res.data)
        })
      })
      },
      async userAverage() {
        await axios.get(this.$store.state.friendUrl + '/user' , {
      headers: {'Content-Type': 'application/json',
                'token': this.$store.state.test_token},
      }).then(res => {
        this.stepList.push({ "nickName" : res.data.user.userNickname, 
        "Step" : res.data.userAverageStep });
        this.energyList.push({ "nickName" : res.data.user.userNickname, 
        "Energy" : res.data.userAverageEnergy });
        this.rhrList.push({ "nickName" : res.data.user.userNickname, 
        "Rhr" : res.data.userAverageRhr });
        this.stressList.push({ "nickName" : res.data.user.userNickname, 
        "Stress" : res.data.userAverageStress });
        this.sleepList.push({ "nickName" : res.data.user.userNickname, 
        "Sleep" : res.data.userAverageSleep });
      })
      },
      getFriendData(data) {
        data.map(function(e){
          this.stepList.push({ "nickName" : e.user.userNickname, 
          "Step" : e.userAverageStep });
          this.energyList.push({ "nickName" : e.user.userNickname, 
          "Energy" : e.userAverageEnergy });
          this.rhrList.push({ "nickName" : e.user.userNickname, 
          "Rhr" : e.userAverageRhr });
          this.stressList.push({ "nickName" : e.user.userNickname, 
          "Stress" : e.userAverageStress });
          this.sleepList.push({ "nickName" : e.user.userNickname, 
          "Sleep" : e.userAverageSleep });
        })
      },
    },

  }
  </script>
  