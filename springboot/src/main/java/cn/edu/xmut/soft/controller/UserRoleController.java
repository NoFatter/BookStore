package cn.edu.xmut.soft.controller;

import cn.edu.xmut.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;
import cn.edu.xmut.soft.mapper.UserRoleMapper;
import cn.edu.xmut.soft.entity.UserRole;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.xmut.common.BaseController;

/**
 * <p>
 * 用户角色表 前端控制器 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/soft/user-role")
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @RequestMapping(method= RequestMethod.POST,value="/getRoleByUserId")
    public Result getRoleByUserId(String id) {
        Result result = new Result();
        UserRole params = new UserRole();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>(params);
        wrapper.eq("user_id",id);
        result.setData(userRoleMapper.selectList(wrapper));
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/updateRoleByUserId")
    public Result updateRoleByUserId(String id, String ids) {
        Result result = new Result();
        //先清空数据库记录
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>(new UserRole());
        wrapper.eq("user_id",id);
        userRoleMapper.delete(wrapper);

        //插入新记录
        for (String roleId : ids.split(",")) {
            UserRole ur = new UserRole();
            ur.setRoleId(roleId);
            ur.setUserId(id);
            userRoleMapper.insert(ur);
        }
        return result;
    }

    @RequestMapping(method= RequestMethod.POST,value="/page")
    public Result page(
        Integer pageNum,
        Integer pageSize) {
        Result result = new Result();
        Page<UserRole> page = new Page<UserRole>(pageNum, pageSize);
        UserRole params = new UserRole();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>(params);
        result.setData(userRoleMapper.selectPage(page, wrapper));
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/save")
    public Result save(@RequestBody UserRole entity) {
        Result result = new Result();
        if (entity.getId() == null) {
            userRoleMapper.insert(entity);
        } else {
            userRoleMapper.updateById(entity);
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value="/delete")
    public Result delete(String ids) {
        Result result = new Result();
        List<String> deleteIds = new ArrayList<String>();
        for (String id : ids.split(",")) {
            deleteIds.add(id);
        }
        userRoleMapper.deleteBatchIds(deleteIds);
        return result;
    }

}
