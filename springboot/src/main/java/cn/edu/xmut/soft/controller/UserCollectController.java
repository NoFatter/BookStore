package cn.edu.xmut.soft.controller;

import cn.edu.xmut.common.BaseController;
import cn.edu.xmut.soft.entity.UserCollect;
import cn.edu.xmut.soft.mapper.UserCollectMapper;
import cn.edu.xmut.utils.JwtUtil;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 19:23
 **/
@RestController
@RequestMapping("/soft/collect")
public class UserCollectController extends BaseController {
    @Autowired
    private UserCollectMapper userCollectMapper;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping(method = RequestMethod.POST,value = "/getCollectByUserId")
    public Result getCollectByUserId(){
        String token = request.getHeader("Token");
        String id = JwtUtil.getUserIdByToken(token);
        Result result = new Result();
        UserCollect params = new UserCollect();
        QueryWrapper<UserCollect> wrapper = new QueryWrapper<>(params);
        wrapper.eq("user_id",id);
        result.setData(userCollectMapper.selectList(wrapper));
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/addCollect")
    public Result addCollect(@RequestBody UserCollect entity){
        String token = request.getHeader("Token");
        String userId = JwtUtil.getUserIdByToken(token);
        Result result = new Result();
        if(entity.getId() == null){
            entity.setUserId(userId);
            userCollectMapper.insert(entity);
        }else {
            userCollectMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/delete")
    public Result deleteCollect(String bookId){
        String token = request.getHeader("Token");
        String userId = JwtUtil.getUserIdByToken(token);
        Result result = new Result();
        UserCollect params = new UserCollect();
        QueryWrapper<UserCollect> wrapper= new QueryWrapper<>(params);
        wrapper.eq("user_id",userId);
        wrapper.eq("book_id",bookId);
        userCollectMapper.delete(wrapper);
        return result;
    }
}
