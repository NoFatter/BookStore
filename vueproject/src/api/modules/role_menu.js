import {post} from '../axios'


export function getMenuByRoleId(params){
   return post('/soft/role-menu/getMenuByRoleId',params)
}
export function updateMenuByRoleId(params){
   return post('/soft/role-menu/updateMenuByRoleId',params)
}