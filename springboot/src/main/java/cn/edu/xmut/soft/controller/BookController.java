package cn.edu.xmut.soft.controller;

import cn.edu.xmut.utils.DateTool;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import cn.edu.xmut.soft.mapper.BookMapper;
import cn.edu.xmut.soft.entity.Book;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.common.BaseController;

/**
 * <p>
 * 书籍表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-03
 */
@RestController
@RequestMapping("/soft/book")
public class BookController extends BaseController {
    @Autowired
    private BookMapper bookMapper;
    @RequestMapping(method = RequestMethod.POST,value = "/all")
    public Result all(){
        Result result = new Result();
        result.setData(bookMapper.selectList(null));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize,
        String title,
        Integer useful) {
        Result result = new Result();
        Page<Book> page = new Page<Book>(pageNum, pageSize);
        Book params = new Book();
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>(params);

        if(title != null && !"".equals(title)){
            wrapper.like("title",title);
        }
        if(useful != null){
            wrapper.eq("useful",useful);
        }
        result.setData(bookMapper.selectPage(page, wrapper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody Book entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            entity.setUseful(1);
            entity.setCreateTime(DateTool.getCurrTime());
            bookMapper.insert(entity);
        } else {
            bookMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.DELETE,value="/delete")
    public Result delete( String ids) {
        Result result = new Result();
        List<String> deleteIds = new ArrayList<String>();
        for (String id : ids.split(",")) {
            deleteIds.add(id);
        }
        bookMapper.deleteBatchIds(deleteIds);
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/updateUseful")
    public Result updateUseful( String ids,Integer useful) {
        Result result = new Result();
        for (String id : ids.split(",")) {
            //修改值
            Book book = new Book();
            book.setUseful(useful);
            //修改条件
            UpdateWrapper<Book> bookUpdateWrapper = new UpdateWrapper<>();
            bookUpdateWrapper.eq("id", id);

            bookMapper.update(book, bookUpdateWrapper);
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/getBookById")
    public Result getBookById(String id){
        Result result = new Result();
        Book param = new Book();
        QueryWrapper<Book> wrapper= new QueryWrapper<>(param);
        wrapper.eq("id",id);
        result.setData(bookMapper.selectList(wrapper));
        return result;
    }
}
