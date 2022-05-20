import {get,post,del,postJson} from '../axios'


export function getAllTree(params){
   return post('/soft/menu/getAllTree',params)
}

export function getAllTreeByUserId(params){
   return get('/soft/menu/getAllTreeByUserId',params)
}


export function add(params){
   return postJson('/soft/menu/save',params)
}

export function dele(params){
   return del('/soft/menu/delete',params)
}
