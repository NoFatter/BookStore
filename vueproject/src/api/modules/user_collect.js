import {post,postJson,del} from "../axios";

export function getCollectByUserId(params){
    return post('/soft/collect/getCollectByUserId',params)
}
export function add(params){
    return postJson('/soft/collect/addCollect',params)
}
export function delCollect(params){
    return post('/soft/collect/delete',params)
}
