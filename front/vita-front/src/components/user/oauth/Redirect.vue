<template lang="pug">
</template>

<script>
import { mapMutations, mapActions } from 'vuex'

export default {
  created () {
    const token = this.$route.query.token
    // console.log('token', token)

    const flag = this.$route.query.extraInfoFlag
    // console.log('flag', flag)

    const userNickname = this.$route.query.userNickname
    // console.log('userNickname', userNickname)

    const userProfileImg = this.$route.query.userProfileImg
    // console.log('userProfileImg', userProfileImg)

    const userPhoneType = this.$route.query.userPhoneType
    // console.log('userPhoneType', userPhoneType)

    if (token) {
      this.setToken(token)
      this.fetchUser()
    }

    if (userNickname) { 
      this.setUserNickname(userNickname)
    }

    if (userProfileImg) {
      this.setUserProfileImg(userProfileImg)
    }

    if (userPhoneType) {
      this.setUserPhoneType(userPhoneType)
    }

    // 추가 정보 미기입시 추가정보 페이지로 replace
    if (flag == 'false') {
      this.$router.replace('/extraInfoForm')
    }
    else {
      this.$router.replace('/')
    }
  },
  methods: {
    ...mapActions(['fetchUser']),
    ...mapMutations(['setToken','setUserNickname','setUserProfileImg','setUserPhoneType'])
  }
}
</script>
