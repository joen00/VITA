<template>
    <div>
      <h1> 친구리스트 </h1>
      {{ friendList }}
      <br>
    
  </div>
  </template>
  
  <script>

  export default {
    name: 'FriendList',
    data () {
      return {
        data: [],
    }
   },
   props: {
    friendList: Array,
   },
   created() {
  },
    methods: {
    },
  }
  </script>
  
  <style>
  
  </style>
  