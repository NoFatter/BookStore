import axios from 'axios'
import qs from 'qs'
import route from '@/router/index'
import {getStore} from '@/libs/storage'

axios.defaults.baseURL = "/api";


//http response 拦截器
axios.interceptors.response.use(res =>{
  //console.log(res)
  let data = res.data
  if(200==data.statusCode){
   // console.log('200')
    return Promise.resolve(data);

  }else if(600==data.statusCode){
    route.push('/')
    return Promise.reject('直接跳转到登陆页面')
  }else{
    //console.log('not 200')
    return Promise.reject(data.message)
  }

},
err=>{
  console.log('error')
  return Promise.reject(err)
})


export function get(url,params){
  let token = getStore("token")
    return axios({
        method: 'get',
        url: `${url}`,
        params: params,
        headers:{
          'token':token
        }
      })
}

export function post(url,params){
  let token = getStore("token")
    return axios({
        method: 'post',
        url: `${url}`,
        data: params,
        transformRequest: [function (data) {
            return qs.stringify(data, {allowDots: true})
          }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'token':token
        }
      })
}

export function del(url,params){
  let token = getStore("token")
    return axios({
        method: 'delete',
        url: `${url}`,
        data: params,
        transformRequest: [function (data) {
            return qs.stringify(data, {allowDots: true})
          }],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
            'token':token
        }
      })
}

export function postJson(url,params){
  let token = getStore("token")
    return axios({
        method: 'post',
        url: `${url}`,
        data: params,
        headers: {
          'Content-Type': 'application/json',
          'token':token
        }
      })
}