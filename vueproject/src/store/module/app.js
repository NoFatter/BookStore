
import router from '@/router/index'
import {setStore, getStore} from '@/libs/storage'
import util from '@/libs/util'
//states
const state = {
  menuList: [],
  firstRouter: null,
  defaultMenuActive: '0',
  routers: []
}



   
//getters
const getters = {}

//actions
const actions = {
  
}

//mutations
const mutations = {
  SET_FIRST_ROUTER(state, router) {
    state.firstRouter = router
  },
  // 动态添加主界面路由，需要缓存
  UPDATE_APP_ROUTER(state, routes) {
    state.routers.push(...routes);
    router.addRoutes(routes);
  },
  // 动态添加全局路由404、500等页面，不需要缓存
  UPDATE_DEFAULT_ROUTER(state, routes) {
    router.addRoutes(routes);
  },
  // 更新菜单列表
  UPDATE_MENU_LIST(state, routes) {
    state.menuList = routes;
    
  },
  // 设置选择菜单
  SET_DEFAULT_MENU_ACTIVE(state, defaultMenuActive) {
    setStore('defaultMenuActive', defaultMenuActive)
    state.defaultMenuActive = defaultMenuActive
  },
  // 获取选中菜单
  GET_DEFAULT_MENU_ACTIVE(state) {
    const defaultMenuActive = getStore('defaultMenuActive')
    if (util.isEmpty(defaultMenuActive)) {
      state.defaultMenuActive = '0'
    } else {
      state.defaultMenuActive = defaultMenuActive
    }
  },
 
 
}

export default {
  state,
  getters,
  actions,
  mutations
}
