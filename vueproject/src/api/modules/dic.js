import {post,postJson,del} from '../axios'

export function page(params){
   return post('/soft/dic/page',params)
}

export function add(params){
   return postJson('/soft/dic/save',params)
}

export function dele(params){
   return del('/soft/dic/delete',params)
}