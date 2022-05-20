import {post} from '../axios'


export function getRoleByUserId(params){
   return post('/soft/user-role/getRoleByUserId',params)
}
export function updateRoleByUserId(params){
   return post('/soft/user-role/updateRoleByUserId',params)
}