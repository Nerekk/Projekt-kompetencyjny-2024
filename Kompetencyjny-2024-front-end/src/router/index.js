import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'HomeView',
    component: HomeView
  },
  
  // {
  //   path: '/signup',
  //   name: 'Signup',
  //   component: Signup
  // },
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: Login
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
