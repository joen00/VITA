import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import account from './modules/account'
Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  state: {
    // 이강윤 : 2715879100
    myUserId: "2715879100",
    // ec2서버 : "http://j8b106.p.ssafy.io"
    // 로컬 유저 서버: "http://localhost:8085"
    serverBaseUrl: "http://localhost:8085"
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
