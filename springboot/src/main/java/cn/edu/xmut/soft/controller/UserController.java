package cn.edu.xmut.soft.controller;

import cn.edu.xmut.common.Constant;
import cn.edu.xmut.soft.entity.UserRole;
import cn.edu.xmut.soft.mapper.UserRoleMapper;
import cn.edu.xmut.utils.DateTool;
import cn.edu.xmut.utils.JwtUtil;
import cn.edu.xmut.utils.MD5Util;
import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import cn.edu.xmut.soft.mapper.UserMapper;
import cn.edu.xmut.soft.entity.User;

import cn.edu.xmut.common.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-10-26
 */
@RestController
@RequestMapping("/soft/user")
public class UserController extends BaseController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @RequestMapping(method= RequestMethod.POST,value="/login")
    public Result login(@RequestBody User user) throws Exception {
        String tmp = request.getHeader("token");
        Result result = new Result();
        QueryWrapper<User> wrapper = new QueryWrapper<User>(new User());
        wrapper.eq("username",user.getUsername());
        User userExit =
                userMapper.selectOne(wrapper);

        if(userExit != null){
            String Md5 = MD5Util.getEncode(user.getPassword(),userExit.getSalt());
            if(Md5.equals(userExit.getPassword())){
                Map<String, Object> map =
                        new HashMap<>();
                map.put("user",userExit);
                String token = JwtUtil.generateToken(userExit.getId());
                map.put("token",token);

                result.setData(map);
                result.success("登陆成功");
            }else{
                result.fail("密码不正确！！");
            }


        }else{
            result.fail("用户名不存在！！");
        }


        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize,
        String username,
        Integer useful) {
        Result result = new Result();
        Page<User> page = new Page<User>(pageNum, pageSize);
        User params = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<User>(params);

        if(username != null && !"".equals(username)){
            wrapper.like("username",username);
        }
        if(useful != null){
            wrapper.eq("useful",useful);
        }

        result.setData(userMapper.selectPage(page, wrapper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody User entity) throws Exception {
        Result result = new Result();
        if (entity.getId() == null) {//add
            entity.setUseful(Constant.GL_USEFUL);
            entity.setCreateTime(DateTool.getCurrTime());
            String pwd = entity.getPassword();
            String salt = UUID.randomUUID().toString();
            String password = MD5Util.getEncode(pwd,salt);
            entity.setSalt(salt);
            entity.setPassword(password);
            userMapper.insert(entity);
            UserRole userRole = new UserRole();
            userRole.setUserId(entity.getId());
            userRole.setRoleId("55192eb8d4544561b875af8940155220");
            userRoleMapper.insert(userRole);
        } else {//update
            userMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.DELETE,value="/delete")
    public Result delete(String ids) {
        Result result = new Result();
        List<String> deleteIds = new ArrayList<String>();
        for (String id : ids.split(",")) {
            deleteIds.add(id);
        }
        userMapper.deleteBatchIds(deleteIds);
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/updateUseful")
    public Result updateUseful( String ids,Integer useful) {
        Result result = new Result();
        for (String id : ids.split(",")) {
            //修改值
            User user = new User();
            user.setUseful(useful);
            //修改条件
            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("id", id);

            userMapper.update(user, userUpdateWrapper);
        }
        return result;
    }
}
