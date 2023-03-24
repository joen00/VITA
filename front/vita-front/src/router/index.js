import Vue from 'vue'
import VueRouter from 'vue-router'
import WearableView from '../views/WearableView.vue'
import WearableFriendView from '../views/WearableFriendView.vue'
import MypageView from '../views/MypageView.vue'
import MainView from '../views/MainView.vue'
import FriendView from '../views/FriendView.vue'
import SymptomView from '../views/SymptomView.vue'
import DiseaseView from '../views/DiseaseView.vue'

Vue.use(VueRouter)

const routes = [
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
    path: "/wearable/friend",
    name: "wearableFriend",
    component: WearableFriendView,
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

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
