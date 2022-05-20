import {get,post,postJson} from '../axios'




export function page(params){
   return post('/soft/role/page',params)
}

export function add(params){
   return postJson('/soft/role/save',params)
}

export function updateUseful(params){
   return post('/soft/role/updateUseful',params)
}

export function getAll(params){
   return get('/soft/role/getAll',params)
}
