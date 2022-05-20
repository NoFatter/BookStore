import Vue from 'vue'
import Vuex from 'vuex'
import app from './module/app'
import {getAllDic} from '@/api/modules/dictype'

Vue.use(Vuex)

export default new Vuex.Store({
  //data
  state: {
    rootCount : 1,
    list:[],
    dicMap:{}//useful=>list
  },
  //setters
  mutations: {
      setList(state,newList){
        state.list = newList
      },
      setDicMap(state,map){
        state.dicMap = map
       // console.log(state.dicMap)
      }
  },
  //methods
  actions: {
    getDicMap({commit}){
        getAllDic().then(res =>{
            //res = result
            let map = res.data
            commit('setDicMap',map)
        }).catch(err =>{
            console.log(err)
        })
    },

  },
  //模块化
  modules: {
    app
  },
  //getters
  getters:{

  }
})
