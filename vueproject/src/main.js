import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
//element ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import global from '@/global'
import mixin from '@/libs/mixin'
import util from "@/libs/util"

//导入haspermission  hasRole
import hasPermission from '@/libs/directive/hasPermission'

Vue.config.productionTip = false

Vue.prototype.GL = global

Vue.mixin(mixin)
Vue.use(hasPermission)

//使用element-ui
Vue.use(ElementUI, {
  size: 'small',
  zIndex: 3000
})

//全局指令
// Vue.directive('fo', {
//   // 当被绑定的元素插入到 DOM 中时……
//   inserted: function (el) {
//     // 聚焦元素
//     el.focus()
//   }
// })

Vue.filter('uppercase',value =>{
  return value.toUpperCase()
})
new Vue({
  router,
  store,
  mounted() {
    //到后台获取所有的字典数据，放入vuex
    this.$store.dispatch('getDicMap')
    //刷新时，自动更新菜单栏
    util.initRouter(this)
  },
  render: h => h(App)
}).$mount('#app')
