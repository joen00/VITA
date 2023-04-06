<template lang="pug">
.bp-header-container(:class='{ sticky: stickyMode }')
  header.bp-header
    div#logo-section
      a.logo-link(@click='go("/")')
        img(src="@/assets/VitaLogo-NavBar.png" id="nav-logo")
    
    div#menu-section
      nav.bp-nav
        ul.nav-list
          li.nav-list-item
              a.nav-list-item-link(id="dropdown") HealthReport
                div.dropdown-content
                  a.drop-down(@click='go("/wearables")') My
                  a.drop-down(@click='go("/wearablefriend")') Friend
                  a.drop-down(@click='go("/wearableapi")') People
              a.nav-list-item-link(@click='go("/friend")') Friend
              a.nav-list-item-link(@click='go("/mypage")') MyPage


    div#login-section
      div.user-profile(v-if="isLoggedIn")
          img(:src="getUserProfileImg"  id="user-profile-img")
          p#user-profile-nickname <strong>{{ getUserNickname }}</strong>

      div.bp-account
        button.account-button(
          v-if='!isLoggedIn'
          type='button'
          @click='$emit("onOpenLoginModal")'
        ) Login
        button.account-button.loggedin(
          v-else
          type='button'
          @click='logout'
        ) Logout
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
        { path: "/wearables", name: "MyHealth" },
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
      if (!this.isLoggedIn) {
        if (path != "/") {
          console.log(path)
          this.$emit("onOpenLoginModal");
          // this.$router.push("/loginForm");
        }
      }
      else this.$router.push(path).then(() => window.scrollTo(0, 0))
    },
    close () {
      this.isOpenAccountDropdown = false
    },
    logout () {
      this.close()
      this.$emit('onLogout')
    },
  },

  computed: {
    ...mapGetters(['token', 'userNickname', 'userProfileImg']),

    isLoggedIn () {
      return this.token != null
    },
    getUserNickname () {
      if (!this.userNickname) return ''
      return this.userNickname
    },
    getUserProfileImg () {
      return this.userProfileImg
    },
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
  width: 100%;
  height: 10px;
  position: absolute;
  z-index: 1;
}
.bp-header-container .bp-header {
  height: 100%;
  display: flex;
  padding: 0 50px;
}
.bp-header-container .bp-header .logo-link {
  cursor: pointer;
  display: block;
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
  text-align: center;
  justify-content: center;
}
.bp-header-container .bp-header .bp-nav .nav-list {
  height: 100%;
  display: flex;
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
  width: 85px;
  height: 60%;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
}
.bp-header-container .bp-header .bp-account .account-button:hover {
  border: none;
  color: #fff;
  font-weight: 600;
  background: #125e7f;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
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
}
li {
  list-style-type : none;
}
#logo-section {
  width: 20%;
  text-align: center;
  justify-content: center;
  margin-top: 5px;
}
#menu-section {
  width: 60%;
  text-align: center;
  display: flex;
}
#login-section {
  width: 20%;
  height: 100%;
  display: flex;
  justify-content: right;
  text-align: center;
}
.user-profile {
  height: 100%;
  display: flex;
  align-items: center;
}
#user-profile-nickname {
  color: #125e7f;
  padding: 5px;
  margin: 15px 40px 15px 10px;
}
#user-profile-img {
  width: 35px;
  height: 35px;
  border-radius: 100%;
}
#dropdown {
  display: inline-block;
}
.dropdown-content {
  display: none;
  margin-top: 20px;
  padding-block: 1px;
  border-radius: 0 0 15px 15px;
}

#dropdown:hover .dropdown-content {
  background-color: #EAFBFF;
}

.dropdown-content a {
  color: #FBD30D;
  padding: 14px 16px;
  margin: 12px 14px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  color: #125e7f;
}

#dropdown:hover .dropdown-content {
  display: block;
}
</style>