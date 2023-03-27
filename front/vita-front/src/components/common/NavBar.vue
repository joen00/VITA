<template lang="pug">
.bp-header-container(:class='{ sticky: stickyMode }')
  header.bp-header
    nav.bp-nav
      ul.nav-list
        li.a.logo-link(@click='go("/")')
          img(src="@/assets/VitaLogo-NavBar.png" id="nav-logo")

        li.nav-list-item(v-for='m in menu')
          a.nav-list-item-link(@click='go(m.path)') {{ m.name }}
    .bp-account
      button.account-button(
        v-if='!isLoggedIn'
        type='button'
        @click='$emit("onOpenLoginModal")'
      ) 로그인
      button.account-button.loggedin(
        v-else
        type='button'
        @click='logout'
      ) 로그아웃
</template>

<script>
import $ from '@/utils'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'NavBar',
  props: ['stickyMode'],
  data () {
    return {
      isOpenAccountDropdown: false,
      menu: [
        { path: "/wearable", name: "MyHealth" },
        { path: "/friend", name: "Friend" },
        { path: "/mypage", name: "MyPage" }
      ]
    }
  },
  created () {
    this.isLoggedIn && this.fetchUser()
  },
  methods: {
    ...mapActions(['fetchUser']),
    go (path) {
      this.close()
      if (this.$route.path !== path) this.$router.push(path)
    },
    close () {
      this.isOpenAccountDropdown = false
    },
    logout () {
      this.close()
      this.$emit('onLogout')
    }
  },
  computed: {
    ...mapGetters(['token', 'user']),
    isLoggedIn () {
      return this.token != null
    },
    isAdmin () {
      return this.user && this.user.roleType === 'ROLE_ADMIN'
    }
  },
  directives: {
    'click-outside': $.clickOutside()
  }
}
</script>

<style scoped>
.bp-header-container {
  color: #fff;
  /* background: #47474B; */
  /* background: #2f2f56; */
  background: linear-gradient(239.1deg, #e2faff -29.57%, rgba(222, 243, 248, 0) 131.52%);
  /* background: rgb(254, 255, 213); */
  width: 100%;
  height: 10px;
  position: absolute;
  z-index: 1;
  justify-content: center;
  display: flex;
}
.bp-header-container .bp-header {
  height: 100%;
  display: flex;
}
.bp-header-container .bp-header .logo-link {
  cursor: pointer;
}
.bp-header-container .bp-header .bp-logo {
  flex: 0;
  height: 100%;
  margin: auto 10px;
}
.bp-header-container .bp-header .bp-nav {
  flex: 1;
  display: flex;
  height: 100%;
}
.bp-header-container .bp-header .bp-nav .nav-list {
  height: 100%;
  display: flex;
  /* justify-content: center; */
}
.bp-header-container .bp-header .bp-nav .nav-list .nav-list-item {
  height: 100%;
  display: flex;
}
.bp-header-container .bp-header .bp-nav .nav-list .nav-list-item .nav-list-item-link {
  margin: auto;
  line-height: 5px;
  padding: 15px;
  cursor: pointer;
  color: #FBD30D;
  font-weight: 900;
  font-size: larger;
  text-decoration: none;
}
.bp-header-container .bp-header .bp-nav .nav-list .nav-list-item .nav-list-item-link:hover {
  color: var(--theme-ui-colors-link-color, rgb(111, 174, 201));
}
.bp-header-container .bp-header .bp-search {
  flex: 0;
  height: 100%;
}
.bp-header-container .bp-header .bp-search .search-button {
  background: transparent;
  cursor: pointer;
  padding: 11px 8px;
}
.bp-header-container .bp-header .bp-search .search-button:hover {
  color: #8a4baf;
}
.bp-header-container .bp-header .bp-account {
  height: 100%;
  display: flex;
  align-items: center;
}
.bp-header-container .bp-header .bp-account .account-button {
  background: #3592ba;
  color: #fff;
  cursor: pointer;
  margin-right: 15px;
  font-size: smaller;
  width: 90px;
  height: 60%;
  border: 1px solid #3592ba;
  border-radius: 5px;
}
.bp-header-container .bp-header .bp-account .account-button:hover {
  /* color: #8a4baf; */
  background: #d4ecf7;
  color: #106285;
}
.bp-header-container .account-dropdown {
  width: 100px;
  background: #fff;
  color: #000;
  border: 1px solid #000;
  position: absolute;
  top: 50px;
  right: 0;
}
.bp-header-container .account-dropdown ul li {
  height: 2rem;
}
.bp-header-container .account-dropdown ul li button {
  background: #fff;
  width: 100%;
  height: 100%;
  cursor: pointer;
}
.bp-header-container .account-dropdown ul li button:hover {
  background: rgba(0, 0, 0, .02);
}
.bp-header-container.sticky {
  top: 0;
  /* position: fixed; */
  border-bottom: 1px solid #d6d6d6;
  box-shadow: 0 6px 15px 0 rgba(0, 0, 0, .02);
}
@media screen and (min-width: 768px) {
  .bp-header-container {
    height: 50px;
  }
  .bp-header-container .bp-header .bp-nav .nav-list .nav-list-item .nav-list-item-link {
    padding: 25px;
  }
  .bp-header-container .bp-header .bp-search .search-button {
    padding: 21px 15px;
  }
  .bp-header-container .bp-header .bp-account .account-button {
    /* padding: 10px 10px; */
  }
  .bp-header-container .bp-header .bp-account .account-button.loggedin {
    padding: 10px 10px;
  }
  .bp-header-container .account-dropdown {
    top: 70px;
  }
}
</style>

<style>
.bp-header-container .bp-header .logo-link .bp-logo svg {
  width: 48px;
  height: 48px;
  margin: .5px 0;
}
@media screen and (min-width: 768px) {
  .bp-header-container .bp-header .logo-link .bp-logo svg {
    width: 64px;
    height: 64px;
    margin: 2.5px 0;
  }
}
#nav-logo {
  width: 100px;
  height: 40px;
  margin-top: 5px;
}
li {
  list-style-type : none;
}
</style>
