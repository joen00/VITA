<template lang="pug">
</template>

<script>
import { mapMutations, mapActions } from 'vuex'

export default {
  created () {
    const token = this.$route.query.token
    console.log('token', token)

    const flag = this.$route.query.extraInfoFlag
    console.log('flag', flag)

    const userNickname = this.$route.query.userNickname
    console.log('userNickname', userNickname)

    if (token) {
      this.setToken(token)
      this.fetchUser()
    }

    if (userNickname) { 
      this.setUserNickname(userNickname)
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
    ...mapMutations(['setToken','setUserNickname'])
  }
}
</script>
