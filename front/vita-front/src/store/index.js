import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import account from './modules/account'
Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  state: {
    myUserId: "1",
    test_token: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyNzAzNjI5NjE0IiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.dV7CsWFo0_-ICBkvTlIQP-75qAuQg_hJ_sJcFjzjLx0',
    url: 'http://j8b106.p.ssafy.io:8000/wearable/user/',
    friendUrl: 'http://j8b106.p.ssafy.io:8000/wearable/friend',
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
