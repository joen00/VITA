import accountApi from '@/api/account'

export default {
  state: {
    user: null,
    token: null,
    userNickname : null,
  },
  getters: {
    user: state => state.user,
    token: state => state.token,
    userNickname: state => state.userNickname
  },
  actions: {
    fetchUser ({state, commit}, callback) {
      state.user
        ? callback && callback()
        : accountApi.getUser(
          res => {
            commit('setUser', res.user)
            callback && callback()
          }
        )
    }
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    },
    setUser (state, user) {
      state.user = user
    },
    setUserNickname(state, userNickname) { 
      state.userNickname = userNickname
    }
  }
}
