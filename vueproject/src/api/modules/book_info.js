import {post,postJson} from "@/api/axios";

export function getBookInfoByBookId(params){
    return post('/soft/book-info/getInfoByBookId',params)
}
export function getAllBookInfos(params){
    return post('/soft/book-info/getAllBookInfo',params)
}
export function page(params){
    return post('soft/book-info/page',params)
}
export function save(params){
    return postJson('soft/book-info/save',params)
}
export function saveImg(params){
    return post('/soft/bookImg/save',params)
}
