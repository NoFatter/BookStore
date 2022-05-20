import {get,post,postJson,del} from '../axios'

export function page(params){
   return post('/soft/dictype/page',params)
}

export function add(params){
   return postJson('/soft/dictype/save',params)
}

export function dele(params){
   return del('/soft/dictype/delete',params)
}

export function getAllDic(params){
   return get('/soft/dictype/getAllDic',params)
}