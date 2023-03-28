import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import account from './modules/account'
Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  state: {
    test_token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyNzAzNjI5NjE0IiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.dV7CsWFo0_-ICBkvTlIQP-75qAuQg_hJ_sJcFjzjLx0',
    // 배포서버
    url: 'https://j8b106.p.ssafy.io/api/wearable/user/',
    friendUrl: 'https://j8b106.p.ssafy.io/api/wearable/friend',
    // 로컬서버
    // url: 'http://localhost:8083/api/wearable/user/',
    // friendUrl: 'http://localhost:8083/api/wearable/friend',
    // 이강윤 : 2715879100
    myUserId: "1",
    // ec2서버 : "https://j8b106.p.ssafy.io:8000"
    // 로컬 유저 서버: "http://localhost:8085"
    serverBaseUrl: "http://localhost:8080/api"
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    account
  },
  plugins: [
    createPersistedState({ storage: window.sessionStorage })
  ],
  strict: debug
})
