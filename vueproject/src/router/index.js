import Vue from 'vue'
import VueRouter from 'vue-router'

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
//异步路由
const MyLogin = () => import('../views/MyLogin.vue')
const MyRegister = () =>import('../views/MyRegister.vue')
const Index = () =>import('../views/Index')
const About = () =>import('../views/About')
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component:Index
  },
  {
    path: '/myLogin',
    name: 'MyLogin',
    component: MyLogin
  },
  {
    path: '/myRegister',
    name:'MyRegister',
    component:MyRegister
  },
  {
    path: '/myAbout',
    name:'About',
    component:About,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {

  next()
})

export default router
