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
    serverBaseUrl: "http://localhost:8080"
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
