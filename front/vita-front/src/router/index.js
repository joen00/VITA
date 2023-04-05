import Vue from 'vue'
import VueRouter from 'vue-router'
import WearableView from '../views/WearableView.vue'
import MypageView from '../views/MypageView.vue'
import MainView from '../views/MainView.vue'
import FriendView from '../views/friend/FriendView.vue'
import SymptomView from '../views/SymptomView.vue'
import DiseaseView from '../views/DiseaseView.vue'
import FriendListView from '../views/friend/FriendListView.vue'
import FileUploadView from '../views/mypage/FileUploadView.vue'
import WearableFriendView from '../views/wearable/WearableFriendView.vue'
import IndexPage from '@/components/common/Index'
import OauthRedirect from '@/components/user/oauth/Redirect'
import ExtraInfoFormView from '../views/ExtraInfoFormView.vue'
import WearableApiView from '../views/wearable/WearableApiView.vue'
import LoginFormView from '../views/LoginFormView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: IndexPage,
    children: [
      {
        path: '/',
        name: 'main',
        component: MainView
      },
      {
        path: '/mypage',
        name: 'mypage',
        component: MypageView
      },
      {
        path: '/fileupload',
        name: 'fileupload',
        component: FileUploadView
      },
      {
        path: "/wearables",
        name: "wearables",
        component: WearableView,
      },
      {
      path: '/wearablefriend',
      name: 'wearablefriend',
      component: WearableFriendView
      },
      {
        path: '/wearableapi',
        name: 'wearableapi',
        component: WearableApiView
        },
      {
        path: "/friend",
        name: "friend",
        component: FriendView,
      },
      {
        path: "/friendlist",
        name: "friendlist",
        component: FriendListView,
        },
      {
        path: "/symptom",
        name: "symptom",
        component: SymptomView,
      },
      {
        path: "/disease",
        name: "disease",
        component: DiseaseView,
      },
      {
        path: '/oauth/redirect',
        name: 'OauthRedrect',
        component: OauthRedirect
      },
      {
        path: '/extraInfoForm',
        name: 'ExtraInfoForm',
        component: ExtraInfoFormView
      },
      {
        path: '/loginForm',
        name: 'LoginFormView',
        component: LoginFormView
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
