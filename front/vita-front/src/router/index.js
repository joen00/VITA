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
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/fileupload',
    name: 'fileupload',
    component: FileUploadView
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MypageView
  },
  {
    path: '/main',
    name: 'mypage',
    component: MypageView
  },
  {
    path: "/wearable",
    name: "wearable",
    component: WearableView,
  },
  {
    path: "/friend",
    name: "friend",
    component: FriendView,
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
    path: "/friendlist",
    name: "friendlist",
    component: FriendListView,
  },
  {
    path: '/wearablefriend',
    name: 'wearablefriend',
    component: WearableFriendView
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
