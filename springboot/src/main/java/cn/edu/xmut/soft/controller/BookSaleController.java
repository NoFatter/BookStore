package cn.edu.xmut.soft.controller;

import cn.edu.xmut.soft.entity.BookSale;
import cn.edu.xmut.soft.mapper.BookSaleMapper;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 0:18
 **/
@RestController
@RequestMapping("soft/book-sale")
public class BookSaleController {
    @Autowired
    private BookSaleMapper bookSaleMapper;
    @RequestMapping(method = RequestMethod.POST,value = "/getBookSaleId")
    public Result getSaleByBookId(String id){
        Result result = new Result();
        BookSale params = new BookSale();
        QueryWrapper<BookSale> wrapper = new QueryWrapper<>(params);
        wrapper.eq("book_id",id);
        result.setData(bookSaleMapper.selectList(wrapper));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/save")
    public Result save(@RequestBody BookSale entity){
        Result result = new Result();
        if(entity.getId()==null){
            bookSaleMapper.insert(entity);
        }else {
            bookSaleMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/updateSaleByBookId")
    public Result updateSaleByBookId(String id,Double discounts){
        Result result = new Result();
        QueryWrapper<BookSale> wrapper = new QueryWrapper<>(new BookSale());
        wrapper.eq("book_id",id);
        bookSaleMapper.delete(wrapper);
        BookSale bs = new BookSale();
        bs.setDiscount(discounts);
        bs.setBookId(id);
        bookSaleMapper.insert(bs);
        return result;
    }
}
