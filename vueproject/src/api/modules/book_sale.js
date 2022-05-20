import {post} from "@/api/axios";

export function getSaleByBookId(params){
    return post('/soft/book-sale/getBookSaleId',params)
}
export function updateSaleByBookId(params){
    return post('/soft/book-sale/updateSaleByBookId',params)
}