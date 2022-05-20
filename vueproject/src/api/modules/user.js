import {del, post, postJson} from '../axios'



export function login(params){
    return postJson('/soft/user/login',params)
}

export function register(params){
   return postJson('/soft/user/save',params)
}

export function page(params){
   return post('/soft/user/page',params)
}

export function add(params){
   return postJson('/soft/user/save',params)
}

export function updateUseful(params){
   return post('/soft/user/updateUseful',params)
}
export function deleteUser(params){
    return del('/soft/user/delete',params)
}
