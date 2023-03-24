import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 로컬테스트 주소
    // url: 'http://localhost:8083/wearable/user/',
    // friendUrl: 'http://localhost:8083/wearable/friend',
    // 배포 주소
    url: 'http://j8b106.p.ssafy.io:8000/wearable/user/',
    friendUrl: 'http://j8b106.p.ssafy.io:8000/wearable/friend',

    // 현재 user 서버 시크릿 키에 맞는 jwt토큰
    test_token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyNzAzNjI5NjE0IiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.dV7CsWFo0_-ICBkvTlIQP-75qAuQg_hJ_sJcFjzjLx0'
    // 현재 배포된 서버 시크릿 키에 맞는 jwt 토큰
    // test_token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyNzAzNjI5NjE0IiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.GB6EJNaohcytiI6VZOfKVuYcQ4_VZx88YNmbq_OpmGU'
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
