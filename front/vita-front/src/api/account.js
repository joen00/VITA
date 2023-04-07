import req from './req-wrapper'

const ACCOUNT_URI = {
  LOGIN: '/api/users/auth/login',
  USER: '/api/users'
}

export default {
  login (body, success, fail) {
    req.post(ACCOUNT_URI.LOGIN, body, success, fail)
  },
  getUser (success) {
    req.get(ACCOUNT_URI.USER, success)
  }
}
