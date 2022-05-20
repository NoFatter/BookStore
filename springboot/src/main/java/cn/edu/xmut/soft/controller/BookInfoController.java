package cn.edu.xmut.soft.controller;

import cn.edu.xmut.common.BaseController;
import cn.edu.xmut.soft.entity.Book;
import cn.edu.xmut.soft.entity.BookInfo;
import cn.edu.xmut.soft.mapper.BookInfoMapper;
import cn.edu.xmut.soft.mapper.BookMapper;
import cn.edu.xmut.utils.DateTool;
import cn.edu.xmut.utils.JwtUtil;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 10:37
 **/
@RestController
@RequestMapping("/soft/book-info")
public class BookInfoController extends BaseController {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(method = RequestMethod.POST,value = "/getInfoByBookId")
    public Result getInfoByBookId(String id){
        Result result = new Result();
        BookInfo bookInfoParams = new BookInfo();
        Book bookParams = new Book();
        QueryWrapper<BookInfo> bookInfoWrapper = new QueryWrapper<>(bookInfoParams);
        QueryWrapper<Book> bookWrapper = new QueryWrapper<>(bookParams);
        bookWrapper.eq("id",id);
        bookInfoWrapper.eq("book_id",id);
        List<BookInfo> bookInfoList = bookInfoMapper.selectList(bookInfoWrapper);
        List<Book> bookList = bookMapper.selectList(bookWrapper);
        for(Book book : bookList){
            book.setEtc(getEmptyBookInfo(book.getId()));
        }
        for(Book book :bookList){
            for(BookInfo bookInfo :bookInfoList){
                if(book.getId().equals(bookInfo.getBookId())){
                    book.setEtc(bookInfo);
                }
            }
        }
        result.setData(bookList);
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/getAllBookInfo")
    public Result getAllBookInfo(){
        Result result = new Result();
        List<Book> list1 = bookMapper.selectList(null);
        List<BookInfo> list2 = bookInfoMapper.selectList(null);
        for(Book book : list1){
            book.setEtc(getEmptyBookInfo(book.getId()));
        }
        for(Book book :list1){
            for(BookInfo bookInfo :list2){
                if(book.getId().equals(bookInfo.getBookId())){
                    book.setEtc(bookInfo);
                }
            }
        }
        result.setData(list1);
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
            Integer pageNum,
            Integer pageSize,
            String title) {
        Result result = new Result();
        Page<Book> page = new Page<Book>(pageNum, pageSize);
        Book params = new Book();
        QueryWrapper<Book> bookWrapper = new QueryWrapper<Book>(params);
        if(title != null && !"".equals(title)){
            bookWrapper.like("title",title);
        }
        List<Book> bookList = bookMapper.selectPage(page,bookWrapper).getRecords();
        BookInfo bookInfoParams = new BookInfo();
        for(Book book:bookList){
            QueryWrapper<BookInfo> bookInfoWrapper = new QueryWrapper<BookInfo>(bookInfoParams);
            bookInfoWrapper.eq("book_id",book.getId());
            List bookInfoList = bookInfoMapper.selectList(bookInfoWrapper);
            if(bookInfoList.isEmpty()){
                book.setEtc(getEmptyBookInfo(book.getId()));
            }else{
                book.setEtc(bookInfoList.get(0));
            }
        }
        result.setData(bookList);
        return result;
    }
    @RequestMapping(method=RequestMethod.POST,value="/save")
    public Result save(@RequestBody BookInfo entity){
        Result result = new Result();
        String token = request.getHeader("Token");
        String user_id = JwtUtil.getUserIdByToken(token);
        if(entity.getId()==null){
            entity.setUpdateTime(DateTool.getCurrTime());
            entity.setCreateUser(user_id);
            bookInfoMapper.insert(entity);
        }else{
            bookInfoMapper.updateById(entity);
        }
        return result;
    }
    public BookInfo getEmptyBookInfo(String bookId){
        BookInfo empty = new BookInfo();
        empty.setAuthor("暂无");
        empty.setCreateUser("暂无");
        empty.setUpdateTime("暂无");
        empty.setId(null);
        empty.setBookId(bookId);
        empty.setInfo("暂无");
        empty.setImg("error.jpg");
        return empty;
    }
}
